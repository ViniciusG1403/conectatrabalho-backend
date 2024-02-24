CREATE TABLE empresa(
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    empresa VARCHAR(100) NOT NULL,
    setor VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    website TEXT NULL,
    linkedin TEXT  NULL,
    url_fotoperfil TEXT NOT NULL
);

COMMENT ON TABLE empresa IS 'Tabela de empresas';
COMMENT ON COLUMN empresa.id IS 'Identificador único da tabela';
COMMENT ON COLUMN empresa.usuario_id IS 'Identificador do usuário';
COMMENT ON COLUMN empresa.empresa IS 'Nome da empresa';
COMMENT ON COLUMN empresa.setor IS 'Setor da empresa';
COMMENT ON COLUMN empresa.descricao IS 'Descrição da empresa';
COMMENT ON COLUMN empresa.website IS 'Website da empresa';
COMMENT ON COLUMN empresa.linkedin IS 'Perfil do linkedin da empresa';
COMMENT ON COLUMN empresa.url_fotoperfil IS 'URL da foto de perfil da empresa';

ALTER TABLE empresa
ADD CONSTRAINT fk_empresa_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE;