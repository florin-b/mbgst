<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Afisare comanda</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>



<script src="<c:url value="/resources/scripts/afiseaza_comanda.js" />"></script>


<style>
.zebra {
	background-color: EAEAEA;
}

#listDiv {
	height: 400px;
	overflow: scroll;
}

#datelivrareTable tbody td, th {
	color: #68838B;
	font-weight: normal;
}

#articoleTable, #antetTable, #dateGenTable tbody td {
	color: #473C8B;
	font-weight: normal;
}

#articoleTable, #antetTable, #dateGenTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#articoleTable td, #antetTable, #dateGenTable  td {
	padding: 2px;
}
</style>

</head>


<body>

	<div data-role="page" id="modifcmd" data-theme="a" data-url="">


		<div data-role="panel" data-display="overlay" data-position="left"
			id="left-panel" data-theme="a">
			<ul data-role="listview">
				<jsp:include page="navbar.jsp">
					<jsp:param name="tipuser" value="${user.tipAngajat}" />
					<jsp:param name="numeuser" value="${user.nume}" />
				</jsp:include>
			</ul>
		</div>
		<div data-role="header" data-theme="a">
			<h1>Modificare comanda</h1>
			<a href="#left-panel" data-theme="d" data-icon="arrow-r"
				data-iconpos="notext" data-shadow="false" data-iconshadow="false"
				class="ui-icon-nodisc">Meniu</a>
		</div>


		<div data-role="content" class="ui-content">

			<select name="cmd_aprob_select" id="cmd_aprob_select">
			</select> <br>
			
			 <select name="modif_cmd_select" id="modif_cmd_select" >
				<option value="0">Selectati o actiune</option>
				<option value="2">Adaugare articole</option>
				<option value="3">Modificare date livrare</option>
			</select> <br>
			
			 <br> <br> <b><font color="#008B00">Date
					generale</font></b>
			<hr>

			<div id="antetCmdAprob">
				<table data-role="table" style="width: 100%" id="dateGenTable"
					class="ui-responsive" data-mode="reflow">
					<thead></thead>
					<tbody>
					</tbody>
				</table>
			</div>


			<br> <br> <b><font color="#008B00">Articole</font></b>
			<hr>


			<div id="articoleCmdAprob">
				<table data-role="table" style="width: 100%" id="articoleTable"
					class="ui-responsive" data-mode="reflow">
					<thead></thead>
					<tbody>
					</tbody>
				</table>

			</div>

			<br>

			<div class="ui-grid-a ui-responsive" id="opereazaCmdDiv"
				style="display: none;">

				<div class="ui-block-a">
					<a href="#" id="aprobaCmd" class="ui-btn ui-corner-all"
						style="background: #bbefbb;">Aproba</a>
				</div>

				<div class="ui-block-b">
					<a href="#" id="respingeCmd" class="ui-btn ui-corner-all"
						style="background: #f1c0af;">Respinge</a>
				</div>
			</div>





		</div>

		<div id="userbean" style="visibility: hidden">${userjson}</div>

		<script
			src="<c:url value="/resources/scripts/modificare_comanda.js" />"></script>
</body>
</html>