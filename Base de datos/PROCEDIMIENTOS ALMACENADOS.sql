-- Procedimiento para Crear Comerciante
CREATE PROCEDURE sp_CrearComerciante
    @Nombre VARCHAR(100),
    @Departamento VARCHAR(50),
    @Municipio VARCHAR(50),
    @Telefono VARCHAR(15) = NULL,
    @CorreoElectronico VARCHAR(100) = NULL,
    @FechaRegistro DATE,
    @Estado VARCHAR(20),
    @CodigoError INT OUTPUT,
    @MensajeError NVARCHAR(200) OUTPUT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Comerciante (Nombre, Departamento, Municipio, Telefono, CorreoElectronico, FechaRegistro, Estado, FechaActualizacion, Usuario)
        VALUES (@Nombre, @Departamento, @Municipio, @Telefono, @CorreoElectronico, @FechaRegistro, @Estado, GETDATE(), SYSTEM_USER);

        SET @CodigoError = 0;
        SET @MensajeError = NULL;
    END TRY
    BEGIN CATCH
        SET @CodigoError = ERROR_NUMBER();
        SET @MensajeError = ERROR_MESSAGE();
    END CATCH
END;

-- Procedimiento para Actualizar Comerciante
CREATE PROCEDURE sp_ActualizarComerciante
    @ComercianteID INT,
    @Nombre VARCHAR(100),
    @Departamento VARCHAR(50),
    @Municipio VARCHAR(50),
    @Telefono VARCHAR(15) = NULL,
    @CorreoElectronico VARCHAR(100) = NULL,
    @FechaRegistro DATE,
    @Estado VARCHAR(20),
    @CodigoError INT OUTPUT,
    @MensajeError NVARCHAR(200) OUTPUT
AS
BEGIN
    BEGIN TRY
        UPDATE Comerciante
        SET Nombre = @Nombre, 
            Departamento = @Departamento, 
            Municipio = @Municipio, 
            Telefono = @Telefono, 
            CorreoElectronico = @CorreoElectronico, 
            FechaRegistro = @FechaRegistro, 
            Estado = @Estado,
            FechaActualizacion = GETDATE(),
            Usuario = SYSTEM_USER
        WHERE ComercianteID = @ComercianteID;

        SET @CodigoError = 0;
        SET @MensajeError = NULL;
    END TRY
    BEGIN CATCH
        SET @CodigoError = ERROR_NUMBER();
        SET @MensajeError = ERROR_MESSAGE();
    END CATCH
END;

-- Procedimiento para Eliminar Comerciante
CREATE PROCEDURE sp_EliminarComerciante
    @ComercianteID INT,
    @CodigoError INT OUTPUT,
    @MensajeError NVARCHAR(200) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM Comerciante WHERE ComercianteID = @ComercianteID;

        SET @CodigoError = 0;
        SET @MensajeError = NULL;
    END TRY
    BEGIN CATCH
        SET @CodigoError = ERROR_NUMBER();
        SET @MensajeError = ERROR_MESSAGE();
    END CATCH
END;

-- Función para Consultar Comerciante por ID
CREATE FUNCTION fn_ConsultarComerciantePorID (@ComercianteID INT)
RETURNS TABLE
AS
RETURN
SELECT C.Nombre, C.Departamento, C.Municipio, C.Telefono, C.CorreoElectronico, C.FechaRegistro, C.Estado,
       SUM(E.Ingresos) AS TotalActivos, SUM(E.NumeroEmpleados) AS CantidadEmpleados
FROM Comerciante C
LEFT JOIN Establecimiento E ON C.ComercianteID = E.ComercianteID
WHERE C.ComercianteID = @ComercianteID
GROUP BY C.Nombre, C.Departamento, C.Municipio, C.Telefono, C.CorreoElectronico, C.FechaRegistro, C.Estado;

-- Función para Consultar Comerciante con Filtros y Paginación
CREATE FUNCTION fn_ConsultarComerciante (
    @Nombre VARCHAR(100) = NULL,
    @Municipio VARCHAR(50) = NULL,
    @FechaRegistro DATE = NULL,
    @Estado VARCHAR(20) = NULL,
    @Page INT = 1,
    @PageSize INT = 10
)
RETURNS @Result TABLE (
    Nombre VARCHAR(100),
    Departamento VARCHAR(50),
    Municipio VARCHAR(50),
    Telefono VARCHAR(15),
    CorreoElectronico VARCHAR(100),
    FechaRegistro DATE,
    Estado VARCHAR(20),
    TotalActivos DECIMAL(18,2),
    CantidadEmpleados INT
)
AS
BEGIN
    INSERT INTO @Result
    SELECT C.Nombre, C.Departamento, C.Municipio, C.Telefono, C.CorreoElectronico, C.FechaRegistro, C.Estado,
           SUM(E.Ingresos) AS TotalActivos, SUM(E.NumeroEmpleados) AS CantidadEmpleados
    FROM Comerciante C
    LEFT JOIN Establecimiento E ON C.ComercianteID = E.ComercianteID
    WHERE (@Nombre IS NULL OR C.Nombre LIKE '%' + @Nombre + '%')
      AND (@Municipio IS NULL OR C.Municipio = @Municipio)
      AND (@FechaRegistro IS NULL OR C.FechaRegistro = @FechaRegistro)
      AND (@Estado IS NULL OR C.Estado = @Estado)
    GROUP BY C.Nombre, C.Departamento, C.Municipio, C.Telefono, C.CorreoElectronico, C.FechaRegistro, C.Estado
    ORDER BY C.Nombre
    OFFSET (@Page - 1) * @PageSize ROWS
    FETCH NEXT @PageSize ROWS ONLY;

    RETURN;
END;
