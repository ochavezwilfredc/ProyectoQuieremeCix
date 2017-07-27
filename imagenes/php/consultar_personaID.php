<?php


include('functions.php');
	$id=$_GET["id"];
	
	if($resultset=getSQLResultSet(" SELECT  "
                . "`nom_ape`, "
                . "`fecha_nac`, `"
                . "dni`, "
                . "`email`, "
                . "`telefono`, "
                . "`estado`, "
                . "`foto` "
                . "FROM `persona` "
                . "WHERE `idpersona`='$id'")){
		while ($row = $resultset->fetch_array(MYSQLI_NUM)){
			echo json_encode($row);
		}
	}