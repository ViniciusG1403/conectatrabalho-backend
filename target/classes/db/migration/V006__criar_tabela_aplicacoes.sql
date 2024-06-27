CREATE TABLE aplicacoes(
    id UUID NOT NULL,
    id_vaga UUID NOT NULL,
    id_candidato UUID NOT NULL,
    data_aplicacao TIMESTAMP NOT NULL,
    feedbackCandidato TEXT,
    feedbackEmpresa TEXT,
    status INT NOT NULL
);

COMMENT ON TABLE aplicacoes IS 'Tabela que armazena as aplicacoes dos candidatos as vagas';
COMMENT ON COLUMN aplicacoes.id IS 'Identificador unico da aplicacao';
COMMENT ON COLUMN aplicacoes.id_vaga IS 'Identificador unico da vaga';
COMMENT ON COLUMN aplicacoes.id_candidato IS 'Identificador unico do candidato';
COMMENT ON COLUMN aplicacoes.data_aplicacao IS 'Data da aplicacao';
COMMENT ON COLUMN aplicacoes.feedbackCandidato IS 'Feedback do candidato';
COMMENT ON COLUMN aplicacoes.feedbackEmpresa IS 'Feedback da empresa';
COMMENT ON COLUMN aplicacoes.status IS E'Status da aplicacao:\n0 - Pendente\n1 - Aprovado\n2 - Reprovado\n3 - Cancelado';


