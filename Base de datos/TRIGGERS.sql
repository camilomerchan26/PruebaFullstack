CREATE SEQUENCE ComercianteSeq AS INT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE EstablecimientoSeq AS INT START WITH 1 INCREMENT BY 1;

CREATE TRIGGER trg_Comerciante_Insert
ON Comerciante
AFTER INSERT
AS
BEGIN
    UPDATE Comerciante
    SET ComercianteID = NEXT VALUE FOR ComercianteSeq,
        FechaActualizacion = GETDATE(),
        Usuario = SYSTEM_USER
    WHERE ComercianteID IS NULL;
END;

CREATE TRIGGER trg_Establecimiento_Insert
ON Establecimiento
AFTER INSERT
AS
BEGIN
    UPDATE Establecimiento
    SET EstablecimientoID = NEXT VALUE FOR EstablecimientoSeq,
        FechaActualizacion = GETDATE(),
        Usuario = SYSTEM_USER
    WHERE EstablecimientoID IS NULL;
END;

CREATE TRIGGER trg_Comerciante_Update
ON Comerciante
AFTER UPDATE
AS
BEGIN
    UPDATE Comerciante
    SET FechaActualizacion = GETDATE(),
        Usuario = SYSTEM_USER
    WHERE ComercianteID IN (SELECT ComercianteID FROM inserted);
END;

CREATE TRIGGER trg_Establecimiento_Update
ON Establecimiento
AFTER UPDATE
AS
BEGIN
    UPDATE Establecimiento
    SET FechaActualizacion = GETDATE(),
        Usuario = SYSTEM_USER
    WHERE EstablecimientoID IN (SELECT EstablecimientoID FROM inserted);
END;
