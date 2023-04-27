<?php

$wsdl = 'http://localhost:8585/ArchiveWs?wsdl';
$clientSoap = new SoapClient($wsdl);


$username = $_POST['username'];
$password =$_POST['password'];

$param = new stdClass();
$param->login = $username;
$param->password = $password;

$resultat = $clientSoap->__soapCall("connexion",array($param));
$rest = $clientSoap->__soapCall("listeEtudiant",array());


if(!(isset($resultat)) && !(isset($rest))){
    echo "login ou mot de passe incorrect !";
}else
    echo "les informations d'un client connecté "."</br>";
    var_dump($resultat); 
    echo "Nom :  ".$resultat->return->nomEtudiant."</br>";
    echo "Login : ".$resultat->return->login."</br>";
    echo "password ".$resultat->return->password."</br></br>";


    // foreach ($rest as $key => $values) {
    //     foreach ($values as $keys => $value) {
    //         echo "</br>".$keys.". ".$value->nomEtudiant;
    //     }
    // }
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-5.0.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap-5.0.2/dist/css/bootstrap.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <div class="container">
            <h1>Liste des utilisateur dans la base de données</h1>
            <div class="table-responsive">
                <div>
                    <table class="table table-bordered" id="" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>nom etudiant</th>
                                <th>Login</th>
                                <th>Date de naissance</th>
                                <th>Password</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php foreach ($rest as $key => $values) {
                                    foreach ($values as $keys => $value) { ?>
                                <tr>    
                                    <td><?= $value->idEtudiant ?></td>
                                    <td><?= $value->nomEtudiant ?></td>
                                    <td><?= $value->login ?></td>
                                    <td><?= $value->dateNaissance ?></td>
                                    <td><?= $value->password ?></td>
                                    <td>
                                        <a href="" class="btn btn-primary">detail</a>
                                    </td>
                                </tr>
                            <?php }}?>
                        </tbody>

                    </table>
                </div>
            </div>        
        </div>
    </div>
    
</body>
</html>




