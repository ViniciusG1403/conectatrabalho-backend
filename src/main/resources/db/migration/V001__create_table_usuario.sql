CREATE TYPE USER_STATUS AS ENUM('INACTIVE', 'ACTIVE');
CREATE TYPE USER_ROLES AS ENUM('BASIC', 'PREMIUM', 'MASTER');
CREATE TYPE USER_TYPE AS ENUM('CONTRACTOR', 'PROVIDER', 'BOTH', 'ADMINISTRATOR');

CREATE TABLE user (
    id UUID NOT NULL,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    dh_register TIMESTAMPTZ NOT NULL,
    status USER_STATUS NOT NULL,
    lastlogin TIMESTAMPTZ NULL,
    lastUpdate TIMESTAMPTZ NULL,
    code TEXT NULL,
    role USER_ROLES NOT NULL,
    type USER_TYPE NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

COMMENT ON TABLE user IS E'Tabela de usuários';
COMMENT ON COLUMN user.id IS E'UUID';
COMMENT ON COLUMN user.name IS E'Nome';
COMMENT ON COLUMN user.email IS E'Email';
COMMENT ON COLUMN user.password IS E'Senha';
COMMENT ON COLUMN user.dh_register IS E'Data e hora do cadastro';
COMMENT ON COLUMN user.status IS E'Situação:\n0 - INACTIVE\n1 - ACTIVE';
COMMENT ON COLUMN user.lastlogin IS E'Data e hora do ultimo login';
COMMENT ON COLUMN user.lastUpdate IS E'Data e hora da última atualização';
COMMENT ON COLUMN user.code IS E'Código gerado para confirmacões via email';
COMMENT ON COLUMN user.role IS E'Nível de acesso:\n0 - BASIC\n1 - PREMIUM\n2 - MASTER';
COMMENT ON COLUMN user.type IS E'Tipo de usuário:\n0 - CONTRACTOR\n1 - PROVIDER\n2 - BOTH\n3 - ADMINISTRATOR';

CREATE TABLE contractor_profile (
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    enterprise TEXT NOT NULL,
    sector TEXT NOT NULL,
    localization TEXT NOT NULL,
    description TEXT NOT NULL,
    website TEXT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT pk_perfil PRIMARY KEY (id)
);

COMMENT ON TABLE contractor_profile IS E'Tabela de perfil de contratante';
COMMENT ON COLUMN contractor_profile.id IS E'UUID';
COMMENT ON COLUMN contractor_profile.user_id IS E'UUID do usuário';
COMMENT ON COLUMN contractor_profile.enterprise IS E'Nome da empresa';
COMMENT ON COLUMN contractor_profile.sector IS E'Ramo de atuação';
COMMENT ON COLUMN contractor_profile.localization IS E'Localização';
COMMENT ON COLUMN contractor_profile.description IS E'Descrição';
COMMENT ON COLUMN contractor_profile.website IS E'Site da empresa';

CREATE TABLE worker_profile(
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    profession TEXT NOT NULL,
    experience TEXT NOT NULL,
    habilities TEXT NOT NULL,
    curriculum_url TEXT NULL,
    portfolio_url TEXT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT pk_perfil PRIMARY KEY (id)
);

COMMENT ON TABLE worker_profile IS E'Tabela de perfil de prestador';
COMMENT ON COLUMN worker_profile.id IS E'UUID';
COMMENT ON COLUMN worker_profile.user_id IS E'UUID do usuário';
COMMENT ON COLUMN worker_profile.profession IS E'Profissão';
COMMENT ON COLUMN worker_profile.experience IS E'Experiência';
COMMENT ON COLUMN worker_profile.habilities IS E'Habilidades';
COMMENT ON COLUMN worker_profile.curriculum_url IS E'URL do currículo';
COMMENT ON COLUMN worker_profile.portfolio_url IS E'URL do portfólio';