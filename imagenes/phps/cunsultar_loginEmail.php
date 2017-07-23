<?php

include('functions.php');
$email = $_GET["email"];

if ($resultset = getSQLResultSet(" SELECT  "
        . "`idpersona`, "
        . "`nom_ape`, "
        . "`email`, "
        . "`clave`,"
        . "`estado`"
        . "FROM `persona` "
        . "WHERE email ='$email'")) {
    while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
        echo json_encode($row);
    }
}
