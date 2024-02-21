package modules.contratante.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.infra.entities.Usuario;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Getter
@Setter
@Entity
@Table(name = "contratante")
public class Contratante {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "empresa", length = 100, nullable = false)
    private String empresa;

    @Column(name = "setor", length = 100, nullable = false)
    private String setor;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "website")
    private String website;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "url_fotoperfil")
    private String urlFotoPerfil;

}
