<?php

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    //Consiguiendo valores
    $id = $_GET["id"];
    
    require_once('dbconnect.php');
$sql = "SELECT  a.idarticulo as id, 
                a.titulo, 
                a.fecha,
                a.latitud,
                a.longitud, 
                a.descripcion,
                tm.descripcion as tipo_mas,
                m.raza,
                m.color,
                a.lugar,
                CONCAT('/img/',da.imagen) as imagen,
                p.nom_ape
        FROM persona as p INNER JOIN articulo AS a ON p.idpersona= a.idpersona
                 INNER JOIN mascota AS m ON m.idmascota = a.idmascota 
                 INNER JOIN tipo_mascota AS tm ON tm.idtipo_mascota = m.idtipo_mascota 
                 INNER JOIN detalle_articulo AS da ON da.idarticulo = a.idarticulo
                 INNER JOIN tipo_articulo AS ta  ON ta.idtipo_articulo = a.idtipo_articulo
        WHERE ta.descripcion = 'Denuncia' AND a.idarticulo ='$id'
";

mysqli_set_charset($con, "utf8"); //formato de datos utf8
if (!$r = mysqli_query($con, $sql)) {
    die();
}

$result = array();

while ($row = mysqli_fetch_array($r)) {
    array_push($result, 
            array(  "id" => $row['id'], 
                    "titulo" => $row['titulo'],
                    "fecha" => $row['fecha'],
                    "latitud" => $row['latitud'],
                    "longitud" => $row['longitud'],
                    "descripcion" => $row['descripcion'],
                    "tipo_mas" => $row['tipo_mas'],
                    "raza" => $row['raza'],
                    "color" => $row['color'],
                    "lugar" => $row['lugar'],
                    "imagen" => $row['imagen'],
                    "nom_ape" => $row['nom_ape'],
                )
            );
}

echo json_encode(array('denuncia' => $result));
mysqli_close($con);

    
    
}