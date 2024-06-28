-- Comerciantes
INSERT INTO Comerciante (Nombre, Departamento, Municipio, Telefono, CorreoElectronico, FechaRegistro, Estado, FechaActualizacion, Usuario)
VALUES 
('Comerciante 1', 'Departamento 1', 'Municipio 1', '1234567890', 'email1@example.com', '2023-01-01', 'Activo', GETDATE(), 'admin'),
('Comerciante 2', 'Departamento 2', 'Municipio 2', '1234567891', 'email2@example.com', '2023-01-02', 'Activo', GETDATE(), 'admin'),
-- (Agregar 8 registros adicionales)

-- Establecimientos
INSERT INTO Establecimiento (ComercianteID, Nombre, Ingresos, NumeroEmpleados, FechaActualizacion, Usuario)
VALUES 
(1, 'Establecimiento 1', 1000.50, 10, GETDATE(), 'admin'),
(1, 'Establecimiento 2', 2000.75, 20, GETDATE(), 'admin'),
-- (Agregar 28 registros adicionales)
