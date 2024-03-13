package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioResponseDTO;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 12/03/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarTodosUsuarioProximidade {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioConverter usuarioConverter;

    public List<UsuarioResponseDTO> execute(UUID idUsuario) {
        Usuario usuarioSolicitante = usuarioRepository.findById(idUsuario).orElseThrow(
            UsuarioNotFoundException::new);

        double latitude = Double.parseDouble(usuarioSolicitante.getEndereco().getLatitude());
        double longitude = Double.parseDouble(usuarioSolicitante.getEndereco().getLongitude());

        List<Usuario> allUsers = usuarioRepository.findAll();

        return allUsers.stream()
            .filter(user -> calculateDistance(latitude, longitude,
                Double.parseDouble(user.getEndereco().getLatitude()),
                Double.parseDouble(user.getEndereco().getLongitude())) <= 80)
            .map(usuarioConverter::toResponse)
            .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // convert to km
    }
}
