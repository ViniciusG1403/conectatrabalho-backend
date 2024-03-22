package modules.vagas.usecases;

import core.geolocalizador.CoordenadasGeograficasDTO;
import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;
import modules.vagas.converters.VagasConverter;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.infra.entities.Vagas;
import modules.vagas.repositories.VagasRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/03/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarVagasPorProximidade {

    private final UsuarioRepository usuarioRepository;

    private final VagasConverter vagasConverter;

    private final VagasRepository vagasRepository;

    public List<VagasResumidoDTO> execute(UUID idUsuario,
        List<CondicaoPesquisa> condicaoPesquisaList, int page, int size) {
        Usuario usuarioSolicitante = usuarioRepository.findById(idUsuario).orElseThrow(
            UsuarioNotFoundException::new);

        double latitude = usuarioSolicitante.getEndereco().getLatitude();
        double longitude = usuarioSolicitante.getEndereco().getLongitude();

        CoordenadasGeograficasDTO coordenadasGeograficasDTO = CoordenadasGeograficasDTO.builder().longitude(longitude).latitude(latitude).build();

        condicaoPesquisaList.add(new CondicaoPesquisa("status", StatusVaga.ATIVA));

        return vagasRepository.findAllProximidade(condicaoPesquisaList, "dataCriacao", "DESC",coordenadasGeograficasDTO, 120, page, size).stream().map(vagasConverter::toResumidoDTO).collect(Collectors.toList());

    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
