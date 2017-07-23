<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //Consiguiendo valores
    $id = $_POST["id"];
    $clave = $_POST["clave"];

    //Creando una consulta sql
    $sql = "UPDATE `persona` 
                SET 
                `clave`='$clave'
                WHERE `idpersona`='$id'";

    //Importing our db connection script
    require_once('dbconnect.php');

    //Ejecutando consulta en la base datos
    if (mysqli_query($con, $sql)) {
        echo 'Contraseña Actualizado Correctamente';
    } else {
        echo 'Error al Actualizar la contraseña';
    }

    //Closing the database 
    mysqli_close($con);
}
