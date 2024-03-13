ALTER TABLE endereco ADD COLUMN latitude TEXT;
ALTER TABLE endereco ADD COLUMN longitude TEXT;

COMMENT ON COLUMN endereco.latitude IS 'Latitude do endereço';
COMMENT ON COLUMN endereco.longitude IS 'Longitude do endereço';