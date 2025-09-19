CREATE DATABASE IF NOT EXISTS gestion_de_congreso;

USE gestion_de_congreso;

CREATE USER 'Usuario1'@'localhost' IDENTIFIED BY 'Usuario12025*';

GRANT SELECT, INSERT, UPDATE, DELETE ON gestion_de_congreso.* TO 'Usuario1'@'localhost';

CREATE TABLE IF NOT EXISTS Usuario (
	DPI_o_Pasaporte VARCHAR(20) PRIMARY KEY,
	Foto VARCHAR(200),
	Nombre VARCHAR(200) NOT NULL,
	Telefono VARCHAR(20) NOT NULL,
	Organizacion VARCHAR(200) NOT NULL,
	Email VARCHAR (200) NOT NULL UNIQUE,
	Contraseña VARCHAR(200) NOT NULL ,
	Estado BOOL NOT NULL DEFAULT 1,
	Rol ENUM('Administrador Sistema', 'Administrador Congreso', 'Participante', 'Comité Científico', 'Participante Invitado') NOT NULL DEFAULT 'Participante'
);

insert into Usuario (DPI_o_Pasaporte, Nombre, Telefono, Organizacion, Email, Contraseña, Estado, rol) 
values ('0000', 'AdminSistema', '12345678', 'Admin', 'admin@gmail.com', 'YWRtaW4xMjM=', 1, 'Administrador Sistema');

CREATE TABLE IF NOT EXISTS Cartera_Digital (
	Id_Usuario VARCHAR(20) NOT NULL,
	Monto DECIMAL(10,2) NOT NULL DEFAULT 0,
	CONSTRAINT fk_Id_Usuario FOREIGN KEY (Id_Usuario) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT pk_Id_Usuario PRIMARY KEY (Id_Usuario)
);

CREATE TABLE IF NOT EXISTS Movimiento (
	Id_Movimiento INT PRIMARY KEY AUTO_INCREMENT,
	Id_Cartera VARCHAR(20) NOT NULL,
	Cantidad DECIMAL(10,2) NOT NULL,
	Tipo ENUM ('Recarga', 'Pago') NOT NULL,
	Fecha DATETIME NOT NULL,
	CONSTRAINT fk_Id_Cartera FOREIGN KEY (Id_Cartera) REFERENCES Cartera_Digital (Id_Usuario)
);

CREATE TABLE IF NOT EXISTS Institucion (
	Id_Institucion INT PRIMARY KEY AUTO_INCREMENT,
	Nombre VARCHAR (200) NOT NULL,
	Direccion VARCHAR (400) NOT NULL,
	Telefono VARCHAR (20) NOT NULL, 
	Email VARCHAR (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Congreso (
	Id_Congreso INT PRIMARY KEY AUTO_INCREMENT,
	Id_Usuario VARCHAR(20) NOT NULL,
	Id_Institucion INT NOT NULL,
	Nombre VARCHAR (100) NOT NULL, 
	Descripcion VARCHAR (400) NOT NULL,
	Ubicacion VARCHAR (200) NOT NULL,
	Precio DECIMAL (10,2) NOT NULL,
	Convocatoria BOOL NOT NULL DEFAULT 1,
	Fecha_Inicio DATE NOT NULL, 
	Fecha_Fin DATE,
	CONSTRAINT fk_Id_Usuario2 FOREIGN KEY (Id_Usuario) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT fk_Id_Institucion FOREIGN KEY (Id_Institucion) REFERENCES Institucion (Id_Institucion)
);

CREATE TABLE IF NOT EXISTS Inscripcion (
	Id_Inscripcion INT PRIMARY KEY AUTO_INCREMENT,
	Id_Usuario VARCHAR (20) NOT NULL,
	Id_Congreso INT NOT NULL,
	CONSTRAINT fk_Id_Usuario3 FOREIGN KEY (Id_Usuario) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT fk_Id_Congreso FOREIGN KEY (Id_Congreso) REFERENCES Congreso (Id_Congreso),
	CONSTRAINT unique_Inscripcion UNIQUE (Id_Usuario, Id_Congreso)
);

CREATE TABLE IF NOT EXISTS Salon (
	Id_Salon INT PRIMARY KEY AUTO_INCREMENT,
	Id_Institucion INT NOT NULL,
	Ubicacion VARCHAR (200) NOT NULL,
	Capacidad INT NOT NULL,
	CONSTRAINT fk_Id_Institucion2 FOREIGN KEY (Id_Institucion) REFERENCES Institucion (Id_Institucion)
);

CREATE TABLE IF NOT EXISTS Participacion (
	Id_Participacion INT PRIMARY KEY AUTO_INCREMENT,
	Id_Inscripcion INT NOT NULL,
	Tipo_Participacion ENUM('Asistente','Ponente','Tallerista', 'Ponente Invitado') DEFAULT 'Asistente', 
	CONSTRAINT fk_Id_Inscripcion FOREIGN KEY (Id_Inscripcion) REFERENCES Inscripcion (Id_Inscripcion)
);

CREATE TABLE IF NOT EXISTS Comite_Cientifico (
	Id_Usuario VARCHAR (20) NOT NULL,
	Id_Congreso INT NOT NULL,
	CONSTRAINT fk_Id_Usuario5 FOREIGN KEY (Id_Usuario) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT fk_Id_Congreso3 FOREIGN KEY (Id_Congreso) REFERENCES Congreso (Id_Congreso),
	CONSTRAINT pk_Comite PRIMARY KEY (Id_Usuario, Id_Congreso)
);

CREATE TABLE IF NOT EXISTS Propuesta_Trabajo (
	Id_Propuesta_Trabajo INT PRIMARY KEY AUTO_INCREMENT,
	Titulo VARCHAR(200) NOT NULL,
	Descripcion VARCHAR(300) NOT NULL,
	Estado ENUM ('Pendiente', 'Aceptado', 'Rechazado') NOT NULL DEFAULT 'Pendiente',
	Tipo ENUM ('Ponencia', 'Taller') NOT NULL,
	Fecha_Propuesta DATE NOT NULL,
	Fecha_Revision DATE,
	Cupo_Max INT,
	Id_Inscripcion INT NOT NULL,
	CONSTRAINT fk_Id_Inscripcion2 FOREIGN KEY (Id_Inscripcion) REFERENCES Inscripcion (Id_Inscripcion)
);

CREATE TABLE IF NOT EXISTS Actividad (
	Id_Actividad INT PRIMARY KEY AUTO_INCREMENT,
	Id_Usuario VARCHAR (20) NOT NULL,
	Id_Congreso INT NOT NULL,
	Id_Salon INT NOT NULL,
	Nombre VARCHAR (200) NOT NULL,
	Descripcion VARCHAR (300) NOT NULL,
	Tipo ENUM ('Ponencia', 'Taller') NOT NULL,
	Hora_Inicio DATETIME NOT NULL,
	Hora_Fin DATETIME NOT NULL,
	Cupo_Max INT,
	CONSTRAINT fk_Id_Usuario7 FOREIGN KEY (Id_Usuario) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT fk_Id_Congreso5 FOREIGN KEY (Id_Congreso) REFERENCES Congreso (Id_Congreso),
	CONSTRAINT fk_Id_Salon FOREIGN KEY (Id_Salon) REFERENCES Salon (Id_Salon)
);

CREATE TABLE IF NOT EXISTS Colaborador (
	Id_Usuario VARCHAR (20) NOT NULL,
	Id_Actividad INT NOT NULL,
	CONSTRAINT fk_Id_Usuario8 FOREIGN KEY (Id_Usuario) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT fk_Id_Actividad FOREIGN KEY (Id_Actividad) REFERENCES Actividad (Id_Actividad),
	CONSTRAINT pk_Colaborador PRIMARY KEY (Id_Usuario, Id_Actividad)
); 

CREATE TABLE IF NOT EXISTS Reservacion (
	Id_Reservacion INT PRIMARY KEY AUTO_INCREMENT,
	Id_Actividad INT NOT NULL,
	Id_Inscripcion INT NOT NULL,
	CONSTRAINT fk_Id_Actividad2 FOREIGN KEY (Id_Actividad) REFERENCES Actividad (Id_Actividad),
	CONSTRAINT fk_Id_Inscripcion3 FOREIGN KEY (Id_Inscripcion) REFERENCES Inscripcion (Id_Inscripcion)
); 

CREATE TABLE IF NOT EXISTS Asistencia (
	Id_Asistencia INT PRIMARY KEY AUTO_INCREMENT,
	Id_Actividad INT NOT NULL,
	Id_Inscripcion INT NOT NULL,
	Registrado_Por VARCHAR (20) NOT NULL,
	CONSTRAINT fk_Id_Actividad3 FOREIGN KEY (Id_Actividad) REFERENCES Actividad (Id_Actividad),
	CONSTRAINT fk_Registrado_Por FOREIGN KEY (Registrado_Por) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT fk_Id_Inscripcion4 FOREIGN KEY (Id_Inscripcion) REFERENCES Inscripcion (Id_Inscripcion)
);

CREATE TABLE IF NOT EXISTS Configuracion_Del_Sistema (
	Id_Configuracion INT PRIMARY KEY AUTO_INCREMENT,
	Porcentaje_Comision DECIMAL (4,4) DEFAULT 0.15,
	Precio_Minimo_Congreso DECIMAL (10,2) DEFAULT 35.00
);

INSERT INTO Configuracion_Del_Sistema (Porcentaje_Comision, Precio_Minimo_Congreso) VALUES (0.15, 35.00);