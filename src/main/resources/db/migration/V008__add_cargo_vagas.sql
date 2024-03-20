ALTER TABLE vagas ADD COLUMN cargo VARCHAR(255);

COMMENT ON COLUMN vagas.cargo IS 'Cargo da vaga';