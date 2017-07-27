<?php
	
	//Definiendo Constantest
	define('HOST','127.0.0.1');
	define('USER','root');
	define('PASS','');
	define('DB','quieremebd');
	
	//Connecting to Database
	$con = mysqli_connect(HOST,USER,PASS,DB) or 
        die('No se puede conectar a la BD');