<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Test</p>

	<input type="button" value="Rafraichir" id="refresh" />

	<div id="test"></div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script>
		function reload() {
			location.reload();
		}
		
		function putPanne() {
			var idMachine = $(this).find('idMachine').text();
			var nomPanne = $(this).find('nomPanne').text();
			var typeMachine = $(this).find('typeMachine').text();
			var date = $(this).find('date').text();
			
			$('#test').append("Panne("+idMachine+","+nomPanne+","+typeMachine+","+date+")<br/>");
		}

		$('#refresh').on('click', reload); //niveau 1

		setTimeout(reload, 5000); //niveau 2

		$.ajax({
			type : "POST",
			url : "http://localhost:8080/PanneSimulator2017/AjaxServlet",
			dataType : "xml"
		}).done(function(data) {
			console.log(data);
			$(data).find('panne').each(putPanne);
			//$('#test').append(data);
		});
		//niveau 3 

		if (!window.WebSocket)
			throw "Impossible d'utiliser WebSocket. Votre navigateur ne connait pas cette classe.";

		var ws = new WebSocket(
				"ws://localhost:8080/PanneSimulator2017/socketPanne");
		ws.onopen = function(evt) {
			onOpen(evt)
		};
		ws.onmessage = function(evt) {
			onMessage(evt)
		};

		function onOpen(evt) {
			console.log("CONNECTE");
		}

		function onMessage(evt) {
			console.log("RECU : " + evt.data);
		}

		setInterval(function() {
			ws.send("Pouet");
		}, 1000);
	</script>



</body>
</html>