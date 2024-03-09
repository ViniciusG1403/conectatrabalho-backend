package modules.empresa.services;

import core.pesquisa.CondicaoPesquisa;
import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.empresa.dtos.*;
import modules.empresa.usecases.*;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class EmpresaService extends Validators {

    private final CriarEmpresa criarEmpresa;

    private final UpdateEmpresa updateEmpresa;

    private final BuscarEmpresaPeloID buscarEmpresaPeloID;

    private final BuscarEmpresas buscarEmpresas;

    private final BuscarEmpresaResumidoByID buscarEmpresaResumidoByID;

    private final SalvarImagemPerfilEmpresa salvarImagemPerfilEmpresa;

    private final BuscarImagemPerfilEmpresa buscarImagemPerfilEmpresa;

    public EmpresaCadastroResponseDTO criar(EmpresaRegisterDTO dto) {
        NonNullValidate(dto.getIdUsuario(), "Usuário");
        NonNullValidate(dto.getSetor(), "Setor");
        NonNullValidate(dto.getDescricao(), "Descrição");

        return criarEmpresa.execute(dto);
    }

    public void atualizar(EmpresaUpdateDTO dto) {
        NonNullValidate(dto.getId(), "ID");
        NonNullValidate(dto.getSetor(), "Setor");
        NonNullValidate(dto.getDescricao(), "Descrição");

        updateEmpresa.execute(dto);
    }

    public EmpresaResponseDTO buscarPeloID(String id) {
        NonNullValidate(id, "ID");
        return buscarEmpresaPeloID.execute(UUID.fromString(id));
    }

    public List<EmpresaResumidoDTO> buscarEmpresas(List<CondicaoPesquisa> condicoesList, int page, int size) {
        return buscarEmpresas.execute(condicoesList, page, size);
    }

    public List<EmpresaResumidoDTO> buscarEmpresaResumidoByID(String id) {
        return buscarEmpresaResumidoByID.execute(UUID.fromString(id));
    }

    public void salvarImagemPerfilEmpresa(MultipartFormDataInput input){
        salvarImagemPerfilEmpresa.execute(input);
    }

    public InputStream buscarImagemPerfilEmpresa(String id){
        return buscarImagemPerfilEmpresa.execute(id);
    }
}
