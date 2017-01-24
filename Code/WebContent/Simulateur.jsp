<%@ page language="java" contentType="text/html; charset=UTF-8;"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URL"%>
    
<%
URL baseUrl = new URL(request.getScheme(),request.getServerName(),request.getServerPort(),request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simulateur de pannes</title>

<!--  Leger CSS pour la page jsp. -->
<style type="text/css" > 
	body div form {
		background-color : #E0FFFF; 
		border: 1px dotted black;
		max-width : 50%;
	}

	#Lien {
		float : right;
	}
	h2 {
		font-family : Times, serif;
		font-style: normal;
		font-weight: 100;
	}
	
	h1{
		font-family : Times, serif;
		font-style: italic;
		font-weight: 100;
		font-variant: normal;
		text-align : center;
	}
</style>

</head>
<body>
	<div>
	<h1>Panne Simulator 2017</h1>
	<a href=<%= baseUrl + "/MonitoringServlet" %> id="Lien">Acces Monitoring</a>
	</div>
	<div>
		<h2>Saisissez la panne :</h2>
		
		<!-- Formulaire de selection de la machine affectee par la panne. -->
		<FORM action="SimulateurServlet" method="post" name="formulaire">
	    <LABEL for="Type de machine affectée">Type de machine affectée : </LABEL>
	              <SELECT name="Type" onchange="valeur=selectedIndex;hide();" required>
	              <OPTION selected value=""></OPTION>
	              <OPTION value = "1" id="test">Serveur</OPTION>
	              <OPTION value = "2" id="test2">Pare-feu</OPTION>
	              <OPTION value = "3" id="test3">Routeur</OPTION>
	              </SELECT><BR>
	
		<script type="text/javascript"> 
		
		//Ajout d'un EventListener pour chacun des choix possibles dans le formulaire de selection de la machine affectee.
		document.getElementById("test").addEventListener("click", hide);
		document.getElementById("test2").addEventListener("click", hide);
		document.getElementById("test3").addEventListener("click", hide);
		
		//Fonction appelee lors d'un changement dans le formulaiure ou lors d'un clic de souris.
		function hide() {	
			//Récupération des éléments que l'on veut afficher ou non sur la page web.
			var elem = document.getElementById("enlever");
			var elem2 = document.getElementById("enlever2");
			var labelf = document.getElementById("Numero1");
			var div1 = document.getElementById("Nompanne");
			
			//Modification de l'affichage selon la valeur de la variable valeur.
			if(valeur === 0){
				div1.style.visibility = 'hidden';
				div2.style.visibility = 'hidden';
			} else{
				div1.style.visibility = 'visible';
				div2.style.visibility = 'visible';
			}
			if(valeur === 1){
				elem.style.visibility = 'visible';
				elem2.style.visibility = 'visible';
				labelf.innerHTML="Numero du serveur (entre 1 et 2000) : ";
			} else {
				elem.style.visibility = 'hidden';
				elem2.style.visibility = 'hidden';
			}
			if(valeur === 2){
				labelf.innerHTML="Numero du pare-feu (entre 1 et 50) : ";
			}
			if(valeur ===3){
				labelf.innerHTML="Numero du routeur (entre 1 et 100) : ";
			}
		}
		</script>
		
		<div id="formulaire">
			<div id="Nompanne">
			
			<!-- Formulaire de selection du nom de la panne. -->
		    <LABEL for="Nom de la Panne">Nom de la panne : </LABEL>
		              <SELECT name="Nom" id="Nom" required>
		              <OPTION selected value=""></OPTION>
		              <OPTION>Reseau</OPTION>
		              <OPTION id = "enlever">Crash disque</OPTION>
		              <OPTION id = "enlever2">Probleme memoire</OPTION>
		              </SELECT><BR>
		    </div>
		    
		    <script type="text/javascript"> 
		    
		    var div2 = document.getElementById("formulaire");
			div2.style.visibility = 'hidden';
			</script>
			
		    <div>   
		    
		    <!-- Champ de saisie de l'identifiant de la machine affectee. -->
		    <LABEL for="Numero de la machine affectée" id="Numero1">Numero du serveur (entre 0001 et 2000) : </LABEL>
		    	<INPUT type="text" name="idMachine" id="Identifiant" onchange="check();" required><BR>
		    </div>
		    
		    <div>         
		    <INPUT type="submit" value="Enregistrer la panne" id="Button"> <INPUT type="reset" value="Effacer les champs">
		    </div>
		</div>
		
		<script type="text/javascript">
		
		//Fonction appelee lors d'un changement dans le champ de saisie de la machine affectee.
		//Permet la vérification de la saisie de l'utilisateur pour savoir si la machine existe.
	    function check(){
	    	var button = document.getElementById("Button");
		    var identifiant = document.getElementById("Identifiant").value;
	    	if(valeur === 1){
	    		if(identifiant>2000){
	    			confirm("Machine inexistante! Saisissez un nombre entre 1 et 2000.")
	    			button.style.visibility = 'hidden';
	    		} else {
	    			button.style.visibility = 'visible';
	    		}
	    	}
	    	if(valeur === 2){
	    		if(identifiant>50){
	    			confirm("Machine inexistante! Saisissez un nombre entre 1 et 50.")
	    			button.style.visibility = 'hidden';
	    		} else {
	    			button.style.visibility = 'visible';
	    		}
	    	}
	    	if(valeur === 3){
	    		if(identifiant>100){
	    			confirm("Machine inexistante! Saisissez un nombre entre 1 et 100.")
	    			button.style.visibility = 'hidden';
	    		} else {
	    			button.style.visibility = 'visible';
	    		}
	    	}
	    }
		</script>
			
		</FORM>
	</div>
	
	
	<div>
	<h2>Ou générez la panne aléatoirement : </h2>
	
	<FORM action="SimulateurServlet" method="post">
		    
	<INPUT type="submit" value="Générer une panne aléatoire" onclick="PanneGenerator();">
	<INPUT name="Type" id="Type2" hidden/>
	<INPUT name="Nom" id="Nom2" hidden/>
	<INPUT name="idMachine" id="idMachine2" hidden/>

	</FORM>
	</div>
	
	<div>
	<h2>Ou générez une rafale de pannes aléatoires : </h2>
	
	<FORM action="SimulateurServletRafale" method="post">
	
	<LABEL for="Nombre de pannes">Nombre de pannes à générer : </LABEL>
	<INPUT type="text" name="NombrePannes" id="NombrePannes" required><BR>
	
	<INPUT type="submit" value="Générer pannes aléatoires en rafale">
	
	</FORM>
	</div>
	
	<script type="text/javascript"> 
	
	//Fonction declenchee lors de la generation aleatoire.
	function PanneGenerator(){
		var valueType = 0; //Valeur envoyee dans la base de donnee;
		var TypeMachine = TypeAleatoire();
		var NumMachine = ComplementNom(TypeMachine);
		var TypePanne;
		if (TypeMachine === "Serveur") {
			TypePanne = TypePanneAleatoire();
			valueType = 1;
		}else{
			if(TypeMachine === "Pare-feu"){
				TypePanne = "Reseau";
				valueType = 2;
			}else{
				TypePanne = "Reseau";
				valueType = 3;
			}
		}
		if (confirm("Le "+ TypeMachine + " numero : "+ NumMachine + " a subi une panne de type " + TypePanne)) {
		    x = "Panne Enregistrée!";
		    confirm(x);
		} else {
		    x = "Panne ignorée! Veuillez générez une autre panne aléatoire ou la configurer manuellement !";
		    confirm(x);
		}
		
		//Recuperation des donnees dons un tableau :
		var res = [valueType, NumMachine, TypePanne];
		document.getElementById("Type2").value = res[0];
		document.getElementById("idMachine2").value = res[1];
		document.getElementById("Nom2").value = res[2];
	}
	
	//Retourne un type de machine aleatoirement.
	function TypeAleatoire() {	
		var TypeDeMachine = ["Serveur", "Pare-feu","Routeur"];
		var choix = (Math.floor(Math.random() * 3)) ;
		var type = TypeDeMachine[choix];
		return type;
		}
		
	//Retourne un numero de machine aleatoirement.
	function ComplementNom(TypeMachine){
		if (TypeMachine === "Serveur"){
			complement = (Math.floor(Math.random() * 1999))+1 ;
		}else if(TypeMachine === "Routeur"){
			complement = (Math.floor(Math.random() * 99))+1 ;	
		}else if(TypeMachine === "Pare-feu"){
			complement = (Math.floor(Math.random() * 49))+1 ;	
		} 
		return complement;
	}
	
	//Retourne un type de panne aleatoirement.
	function TypePanneAleatoire() {
		var TypeDePanne = ["Reseau", "Crash disque","Problème memoire"];
		var choix = (Math.floor(Math.random() * 3)) ;
		var type = TypeDePanne[choix];
		return type;
	}
	</script>
	
	</div>
</body>
</html>