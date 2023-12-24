CREATE TYPE USER_STATUS AS ENUM('INATIVO', 'ATIVO');
CREATE TYPE USER_ROLES AS ENUM('BASICO', 'PREMIUM', 'MASTER');
CREATE TYPE USER_TYPE AS ENUM('CONTRATANTE', 'PRESTADOR', 'AMBOS', 'ADMINISTRADOR');

CREATE TABLE user (
    id UUID NOT NULL,
    name TEXT NOT NULL,
    mail TEXT NOT NULL,
    password TEXT NOT NULL,
    dh_register TIMESTAMPTZ NOT NULL,
    status USER_STATUS NOT NULL,
    lastlogin TIMESTAMPTZ NULL,
    code TEXT NULL,
    role USER_ROLES NOT NULL,
    type USER_TYPE NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

COMMENT ON TABLE user IS E'Tabela de usuários';
COMMENT ON COLUMN user.id IS E'UUID';
COMMENT ON COLUMN user.name IS E'Nome';
COMMENT ON COLUMN user.mail IS E'Email';
COMMENT ON COLUMN user.password IS E'Senha';
COMMENT ON COLUMN user.dh_register IS E'Data e hora do cadastro';
COMMENT ON COLUMN user.status IS E'Situação:\n0 - INATIVO\n1 - ATIVO';
COMMENT ON COLUMN user.lastlogin IS E'Data e hora do ultimo login';
COMMENT ON COLUMN user.code IS E'Código gerado para confirmacões via email';
COMMENT ON COLUMN user.role IS E'Nível de acesso:\n0 - BÁSICO\n1 - PREMIUM\n2 - MASTER';
COMMENT ON COLUMN user.type IS E'Tipo de usuário:\n0 - CONTRATANTE\n1 - PRESTADOR\n2 - AMBOS\n3 - ADMINISTRADOR';