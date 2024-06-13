CREATE TABLE IF NOT EXISTS cliente (
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      nombre VARCHAR(255) DEFAULT NULL COMMENT 'nombre',
                      apellidos VARCHAR(255) DEFAULT NULL COMMENT 'apellidos',
                      tipo_documento VARCHAR(255) DEFAULT NULL COMMENT 'tipoDocumento',
                      numero_documento VARCHAR(255) DEFAULT NULL COMMENT 'numeroDocumento');


