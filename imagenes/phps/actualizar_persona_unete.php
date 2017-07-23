<?php



if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //Consiguiendo valores
    $id = $_POST["id"];

    //Creando una consulta sql
    $sql = "UPDATE `persona` "
            . "SET `estado`='1' "
            . "WHERE idpersona ='$id'";

    //Importing our db connection script
    require_once('dbconnect.php');

    //Ejecutando consulta en la base datos
    if (mysqli_query($con, $sql)) {
        echo 'Solicitud exitosa. Pronto nos pondremos en contacto!';
    } else {
        echo 'Error de solicitud';
    }

    //Closing the database 
    mysqli_close($con);
}