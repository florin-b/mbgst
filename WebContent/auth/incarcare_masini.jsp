<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Incarcare masini</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<link rel="stylesheet" href="resources/css/afiseaza_comanda.css">

<script src="<c:url value="/resources/scripts/incarcare_masini.js" />"></script>



<style>
#listDiv {
	height: 400px;
	overflow: scroll;
}

#datelivrareTable, #dategeneraleTable tbody td, th {
	color: #68838B;
	font-weight: normal;
}

#comenziTable tbody td {
	color: #473C8B;
	font-weight: normal;
}

#articoleTable, #comenziTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#articoleTable td, #comenziTable  td {
	padding: 2px;
}
</style>

</head>


<body>

	<div data-role="page" id="incarc_masini" data-theme="a" data-url="">



		<div data-role="header" data-theme="a">
			<h1>Incarcare masini</h1>
			<a href="main" data-ajax="false" id="afisMainLink" data-theme="d"
				data-icon="arrow-l" data-iconpos="notext" data-shadow="true"
				data-iconshadow="true" class="ui-icon-nodisc"></a>
		</div>


		<div data-role="content" class="ui-content">

			<br><br>

			<table style="width: 100%;">
				<tr>
					<td style="width: 70%;"><h3 id='labelMasini'></h3></td>
					<td><a href="#" id="refreshList" class="ui-btn ui-corner-all"
						style="background: #DCDDFD;">Actualizeaza</a></td>
				</tr>
			</table>

			<br> <br> <select name="masini_select"
				id="masini_select">

			</select> <br> <br> <a href="#" id="sfIncarcare"
				class="ui-btn ui-corner-all"
				style="background: #99FFD3; display: none">Sfarsit incarcare</a>

		</div>
	</div>


	<div data-role="dialog" id="dialogIncarcare">
		<div data-role="header">
			<h1 id="tipAlertA"></h1>
		</div>
		<div data-role="content">
			<div id="textAlertA"></div>
		</div>
	</div>

	<div id="userbean" style="visibility: hidden">${userjson}</div>


	<script
		src="<c:url value="/resources/helper_scritps/helper_livrare.js" />"></script>

</body>
</html>