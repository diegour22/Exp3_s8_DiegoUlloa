-- =========================================================
-- Script SQL: Creación Base de Datos CineDB
-- Autor: Diego Ulloa
-- Materia: DESARROLLO ORIENTADO A OBJETOS II
-- Profesor: EITHEL KLAUSS PATRICIO GONZALEZ ROJAS
-- Descripción:
--   Este script crea la base de datos del proyecto Magenta Cinema
--   e inserta algunos registros de ejemplo para pruebas.
-- =========================================================

-- 1. Crear base de datos (si no existe)
CREATE DATABASE IF NOT EXISTS CineDB
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- 2. Usar base de datos
USE CineDB;

-- 3. Crear tabla Pelicula
CREATE TABLE IF NOT EXISTS Pelicula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracionMin INT NOT NULL,
    clasificacion VARCHAR(20),
    sinopsis TEXT
);

-- 4. Insertar registros de prueba
INSERT INTO Pelicula (titulo, director, genero, anio, duracionMin, clasificacion, sinopsis)
VALUES
('Inception', 'Christopher Nolan', 'Ciencia Ficción', 2010, 148, 'PG-13', 'Un ladrón que roba secretos a través de los sueños.'),
('Titanic', 'James Cameron', 'Romance', 1997, 195, 'PG-13', 'Una historia de amor a bordo del famoso barco.'),
('Gladiador', 'Ridley Scott', 'Acción', 2000, 155, 'R', 'Un general romano busca vengar la muerte de su familia.'),
('Avatar', 'James Cameron', 'Ciencia Ficción', 2009, 162, 'PG-13', 'Un exmarine se une a una tribu alienígena en Pandora.'),
('El Señor de los Anillos', 'Peter Jackson', 'Fantasía', 2001, 178, 'PG-13', 'Una aventura épica para destruir un anillo de poder.'),
('Interestelar', 'Christopher Nolan', 'Aventura', 2014, 169, 'PG-13', 'Exploradores viajan por un agujero de gusano en busca de un nuevo hogar.');

-- 5. Verificar datos insertados
SELECT * FROM Pelicula;
