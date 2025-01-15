CREATE TABLE IF NOT EXISTS alumnos (
    id bigint,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    estado VARCHAR(50),
    edad INT
);

INSERT INTO alumnos (id, nombre, apellido, estado, edad) 
VALUES (1, 'andre', 'pantoja', 'activo', 23);
