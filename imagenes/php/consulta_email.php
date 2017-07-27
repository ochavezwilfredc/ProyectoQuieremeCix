<?php

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    //Consiguiendo valores

    require_once('dbconnect.php');
    $email = $_GET["email"];
    $sql = "SELECT
	p.idpersona as id, p.email
	FROM persona p
	WHERE p.email ='$email'";

    mysqli_set_charset($con, "utf8"); //formato de datos utf8
    if (!$r = mysqli_query($con, $sql)) {
        die();
    }

    $result = array();

    while ($row = mysqli_fetch_array($r)) {
        array_push($result, array("id" => $row['id'],
            "email" => $row['email']
                )
        );
    }

    echo json_encode(array('persona' => $result));
    mysqli_close($con);
}