<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="java.util.List,monitoring.Panne,java.net.URL,java.net.URI"%>
<% 
//R�cup�ration des variables utilisees pour connaitre le nombre de pannes.
int nbPannesMin = (Integer) request.getAttribute("nbPannesMin");
int nbPannesHeure = (Integer) request.getAttribute("nbPannesHeure");
int nbPannesJour = (Integer) request.getAttribute("nbPannesJour");
int nbPannesMois = (Integer) request.getAttribute("nbPannesMois");
List<Panne> listePannes = (List<Panne>)request.getAttribute("listePannes");
URL baseUrl = new URL(request.getScheme(),request.getServerName(),request.getServerPort(),request.getContextPath());
URI wsBaseUrl = new URI("ws",null,request.getServerName(),request.getServerPort(),request.getContextPath(),null,null);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--  Leger CSS pour la page jsp. -->
<style type="text/css">
body ul {
	background-color: #E0FFFF;
	border: 1px dotted black;
}

body ul li {
	list-style-type: square;
	font-style: normal;
	font-weight: 100;
}

body div {
	text-align: right;
}

h2 {
	font-family: Times, serif;
	font-style: normal;
	font-weight: 100;
}

h1 {
	font-family: Times, serif;
	font-style: italic;
	font-weight: 100;
	font-variant: normal;
	text-align: center;
}
</style>

<title>Console de Monitoring</title>
</head>
<body>
	<h1>Liste des pannes</h1>
	<div>
		<a href=<%= baseUrl + "/SimulateurServlet" %>>Acces Simulateur</a>
	</div>
	<input type="button" value="Rafraichir" id="refresh" />
	<h2>Pannes ayant eu lieu au cours :</h2>
	<ul>
		<li>De la derniere minute : <%=nbPannesMin%> <a
			href='<%= baseUrl+"/MonitoringServlet?type=minute" %>'>Détails</a></li>
		<BR>
		<li>De la derniere heure : <%=nbPannesHeure%> <a
			href='<%= baseUrl+"/MonitoringServlet?type=heure" %>'>Détails</a></li>
		<BR>
		<li>Du dernier jour : <%=nbPannesJour%> <a
			href='<%= baseUrl+"/MonitoringServlet?type=jour" %>'>Détails</a></li>
		<BR>
		<li>Du dernier mois : <%=nbPannesMois%> <a
			href='<%= baseUrl+"/MonitoringServlet?type=mois" %>'>Détails</a></li>
		<BR>
		<li><a href='<%= baseUrl+"/MonitoringServlet" %>'>Tout
				afficher</a></li>
	</ul>

	<%
		String URLdetail =  baseUrl+"/MonitoringServlet";
		if(request.getQueryString()!=null){
			if(request.getQueryString().contains("minute")){
				URLdetail += "?type=minute&";
			}else if(request.getQueryString().contains("heure")){
				URLdetail += "?type=heure&";				
			}else if(request.getQueryString().contains("jour")){
				URLdetail += "?type=jour&";				
			}else if(request.getQueryString().contains("mois")){
				URLdetail += "?type=mois&";				
			} else {
				URLdetail += "?";
			}
		} else {
			URLdetail += "?";
		}
	%>

	<p>Détails Pannes : <% out.print(baseUrl+"/MonitoringServlet"+ "?" + request.getQueryString()); %></p>
	<a href='<% out.print(URLdetail + "typeMachine=serveur"); %>'>Détails par serveur</a>
	<a href='<% out.print(URLdetail + "typeMachine=routeur"); %>'>Détails par routeur</a>
	<a href='<% out.print(URLdetail + "typeMachine=Pare-feu"); %>'>Détails par pare-feu</a>
	<a href='<% out.print(URLdetail); %>'>Détails toutes machines</a>
	<%
	String table = new String();
	
		table = "<table border=1 id='liste'>";
		table += "<tr><th>ID machine</th><th>Type machine</th><th>Nom Panne</th><th>Date</th><th>Résolue ?</th></tr>";
		for(Panne p : listePannes){
			table += "<tr class='panne'><td>"+p.getIdMachine()+"</td>";
			table += "<td>"+p.getTypeMachine()+"</td>";
			table += "<td>"+p.getName()+"</td>";
			table += "<td>"+p.getDate()+"</td>";
			table += "<td><input type=checkbox";
			if(p.isResolue()){
				table += " checked";
			}
			table +=" value="+p.getIdPanne()+"></input></td>";
			table += "</tr>";
		}
		table += "</table>";
	%>
	<%=table %>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script>
		//Fonction pour récupérer les paramêtres GET de l'URL
		$.urlParam = function(name){
			var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(location.href);
			if(results==null){
				return null;
			} else {
				return results[1] || 0;
			}
		}
		
		//Fonction pour recharger la page
		function reload() {
			location.reload();
		}
		
		//Requête AJAX pour récupérer les pannes
		function getPannes(){
			$.ajax({
				type : "POST",
				url : "<%= baseUrl+"/AjaxServlet" %>",
				data : {type: $.urlParam('type'),requestType: "getPannes",typeMachine: $.urlParam('typeMachine')},
				dataType : "xml"
			}).done(function(data) {
				console.log(data);
				//On vide la liste avant de rajouter chaque panne dedans
				$('.panne').remove();
				$(data).find('panne').each(putPanne);
			});
		}
		
		//Requête AJAX pour résoudre les pannes
		function setChecked(event){
			$.ajax({
				type : "POST",
				url : "<%= baseUrl+"/AjaxServlet" %>",
				data : {idPanne: event.target.value,requestType: "setChecked",checked: event.target.checked}			}).done(function(data) {
				console.log("fini");
			});
		}
		
		//Fonction pour afficher les pannes dans le DOM
		function putPanne() {
			var idMachine = $(this).find('idMachine').text();
			var nomPanne = $(this).find('nomPanne').text();
			var typeMachine = $(this).find('typeMachine').text();
			var date = $(this).find('date').text();
			var resolue = $(this).find('resolue').text();
			
			var string = "<tr class='panne'><td>"+idMachine+"</td><td>"+typeMachine+"</td><td>"+nomPanne+"</td><td>"+date+"</td><td><input type=checkbox";
			if(resolue=="true"){
				console.log(resolue);
				string += " checked";
			}
			string += "></input></td></tr>";
			
			$('#liste').append(string);
		}

		//Event onClick pour le bouton refresh
		$('#refresh').on('click', reload); //niveau 1 -- Bouton #refresh 
		//Event onClick pour les checkbox des pannes
		$('.panne > td > input').on('click',setChecked);
		
		setTimeout(reload, 300000); //niveau 2 -- Rechargement de la page toutes les 5 minutes

		//niveau 3 -- Chargement de la requête AJAX toutes les minutes
		setInterval(getPannes,60000);

		if (!window.WebSocket) //niveau 4 -- Websocket entre le client et le serveur à l'adresse socketPanne
			throw "Impossible d'utiliser WebSocket. Votre navigateur ne connait pas cette classe.";

		var ws = new WebSocket(
				"<%= wsBaseUrl+"/socketPanne" %>");
		ws.onopen = function(evt) {
			onOpen(evt)
		};
		ws.onmessage = function(evt) {
			onMessage(evt)
		};

		function onOpen(evt) {
			console.log("CONNECTE");
		}

		//On update les pannes lorsque l'on reçoit un message du serveur
		function onMessage(evt) {
			console.log("RECU : " + evt.data);
			getPannes();
		}
	</script>
</body>