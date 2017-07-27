<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //Consiguiendo valores
    $titulo = $_POST['titulo'];
    $lugar = $_POST['lugar'];

    $raza = $_POST['raza'];
    $color = $_POST['color'];
    $tipo_masc = $_POST['tipo_masc'];

    $latitud = $_POST['latitud'];
    $longitud = $_POST['longitud'];
    $descripcion = $_POST['descripcion'];
    $id_persona = $_POST['id_persona'];

    $nombre_foto = $_POST['nombre_foto'];
    $imagen = $_POST['foto'];

    //Importar nuestro script de conexión dbregistrar_denuncia
    require_once('dbconnect.php');

//-------------Insertar Mascota ---------------------
    $sql_insertarMas="
        INSERT INTO `mascota`(
                            `raza`, 
                            `color`, 
                            `estado`, 
                            `eliminado`, 
                            `idtipo_mascota`) 
        VALUES ('$raza','$color',1,0,$tipo_masc)";
    
    mysqli_query($con, $sql_insertarMas);
    

//--------------------fin de insercción de mascota ----------

    $sql_idMaxMas = "select max(m.idmascota) as id
                    from mascota as m";

    $idMasMax = $con->query($sql_idMaxMas);

    if (mysqli_num_rows($idMasMax) > 0) {
        // output data of each row
        while ($row = mysqli_fetch_assoc($idMasMax)) {
            $id_mascota = $row["id"];
        }
    }

    //Creando una consulta sql
    $sql_insertaArt = "INSERT INTO `articulo`( 
                                        `titulo`,
                                        `lugar`, 
                                        `latitud`, 
                                        `longitud`, 
                                        `descripcion`, 
                                        `eliminado`, 
                                        `idtipo_articulo`, 
                                        `idmascota`, 
                                        `idpersona`)
             VALUES ('$titulo','$lugar','$latitud','$longitud','$descripcion',0,2,'$id_mascota','$id_persona')";
    mysqli_query($con, $sql_insertaArt);

    /*if (mysqli_query($con, $sql_insertaArt)) {
        echo 'Persona registrada correctamente';
    } else {
        echo 'No se puede agregar la persona';
    }*/

//*************** Insertar Detalle de Articulo ***********

    $sql_getIdArt="select max(a.idarticulo) as id
                            from articulo as a";

    $idArtMax = $con->query($sql_getIdArt);

    if (mysqli_num_rows($idArtMax) > 0) {
        // output data of each row
        while ($row = mysqli_fetch_assoc($idArtMax)) {
            $idArt = $row["id"];
        }
    }

    $sql_insertarDetArt="INSERT INTO `detalle_articulo`( "
            . "`imagen`, "
            . "`eliminado`,"
            . " `idarticulo`)"
            . " VALUES ('$nombre_foto',0,'$idArt')";
    
//********************************************************************

    $path = "img/$nombre_foto";
    //Ejecutando consulta en la base datos
    if (mysqli_query($con, $sql_insertarDetArt)) {
        file_put_contents($path, base64_decode($imagen));
        echo 'Adopción registrada correctamente';
    } else {
        echo 'No se puede agregar la Adopcion!';
    }

    //Closing the database 
    mysqli_close($con);
}