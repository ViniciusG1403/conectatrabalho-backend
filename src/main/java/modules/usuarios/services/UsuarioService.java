package modules.usuarios.services;

import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.dtos.UsuarioRegistroDTO;
import modules.usuarios.dtos.UsuarioResponseDTO;
import modules.usuarios.usecases.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 13/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UsuarioService extends Validators {

    private final CriarOuAtualizarUsuario criarOuAtualizarUsuario;

    private final BuscarUsuarioPeloID buscarUsuarioPeloID;

    private final AtivarUsuario ativarUsuario;

    private final InativarUsuario inativarUsuario;

    private final BloquearUsuario bloquearUsuario;

    private final BuscarTodosOsUsuarios buscarTodosOsUsuarios;

    public UsuarioResponseDTO criarOuAtualizar(UsuarioDTO dto) {
        NonNullValidate(dto.getNome(), "Nome");
        NonNullValidate(dto.getEmail(), "Email");
        NonNullValidate(dto.getTelefone(), "Telefone");
        NonNullValidate(dto.getTipo(), "Tipo");
        NonNullValidate(dto.getSenha(), "Senha");
        EmailFormatValidate(dto.getEmail());

        return criarOuAtualizarUsuario.execute(dto);
    }

    public UsuarioResponseDTO buscarPeloId(String id) {
        return buscarUsuarioPeloID.execute(UUID.fromString(id));
    }

    public void ativarUsuario(String codigo) {
        ativarUsuario.execute(codigo);
    }

    public void inativarUsuario(String codigo) {
        inativarUsuario.execute(codigo);
    }

    public void bloquearUsuario(String id) {
        bloquearUsuario.execute(UUID.fromString(id));
    }

    public List<UsuarioResponseDTO> buscarTodosUsuarios() { return buscarTodosOsUsuarios.execute(); }

}
