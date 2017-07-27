<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //Consiguiendo valores
    $id = $_POST["id"];
    $email = $_POST["email"];
    $codigo = $_POST["codigo"];

    require_once('dbconnect.php');
    


    $para = "$email";
    $titulo = "Recuperación de Cuenta";
    $mensaje = "Codigo de Verificación: " + $codigo;
    
    $bool = mail($para, $titulo, $mensaje);
    if ($bool) {
        echo "Mensaje enviado";
        $sql = "INSERT INTO `codigo_recuperacion`(
                    `descripcion`,
                    `idpersona`)
                VALUES ($codigo,$id)";

        mysqli_query($con, $sql);
        //Closing the database 
        mysqli_close($con);
    } else {
        echo "Mensaje no enviado";
    }
}




