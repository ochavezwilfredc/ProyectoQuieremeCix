<?php

include('functions.php');
	$email=$_GET["email"];
	
	if($resultset=getSQLResultSet(" SELECT  "
                . "`nom_ape`, "
                . "`fecha_nac`, `"
                . "dni`, "
                . "`email`, "
                . "`telefono`, "
                . "`estado`, "
                . "`foto` "
                . "FROM `persona` "
                . "WHERE email ='$email'")){
		while ($row = $resultset->fetch_array(MYSQLI_NUM)){
			echo json_encode($row);
		}
	}
