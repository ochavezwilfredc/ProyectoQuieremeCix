-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-07-2017 a las 06:59:12
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
  `ratingBar` double(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`idarticulo`, `titulo`, `fecha`, `lugar`, `latitud`, `longitud`, `descripcion`, `eliminado`, `idtipo_articulo`, `idmascota`, `idpersona`, `ratingBar`) VALUES
(1, 'Búsqueda ', '2017-07-12 05:09:14', 'Derrama Magisterial Mz:P1 Lt:21', -6.753856875426512, -79.87139403820038, 'Ofrezco recomenzar  ante cualquier información comunicarse a #952234080 o 074320435 ', b'0', 1, 1, 1, 3.00),
(7, 'Búsqueda ', '2017-07-12 08:42:21', 'Av: Argentina Nro. 110 JLO', -6.753856875426512, -79.87139403820038, 'Por favor contactarse al siguiente número 963258743', b'0', 1, 2, 1, 1.20),
(8, 'Doy en Adopcón', '2017-07-26 08:22:00', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Busco alguien quien me cuide llamar a #963369633', b'0', 2, 11, 1, 1.70),
(10, 'Doy en Adopcón', '2017-07-26 08:35:08', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Busco alguien quien me cuide llamar a #963369633', b'0', 2, 13, 1, 1.00),
(15, 'Doy en Adopción', '2017-07-26 08:54:35', 'Los sauces', -79.87139403820038, -6.753856875426512, 'Busco alguien quien me cuide llamar a #074321677', b'0', 2, 18, 1, 3.00),
(16, 'Busco a mi mascota', '2017-07-27 00:54:48', 'Los sauces', -6.795210094984799, -79.88615021109581, 'Ofresco buena recommpenza ante cualquier consulta llame 987654321', b'0', 1, 19, 1, 2.00),
(17, 'Busco a mi mascota', '2017-07-27 02:32:49', 'Los sauces', -6.795210094984799, -79.88615021109581, 'Ofresco buena recommpenza ante cualquier consulta llame 987009789', b'0', 1, 20, 1, 4.00),
(18, 'Busco a mi gato', '2017-07-27 03:43:38', 'JLO', -6.795210094984799, -79.88615021109581, 'Ofresco buena recommpenza ante cualquier consulta llame #074420899', b'0', 1, 21, 1, 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `codigo_recuperacion`
--

CREATE TABLE `codigo_recuperacion` (
  `idcodigo_recuperacion` int(11) NOT NULL,
  `descripcion` varchar(6) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `eliminado` bit(1) DEFAULT b'0',
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
(1, 'mascota1.png', b'0', 1),
(3, 'mascota3.png', b'0', 7),
(4, 'Pitbull979110.png', b'0', 8),
(6, 'Labrador56689.png', b'0', 10),
(11, 'animal_ado74483.png', b'0', 15),
(12, 'animal_denuncia310214.png', b'0', 16),
(13, 'animal_denuncia83113.png', b'0', 17),
(14, 'animal_denuncia110189.png', b'0', 18);

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
(10, 'Pitbull', 'Colorado con masnchas blancas', b'1', b'0', 1),
(11, 'Pitbull', 'Colorado con masnchas coloradas', b'1', b'0', 1),
(12, 'Pitbull', 'Colorado con masnchas coloradas', b'1', b'0', 1),
(13, 'Labrador', 'Colorado con masnchas blancas', b'1', b'0', 1),
(14, 'pastor aleman', 'Colorado con masnchas blancas', b'1', b'0', 1),
(15, 'pastor aleman', 'Colorado con masnchas blancas', b'1', b'0', 1),
(16, 'pastor alemÃ¡n', 'Colorado con masnchas blancas', b'1', b'0', 1),
(17, 'pastor alemÃ¡n', 'Colorado con masnchas blancas', b'1', b'0', 1),
(18, 'pastor aleman', 'Colorado con masnchas blancas', b'1', b'0', 1),
(19, 'pastor aleman', 'Colorado con masnchas blancas', b'1', b'0', 1),
(20, 'comÃºn', 'Colorado con masnchas blancas', b'1', b'0', 1),
(21, 'gatito', 'Colorado con masnchas blancas', b'1', b'0', 2);

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
(1, 'Cristobal Olano Chavez', '1990-02-01', '47682522', 'Masculino', 'ochavezwilfredc@crece.uss.edu.pe', 'e10adc3949ba59abbe56e057f20f883e', '952234074', b'0', 'cristobal.png', b'0'),
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
  MODIFY `idarticulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
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
  MODIFY `iddetalle_articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `idmascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
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
  ADD CONSTRAINT `fk_codigo_recuperacion_persona1` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE CASCADE ON UPDATE CASCADE;

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
