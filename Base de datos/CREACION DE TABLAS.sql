

CREATE TABLE Comerciante (
    ComercianteID INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Departamento VARCHAR(50) NOT NULL,
    Municipio VARCHAR(50) NOT NULL,
    Telefono VARCHAR(15),
    CorreoElectronico VARCHAR(100),
    FechaRegistro DATE NOT NULL,
    Estado VARCHAR(20) NOT NULL,
    FechaActualizacion DATETIME NOT NULL,
    Usuario VARCHAR(50) NOT NULL
);

CREATE TABLE Establecimiento (
    EstablecimientoID INT PRIMARY KEY,
    ComercianteID INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Ingresos DECIMAL(18,2) NOT NULL,
    NumeroEmpleados INT NOT NULL,
    FechaActualizacion DATETIME NOT NULL,
    Usuario VARCHAR(50) NOT NULL,
    FOREIGN KEY (ComercianteID) REFERENCES Comerciante(ComercianteID)
);