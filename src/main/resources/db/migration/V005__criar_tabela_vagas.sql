CREATE TABLE vagas(
    id UUID PRIMARY KEY,
    id_perfilcontratante UUID NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    remuneracao DECIMAL(18,2) NOT NULL,
    tipo INTEGER NOT NULL,
    nivel VARCHAR(20) NOT NULL,
    status INT NOT NULL,
    data_criacao TIMESTAMPTZ NOT NULL
);

COMMENT ON TABLE vagas IS 'Tabela de vagas de emprego';
COMMENT ON COLUMN vagas.id IS 'Identificador único da vaga';
COMMENT ON COLUMN vagas.id_perfilcontratante IS 'Identificador único do perfil do contratante';
COMMENT ON COLUMN vagas.titulo IS 'Título da vaga';
COMMENT ON COLUMN vagas.descricao IS 'Descrição da vaga';
COMMENT ON COLUMN vagas.remuneracao IS 'Remuneração da vaga';
COMMENT ON COLUMN vagas.tipo IS E'Tipo da vaga\n 0 - CLT\n 1 - PJ\n 2 - Estágio\n 3 - Trainee';
COMMENT ON COLUMN vagas.nivel IS 'Nível da vaga';
COMMENT ON COLUMN vagas.status IS 'Status da vaga\n 0 - Inativa\n 1 - Ativa\n 2 - Pausada\n 3 - Encerrada';
COMMENT ON COLUMN vagas.data_criacao IS 'Data de criação da vaga';

ALTER TABLE vagas
ADD CONSTRAINT fk_vagas_perfilcontratante
FOREIGN KEY (id_perfilcontratante)
REFERENCES contratante(id)