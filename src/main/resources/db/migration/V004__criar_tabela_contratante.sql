CREATE TABLE contratante(
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    empresa VARCHAR(100) NOT NULL,
    setor VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    website TEXT NULL,
    linkedin TEXT  NULL,
    url_fotoperfil TEXT NOT NULL
);

COMMENT ON TABLE contratante IS 'Tabela de contratantes';
COMMENT ON COLUMN contratante.id IS 'Identificador único da tabela';
COMMENT ON COLUMN contratante.usuario_id IS 'Identificador do usuário';
COMMENT ON COLUMN contratante.empresa IS 'Nome da empresa';
COMMENT ON COLUMN contratante.setor IS 'Setor da empresa';
COMMENT ON COLUMN contratante.descricao IS 'Descrição da empresa';
COMMENT ON COLUMN contratante.website IS 'Website da empresa';
COMMENT ON COLUMN contratante.linkedin IS 'Perfil do linkedin da empresa';
COMMENT ON COLUMN contratante.url_fotoperfil IS 'URL da foto de perfil da empresa';

ALTER TABLE contratante
ADD CONSTRAINT fk_contratante_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE;