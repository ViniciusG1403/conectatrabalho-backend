CREATE TABLE endereco(
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    cep VARCHAR(8) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    pais VARCHAR(60) NOT NULL,
    municipio VARCHAR(60) NOT NULL,
    bairro VARCHAR(60) NOT NULL,
    logradouro VARCHAR(60) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(60)
);

COMMENT ON TABLE endereco IS 'Tabela de endereços';
COMMENT ON COLUMN endereco.id IS 'Identificador do endereço';
COMMENT ON COLUMN endereco.usuario_id IS 'Identificador do usuário';
COMMENT ON COLUMN endereco.cep IS 'CEP do endereço';
COMMENT ON COLUMN endereco.estado IS 'Estado do endereço';
COMMENT ON COLUMN endereco.pais IS 'País do endereço';
COMMENT ON COLUMN endereco.municipio IS 'Município do endereço';
COMMENT ON COLUMN endereco.bairro IS 'Bairro do endereço';
COMMENT ON COLUMN endereco.logradouro IS 'Logradouro do endereço';
COMMENT ON COLUMN endereco.numero IS 'Número do endereço';
COMMENT ON COLUMN endereco.complemento IS 'Complemento do endereço';


ALTER TABLE endereco
ADD CONSTRAINT fk_endereco_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE;