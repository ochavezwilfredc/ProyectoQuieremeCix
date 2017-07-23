-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2017 a las 23:25:00
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
  `idpersona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`idarticulo`, `titulo`, `fecha`, `lugar`, `latitud`, `longitud`, `descripcion`, `eliminado`, `idtipo_articulo`, `idmascota`, `idpersona`) VALUES
(1, 'Búsqueda de mi Mascota ', '2017-07-12 05:09:14', 'Derrama Magisterial Mz:P1 Lt:21', -6.753856875426512, -79.87139403820038, 'Ofrezco recomenzar  ante cualquier información comunicarse a #952234080 o 074320435 ', b'0', 1, 1, 1),
(7, 'Búsqueda de mi Mascota', '2017-07-12 08:42:21', 'Av: Argentina Nro. 110 ', NULL, NULL, 'Por favor contactarse al siguiente número 963258743', b'0', 1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `codigo_recuperacion`
--

CREATE TABLE `codigo_recuperacion` (
  `idcodigo_recuperacion` int(11) NOT NULL,
  `descripcion` varchar(6) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `eliminado` bit(1) DEFAULT NULL,
  `idpersona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `idcomentario` int(11) NOT NULL,
  `mensaje` text,
  `eliminado` bit(1) DEFAULT NULL,
  `idpersona` int(11) NOT NULL,
  `idarticulo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_articulo`
--

CREATE TABLE `detalle_articulo` (
  `iddetalle_articulo` int(11) NOT NULL,
  `imagen` varchar(100) DEFAULT NULL,
  `eliminado` bit(1) DEFAULT NULL,
  `idarticulo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_articulo`
--

INSERT INTO `detalle_articulo` (`iddetalle_articulo`, `imagen`, `eliminado`, `idarticulo`) VALUES
(1, 'mascota3.png', b'0', 1),
(3, 'mascota4.png', b'0', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota`
--

CREATE TABLE `mascota` (
  `idmascota` int(11) NOT NULL,
  `raza` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `eliminado` bit(1) DEFAULT NULL,
  `idtipo_mascota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `mascota`
--

INSERT INTO `mascota` (`idmascota`, `raza`, `color`, `estado`, `eliminado`, `idtipo_mascota`) VALUES
(1, 'Labrador', 'Blanco con manchas coloradas y negras', b'1', b'0', 1),
(2, 'Rottweiler', 'Negro con manchas coloradas', b'1', b'0', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `idpersona` int(11) NOT NULL,
  `nom_ape` varchar(100) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `sexo` enum('Masculino','Femenino') DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `clave` varchar(50) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `foto` varchar(100) DEFAULT NULL,
  `eliminado` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idpersona`, `nom_ape`, `fecha_nac`, `dni`, `sexo`, `email`, `clave`, `telefono`, `estado`, `foto`, `eliminado`) VALUES
(1, 'cristobal olano chavez', '1990-02-01', '47682522', 'Masculino', 'cristo@uss.pe', 'e10adc3949ba59abbe56e057f20f883e', '963258745', b'1', 'cristobal.png', b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_articulo`
--

CREATE TABLE `tipo_articulo` (
  `idtipo_articulo` int(11) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `eliminado` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_articulo`
--

INSERT INTO `tipo_articulo` (`idtipo_articulo`, `descripcion`, `eliminado`) VALUES
(1, 'Denuncia', b'0'),
(2, 'Adopción', b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_mascota`
--

CREATE TABLE `tipo_mascota` (
  `idtipo_mascota` int(11) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `eliminado` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_mascota`
--

INSERT INTO `tipo_mascota` (`idtipo_mascota`, `descripcion`, `eliminado`) VALUES
(1, 'Perro', b'0'),
(2, 'Gato', b'0');

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
-- Indices de la tabla `codigo_recuperacion`
--
ALTER TABLE `codigo_recuperacion`
  ADD PRIMARY KEY (`idcodigo_recuperacion`),
  ADD KEY `fk_codigo_recuperacion_persona1_idx` (`idpersona`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`idcomentario`),
  ADD KEY `fk_comentario_persona_idx` (`idpersona`),
  ADD KEY `fk_comentario_articulo1_idx` (`idarticulo`);

--
-- Indices de la tabla `detalle_articulo`
--
ALTER TABLE `detalle_articulo`
  ADD PRIMARY KEY (`iddetalle_articulo`),
  ADD KEY `fk_detalle_articulo_articulo1_idx` (`idarticulo`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`idmascota`),
  ADD KEY `fk_mascota_tipo_mascota1_idx` (`idtipo_mascota`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`idpersona`);

--
-- Indices de la tabla `tipo_articulo`
--
ALTER TABLE `tipo_articulo`
  ADD PRIMARY KEY (`idtipo_articulo`);

--
-- Indices de la tabla `tipo_mascota`
--
ALTER TABLE `tipo_mascota`
  ADD PRIMARY KEY (`idtipo_mascota`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `idarticulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `codigo_recuperacion`
--
ALTER TABLE `codigo_recuperacion`
  MODIFY `idcodigo_recuperacion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `idcomentario` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `detalle_articulo`
--
ALTER TABLE `detalle_articulo`
  MODIFY `iddetalle_articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `idmascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idpersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tipo_articulo`
--
ALTER TABLE `tipo_articulo`
  MODIFY `idtipo_articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipo_mascota`
--
ALTER TABLE `tipo_mascota`
  MODIFY `idtipo_mascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `fk_articulo_mascota1` FOREIGN KEY (`idmascota`) REFERENCES `mascota` (`idmascota`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_articulo_persona1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_articulo_tipo_articulo1` FOREIGN KEY (`idtipo_articulo`) REFERENCES `tipo_articulo` (`idtipo_articulo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `codigo_recuperacion`
--
ALTER TABLE `codigo_recuperacion`
  ADD CONSTRAINT `fk_codigo_recuperacion_persona1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `fk_comentario_articulo1` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_comentario_persona` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_articulo`
--
ALTER TABLE `detalle_articulo`
  ADD CONSTRAINT `fk_detalle_articulo_articulo1` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD CONSTRAINT `fk_mascota_tipo_mascota1` FOREIGN KEY (`idtipo_mascota`) REFERENCES `tipo_mascota` (`idtipo_mascota`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
