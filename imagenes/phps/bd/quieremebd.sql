-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-07-2017 a las 23:31:07
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `quieremebd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `idarticulo` int(11) NOT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lugar` varchar(45) DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `descripcion` text,
  `eliminado` bit(1) DEFAULT NULL,
  `idtipo_articulo` int(11) NOT NULL,
  `idmascota` int(11) NOT NULL,
  `idpersona` int(11) NOT NULL,
  `ratingBar` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`idarticulo`, `titulo`, `fecha`, `lugar`, `latitud`, `longitud`, `descripcion`, `eliminado`, `idtipo_articulo`, `idmascota`, `idpersona`, `ratingBar`) VALUES
(1, 'Búsqueda de mi Mascota', '2017-07-12 05:09:14', 'Derrama Magisterial Mz:P1 Lt:21', -6.753856875426512, -79.87139403820038, 'Ofrezco recomenzar  ante cualquier información comunicarse a #952234080 o 074320435 ', b'0', 1, 1, 1, '1'),
(7, 'Búsqueda ', '2017-07-12 08:42:21', 'Av: Argentina Nro. 110 ', NULL, NULL, 'Por favor contactarse al siguiente número 963258743', b'0', 1, 2, 1, '3'),
(18, 'Busco a mi mascota', '2017-07-23 20:40:41', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Ofresco buena recommpenza ante cualquier consulta llame 96336963', b'0', 1, 10, 1, '2');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`idarticulo`),
  ADD KEY `fk_articulo_tipo_articulo1_idx` (`idtipo_articulo`),
  ADD KEY `fk_articulo_mascota1_idx` (`idmascota`),
  ADD KEY `fk_articulo_persona1_idx` (`idpersona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `idarticulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `fk_articulo_mascota1` FOREIGN KEY (`idmascota`) REFERENCES `mascota` (`idmascota`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_articulo_persona1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_articulo_tipo_articulo1` FOREIGN KEY (`idtipo_articulo`) REFERENCES `tipo_articulo` (`idtipo_articulo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
