<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //Consiguiendo valores
    $id = $_POST["id"];
    $nom_ape = $_POST["nom_ape"];
    $telefono = $_POST["telefono"];
    $correo = $_POST["email"];
    
    //Creando una consulta sql
    $sql = "UPDATE `persona`
            SET
                `nom_ape`='$nom_ape',
                `email`='$correo',
                `telefono`='$telefono'
            WHERE `idpersona` ='$id'";

    //Importing our db connection script
    require_once('dbconnect.php');

    //Ejecutando consulta en la base datos
    if (mysqli_query($con, $sql)) {
        echo 'Perfil Actualizado Correctamente';
    } else {
        echo 'Error al Actualizar el perfil';
    }

    //Closing the database 
    mysqli_close($con);
}
