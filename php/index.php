<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
</head>

    <body id = "formu">
        <!--  formulaire de connexion  -->
        <div id="formere">
        </div> 
        <div id="former">
            <form action= "traitement.php" method= "post" id = "form">
                <h2>Connexion</h2>
                <label for= "username">Nom d’utilisateur :</label>
                <input type= "text" id= "username" name= "username" placeholder= "Entrez votre nom d’utilisateur" required>
                <label for= "password">Mot de passe :</label>
                <input type= "password" id= "password" name= "password"placeholder="Entrez votre mot de passe"required>
                <input type= "submit" value= "Se connecter">
                <div>
                    <input type="checkbox" id="id_pass" name="rd_femme" value="MtpOublier" onclick="changer()"><br>
                    <label for="id_pass">Afficher le mot de passe</label>
                </div>
            </form>
        </div>  

        <script>
            e = true;
            function changer() {
                if (e)
                {
                    document.getElementById("password").setAttribute("type", "text");
                    e = false;
                } else {

                    document.getElementById("password").setAttribute("type", "password");
                    e = true;
                }
            }
        </script>
        
    </body>
</html>