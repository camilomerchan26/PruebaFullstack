-- Función para Obtener Comerciantes Registrados y Activos con Detalles
CREATE FUNCTION fn_ObtenerComerciantesActivos ()
RETURNS CURSOR
AS
BEGIN
    DECLARE comerciantes_cursor CURSOR FOR
    SELECT C.Nombre, C.Departamento, C.Municipio, C.Telefono, C.CorreoElectronico, C.FechaRegistro, C.Estado,
           COUNT(E.EstablecimientoID) AS CantidadEstablecimientos, SUM(E.Ingresos) AS TotalActivos, SUM(E.NumeroEmpleados) AS CantidadEmpleados
    FROM Comerciante C
    LEFT JOIN Establecimiento E ON C.ComercianteID = E.ComercianteID
    WHERE C.Estado = 'Activo'
    GROUP BY C.Nombre, C.Departamento, C.Municipio, C.Telefono, C.CorreoElectronico, C.FechaRegistro, C.Estado
    ORDER BY CantidadEstablecimientos DESC;

    RETURN comerciantes_cursor;
END;
