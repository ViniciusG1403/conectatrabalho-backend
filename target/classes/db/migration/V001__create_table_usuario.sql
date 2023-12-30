CREATE TYPE USER_STATUS AS ENUM('INACTIVE', 'ACTIVE');
CREATE TYPE USER_ROLES AS ENUM('BASIC', 'PREMIUM', 'MASTER');
CREATE TYPE USER_TYPE AS ENUM('CONTRACTOR', 'PROVIDER', 'BOTH', 'ADMINISTRATOR');

CREATE TABLE localization (
     id UUID NOT NULL,
     cep TEXT NOT NULL,
     street TEXT NOT NULL,
     number TEXT NOT NULL,
     complement TEXT NULL,
     neighborhood TEXT NOT NULL,
     city TEXT NOT NULL,
     state TEXT NOT NULL,
     CONSTRAINT pk_localization PRIMARY KEY (id)
);

COMMENT ON TABLE localization IS E'Tabela de localização';
COMMENT ON COLUMN localization.id IS E'UUID';
COMMENT ON COLUMN localization.cep IS E'CEP';
COMMENT ON COLUMN localization.street IS E'Rua';
COMMENT ON COLUMN localization.number IS E'Número';
COMMENT ON COLUMN localization.complement IS E'Complemento';
COMMENT ON COLUMN localization.neighborhood IS E'Bairro';
COMMENT ON COLUMN localization.city IS E'Cidade';
COMMENT ON COLUMN localization.state IS E'Estado';

CREATE TABLE app_users (
    id UUID NOT NULL,
    localization_id UUID NOT NULL,
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
    CONSTRAINT fk_localization FOREIGN KEY (localization_id) REFERENCES localization (id),
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

COMMENT ON TABLE app_users IS E'Tabela de usuários';
COMMENT ON COLUMN app_users.id IS E'UUID';
COMMENT ON COLUMN app_users.localization_id IS E'UUID da localização';
COMMENT ON COLUMN app_users.name IS E'Nome';
COMMENT ON COLUMN app_users.email IS E'Email';
COMMENT ON COLUMN app_users.password IS E'Senha';
COMMENT ON COLUMN app_users.dh_register IS E'Data e hora do cadastro';
COMMENT ON COLUMN app_users.status IS E'Situação:\n0 - INACTIVE\n1 - ACTIVE';
COMMENT ON COLUMN app_users.lastlogin IS E'Data e hora do ultimo login';
COMMENT ON COLUMN app_users.lastUpdate IS E'Data e hora da última atualização';
COMMENT ON COLUMN app_users.code IS E'Código gerado para confirmacões via email';
COMMENT ON COLUMN app_users.role IS E'Nível de acesso:\n0 - BASIC\n1 - PREMIUM\n2 - MASTER';
COMMENT ON COLUMN app_users.type IS E'Tipo de usuário:\n0 - CONTRACTOR\n1 - PROVIDER\n2 - BOTH\n3 - ADMINISTRATOR';

CREATE TABLE contractor_profile (
    id UUID NOT NULL,
    app_users_id UUID NOT NULL,
    enterprise TEXT NOT NULL,
    sector TEXT NOT NULL,
    description TEXT NOT NULL,
    website TEXT NULL,
    CONSTRAINT fk_app_users FOREIGN KEY (app_users_id) REFERENCES app_users (id),
    CONSTRAINT pk_contractor_profile PRIMARY KEY (id)
);

COMMENT ON TABLE contractor_profile IS E'Tabela de perfil de contratante';
COMMENT ON COLUMN contractor_profile.id IS E'UUID';
COMMENT ON COLUMN contractor_profile.app_users_id IS E'UUID do usuário';
COMMENT ON COLUMN contractor_profile.enterprise IS E'Nome da empresa';
COMMENT ON COLUMN contractor_profile.sector IS E'Ramo de atuação';
COMMENT ON COLUMN contractor_profile.description IS E'Descrição';
COMMENT ON COLUMN contractor_profile.website IS E'Site da empresa';

CREATE TABLE worker_profile(
    id UUID NOT NULL,
    app_users_id UUID NOT NULL,
    profession TEXT NOT NULL,
    experience TEXT NOT NULL,
    habilities TEXT NOT NULL,
    curriculum_url TEXT NULL,
    portfolio_url TEXT NULL,
    CONSTRAINT fk_app_users FOREIGN KEY (app_users_id) REFERENCES app_users (id),
    CONSTRAINT pk_worker_profile PRIMARY KEY (id)
);

COMMENT ON TABLE worker_profile IS E'Tabela de perfil de prestador';
COMMENT ON COLUMN worker_profile.id IS E'UUID';
COMMENT ON COLUMN worker_profile.app_users_id IS E'UUID do usuário';
COMMENT ON COLUMN worker_profile.profession IS E'Profissão';
COMMENT ON COLUMN worker_profile.experience IS E'Experiência';
COMMENT ON COLUMN worker_profile.habilities IS E'Habilidades';
COMMENT ON COLUMN worker_profile.curriculum_url IS E'URL do currículo';
COMMENT ON COLUMN worker_profile.portfolio_url IS E'URL do portfólio';