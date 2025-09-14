CREATE DATABASE IF NOT EXISTS gestion_de_congreso;
USE gestion_de_congreso;

CREATE TABLE IF NOT EXISTS Usuario (
	DPI_o_Pasaporte VARCHAR(20) PRIMARY KEY,
	Foto VARCHAR(200),
	Nombre VARCHAR(200) NOT NULL,
	Telefono VARCHAR(20) NOT NULL,
	Organizacion VARCHAR(200) NOT NULL,
	Email VARCHAR (200) NOT NULL UNIQUE,
	Password VARCHAR(200) NOT NULL ,
	Estado BOOL NOT NULL DEFAULT 1,
	Rol ENUM('Administrador Sistema', 'Administrador Congreso', 'Participante', 'Comité Científico', 'Participante Invitado') NOT NULL DEFAULT 'Participante'
);

CREATE TABLE IF NOT EXISTS Cartera_Digital(
	Id_Usuario VARCHAR(20) UNIQUE NOT NULL,
	Monto DECIMAL(10,2) NOT NULL DEFAULT 0,
	CONSTRAINT fk_Id_Usuario FOREIGN KEY (Id_Usuario) REFERENCES Usuario (DPI_o_Pasaporte),
	CONSTRAINT pk_Id_Usuario PRIMARY KEY (Id_Usuario)
);

CREATE TABLE IF NOT EXISTS Movimiento(
	Id_Movimiento INT PRIMARY KEY AUTO_INCREMENT,
	Id_Cartera VARCHAR(20) NOT NULL,
	Cantidad DECIMAL(10,2) NOT NULL,
	Tipo ENUM ('Recarga', 'Pago') NOT NULL,
	Fecha DATETIME,
	CONSTRAINT fk_Id_Cartera FOREIGN KEY (Id_Cartera) REFERENCES Cartera_Digital (Id_Usuario)
);