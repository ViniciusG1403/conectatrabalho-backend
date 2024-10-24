CREATE TABLE usuarios(
    id UUID PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    senha TEXT NOT NULL,
    tipo INTEGER NOT NULL,
    status INTEGER NOT NULL DEFAULT 0,
    dhRegistro TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    dhUltimaAtualizacao TIMESTAMPTZ,
    codigo VARCHAR(6),
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER'
);

COMMENT ON TABLE usuarios IS 'Tabela de usuários';
COMMENT ON COLUMN usuarios.id IS 'Identificador do usuário';
COMMENT ON COLUMN usuarios.nome IS 'Nome do usuário';
COMMENT ON COLUMN usuarios.email IS 'E-mail do usuário';
COMMENT ON COLUMN usuarios.telefone IS 'Telefone do usuário';
COMMENT ON COLUMN usuarios.senha IS 'Senha do usuário';
COMMENT ON COLUMN usuarios.tipo IS E'Tipo do usuário\n 0 - Candidato\n 1 - Empresa';
COMMENT ON COLUMN usuarios.status IS E'Status do usuário\n 0 - Inativo\n 1 - Ativo\n 2 - Bloqueado\n 3 - Excluído';
COMMENT ON COLUMN usuarios.dhRegistro IS 'Data de registro do usuário';
COMMENT ON COLUMN usuarios.dhUltimaAtualizacao IS 'Data da última atualização do usuário';
COMMENT ON COLUMN usuarios.codigo IS 'Código de verificação do usuário';
COMMENT ON COLUMN usuarios.role IS 'Regra de acesso do usuario - ROLE_USER ou ROLE_ADMIN';
