-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-07-2017 a las 19:23:54
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
(1, 'Búsqueda de mi Mascota', '2017-07-12 05:09:14', 'Derrama Magisterial Mz:P1 Lt:21', -6.753856875426512, -79.87139403820038, 'Ofrezco recomenzar  ante cualquier información comunicarse a #952234080 o 074320435 ', b'0', 1, 1, 1),
(7, 'Búsqueda ', '2017-07-12 08:42:21', 'Av: Argentina Nro. 110 ', NULL, NULL, 'Por favor contactarse al siguiente número 963258743', b'0', 1, 2, 1),
(11, 'busqueda', '2017-07-18 22:23:38', 'anguia', 17, 10, 'bla bla', b'0', 1, 6, 1),
(13, 'Busco a mi mascota', '2017-07-22 17:50:56', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Ofresco buena recommpenza ante cualquier consulta llame 96336963', b'0', 1, 7, 1),
(14, 'Busco a mi mascota', '2017-07-23 15:44:15', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Ofresco buena recommpenza ante cualquier consulta llame 96336963', b'0', 1, 7, 24),
(15, 'Busco a mi mascota', '2017-07-23 16:35:41', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Ofresco buena recommpenza ante cualquier consulta llame 96336963', b'0', 1, 7, 1),
(16, 'Busco a mi mascota', '2017-07-23 16:36:56', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Ofresco buena recommpenza ante cualquier consulta llame 96336963', b'0', 1, 7, 1);

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
(3, 'mascota4.png', b'0', 7),
(5, 'mascota2.png', b'0', 11),
(7, 'Pitbull98233.png', b'0', 13),
(8, 'Pitbull109121.png', b'0', 14),
(9, 'Pitbull135105.png', b'0', 15),
(10, 'Pitbull68323.png', b'0', 16);

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
(2, 'Rottweiler', 'Negro con manchas coloradas', b'1', b'0', 1),
(6, 'bolldog', 'negro', b'1', b'0', 1),
(7, 'bolldog', 'negro', b'1', b'0', 1);

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
(1, 'Cristobal Olano Chavez', '1990-02-01', '47682522', 'Masculino', 'cristo@uss.pe', 'e10adc3949ba59abbe56e057f20f883e', '952234074', b'1', 'cristobal.png', b'0'),
(2, 'Kelvin olano chavez', '1990-02-23', '54385233', 'Masculino', 'kelvin@uss.pe', 'e10adc3949ba59abbe56e057f20f883e', '963258451', b'1', 'kelvin.png', b'0'),
(3, 'Nancy inga', '1990-02-17', '96325874', 'Femenino', 'nan@uss.pe', 'e10adc3949ba59abbe56e057f20f883e', '963258333', b'1', 'nancy.png', b'0'),
(13, 'emilia olano', '1990-02-24', '75236955', 'Femenino', 'emi@uss.pe', 'e10adc3949ba59abbe56e057f20f883e', '987456321', b'1', 'emilia.png', b'0'),
(20, 'pepe fuentes', '1990-02-25', '52696633', 'Masculino', 'pepe@uss.pe', 'e10adc3949ba59abbe56e057f20f883e', '96336963', b'0', 'pepe6101410.png', b'0'),
(21, 'cristobal  chavez', '1990-02-24', '12345678', 'Masculino', 'andy@hotmail.com', 'e10adc3949ba59abbe56e057f20f883e', '963369963', b'0', 'cristobal44366.png', b'0'),
(23, 'paisa', '1990-02-01', '96336996', 'Masculino', 'paisa@uss.pe', 'e10adc3949ba59abbe56e057f20f883e', '9636963369', b'0', 'paisa93625.png', b'0'),
(24, 'NicolÃ¡s Flores', '1994-09-10', '72178651', 'Masculino', 'ftellojaime@crece.uss.edu.pe', 'e10adc3949ba59abbe56e057f20f883e', '959545646', b'1', 'Nicolas77255.png', b'0'),
(25, 'Anthony Castro', '1997-06-26', '73320710', 'Masculino', 'giuseppe.ca@hotmail.com', 'e10adc3949ba59abbe56e057f20f883e', '970390247', b'0', 'Anthony1027107.png', b'0'),
(26, 'Jhon Adderly Perez', '1989-09-28', '45457014', 'Masculino', 'jhonperez.2909@gmail.com', 'd5f0c998a353ac575d7ac5d69108c1f9', '984896310', b'1', '45457014_109746.png', b'0');

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
(1, 'Canino', b'0'),
(2, 'Felino', b'0'),
(3, 'Otro', b'0');

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
  MODIFY `idarticulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
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
  MODIFY `iddetalle_articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `idmascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idpersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT de la tabla `tipo_articulo`
--
ALTER TABLE `tipo_articulo`
  MODIFY `idtipo_articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipo_mascota`
--
ALTER TABLE `tipo_mascota`
  MODIFY `idtipo_mascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
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
