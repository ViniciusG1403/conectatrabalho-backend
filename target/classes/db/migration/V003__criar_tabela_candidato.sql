CREATE TABLE candidato (
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    habilidades TEXT NULL,
    linkedin TEXT  NULL,
    github TEXT  NULL,
    portfolio TEXT  NULL,
    disponibilidade VARCHAR(40) NOT NULL,
    pretensao_salarial DECIMAL(10,2) NOT NULL
);

COMMENT ON TABLE candidato IS 'Tabela de candidatos';
COMMENT ON COLUMN candidato.id IS 'Identificador único do candidato';
COMMENT ON COLUMN candidato.usuario_id IS 'Identificador único do usuário';
COMMENT ON COLUMN candidato.habilidades IS 'Habilidades do candidato';
COMMENT ON COLUMN candidato.linkedin IS 'Perfil do candidato no LinkedIn';
COMMENT ON COLUMN candidato.github IS 'Perfil do candidato no GitHub';
COMMENT ON COLUMN candidato.portfolio IS 'Perfil do candidato no Portfolio';
COMMENT ON COLUMN candidato.disponibilidade IS 'Disponibilidade do candidato';
COMMENT ON COLUMN candidato.pretensao_salarial IS 'Pretensão salarial do candidato';

ALTER TABLE candidato
ADD CONSTRAINT fk_candidato_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE;