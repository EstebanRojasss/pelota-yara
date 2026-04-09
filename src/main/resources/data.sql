INSERT INTO equipos (id, apodo, fundacion, nombre)
VALUES
(1, 'El Decano', '1902-07-25', 'Club Olimpia'),
(2, 'El Ciclón', '1912-10-01', 'Club Cerro Porteño'),
(3, 'El Gumarelo', '1905-07-30', 'Club Libertad'),
(4, 'El Indio', '1903-10-12', 'Club Guaraní');

INSERT INTO jugadores (id, edad, nacionalidad, nombre, equipo_id)
VALUES
(1, 44, 'Paraguaya', 'Roque Santa Cruz', 1),
(2, 30, 'Paraguaya', 'Derlis González', 1),
(3, 31, 'Argentina', 'Facundo Zabala', 1),
(4, 21, 'Paraguaya', 'Marcos Gómez', 1),
(5, 36, 'Argentina', 'Lucas Pratto', 1),
(6, 31, 'Argentina', 'Diego Churín', 2),
(7, 26, 'Paraguaya', 'Robert Morales', 2),
(8, 20, 'Paraguaya', 'Damián Bobadilla', 2),
(9, 33, 'Paraguaya', 'Juan Iturbe', 2),
(10, 29, 'Brasileña', 'Eduardo Brock', 2);

INSERT INTO equipo_competencias (equipo_id, competencias)
VALUES
(1, 'CLAUSURA'),
(1, 'APERTURA'),
(2, 'CLAUSURA'),
(2, 'APERTURA');

INSERT INTO partidos(id, gol_local, gol_visitante, minuto_actual, minuto_adicional1t, minuto_adicional2t, status, equipo_local, equipo_visitante)
VALUES (1, 0, 0, 0, 0, 0,'EN_JUEGO', 1, 2)

