<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    //Consiguiendo valores
    $nom_ape = $_POST['nom_ape'];
    $fn = $_POST['fecha_nac'];
    $dni = $_POST['dni'];
    $sexo = $_POST['sexo'];
    $email = $_POST['email'];
    $clave = $_POST['clave'];
    $telefono = $_POST['telefono'];
    $nombre_foto = $_POST['nombre_foto'];
    $imagen = $_POST['foto'];

    //Importar nuestro script de conexión db
    require_once('dbconnect.php');
    
    $path = "uploads/$nombre_foto";
    
    //Creando una consulta sql
    $clave_encriptada = md5($clave);
    $sql = "INSERT INTO `persona`(
                    `nom_ape`, 
                    `fecha_nac`, 
                    `dni`, 
                    `sexo`, 
                    `email`, 
                    `clave`, 
                    `telefono`, 
                    `estado`, 
                    `foto`, 
                    `eliminado`) 
             VALUES (  '$nom_ape',"
                    . "'$fn',"
                    . "'$dni',"
                    . "'$sexo',"
                    . "'$email',"
                    . "'$clave_encriptada',"
                    . "'$telefono',"
                    . "0,"
                    . "'$nombre_foto',"
                    . "0)";

    //Ejecutando consulta en la base datos
      
    if (mysqli_query($con, $sql)) {
        file_put_contents($path, base64_decode($imagen));
        echo "Usuario Registrado Correctamente";
    }

    //Closing the database 
    mysqli_close($con);
} else {
    echo "Error";
}