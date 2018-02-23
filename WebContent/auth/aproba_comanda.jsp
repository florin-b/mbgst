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

	<div data-role="page" id="aprobacomanda" data-theme="a" data-url="">


		
		<div data-role="header" data-theme="a">
			<h1>Aprobare comanda</h1>
			<a href="main" data-theme="d" data-icon="arrow-l"
				data-iconpos="notext" data-shadow="false" data-iconshadow="false"
				class="ui-icon-nodisc"></a>
		</div>


		<div data-role="content" class="ui-content">

			<select name="cmd_aprob_select" id="cmd_aprob_select">

			</select> <br> <br>

			<div id="divDateGenAprob" style="display: none">
				<b> <font color="#008B00">Date generale</font></b>
				<hr>
			</div>

			<div id="antetCmdAprob">
				<table data-role="table" style="width: 100%" id="dateGenTable"
					class="ui-responsive" data-mode="reflow">
					<thead></thead>
					<tbody>
					</tbody>
				</table>
			</div>


			<br> <br>


			<div id="divArticoleAprob" style="display: none">
				<b><font color="#008B00">Articole</font></b>
				<hr>
			</div>

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
					<a href="#" id="respingeCmd" class="ui-btn ui-corner-all"
						style="background: #FFD399;">Respinge</a>
				</div>

				<div class="ui-block-b">
					<a href="#" id="aprobaCmd" class="ui-btn ui-corner-all"
						style="background: #99FFD3;">Aproba</a>
				</div>
			</div>





		</div>

	</div>



	<div data-role="dialog" id="dialogAprobare">
		<div data-role="header">
			<h1>
				<div id="tipAlertM"></div>
			</h1>
		</div>
		<div data-role="content">
			<div id="textAlertM"></div>
		</div>
	</div>


	<div id="userbean" style="visibility: hidden">${userjson}</div>

	<script src="<c:url value="/resources/scripts/aproba_comanda.js" />"></script>
</body>
</html>