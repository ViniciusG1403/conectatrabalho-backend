package modules.usuarios.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.enumerations.TipoUsuario;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 13/02/24
 */
@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo", nullable = false)
    private TipoUsuario tipo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private StatusUsuario status;

    @Column(name = "dhregistro")
    private Timestamp registro;

    @Column(name = "dhultimaatualizacao")
    private Timestamp ultimaAtualizacao;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
}
