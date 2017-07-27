<?php

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    //Consiguiendo valores

    require_once('dbconnect.php');
    $id = $_GET["id"];
    $sql = "UPDATE articulo as a, 
		 detalle_articulo as da,
		 mascota as m
        SET a.eliminado = '1',
            da.eliminado = '1',
            m.eliminado = '1'
        WHERE a.idarticulo = '$id' and
              da.idarticulo = '$id' and 
              a.idmascota = m.idmascota";

    if (mysqli_query($con, $sql)) {
        echo "Articulo eliminado correctamente!";
    } else {
        echo "Error! no se pudo eliminar: " . mysqli_error($conn);
    }

    mysqli_close($con);
}