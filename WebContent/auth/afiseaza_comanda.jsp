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

<link rel="stylesheet" href="resources/css/afiseaza_comanda.css">

<script src="<c:url value="/resources/scripts/afiseaza_comanda.js" />"></script>


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

	<div data-role="page" id="afiscomanda" data-theme="a" data-url="">



		<div data-role="header" data-theme="a">
			<h1>Afisare comanda</h1>
			<a href="main" data-ajax="false" id="afisMainLink" data-theme="d" data-icon="arrow-l"
				data-iconpos="notext" data-shadow="true" data-iconshadow="true"
				class="ui-icon-nodisc"></a>
		</div>


		<div data-role="content" class="ui-content">



			<div class="ui-grid">

				<div data-role="collapsible-set" data-content-theme="d"
					id="optiuni_div" data-collapsed="true">
					<div data-role="collapsible" id="inner_optiuni_div"
						data-content-theme="c">
						<h4>Optiuni cautare</h4>

						<div>
							<table data-role="table" id="optiuni-table" data-mode="reflow"
								class="ui-body-c   ui-responsive">
								<thead>
									<tr class="ui-bar-c">
										<td></td>
										<td></td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Interval emitere</td>
										<td><select id='intervalCautare'>
												<option value="1">Astazi</option>
												<option value="2">Ultimele 7 zile</option>
												<option value="3">Ultimele 30 de zile</option>

										</select></td>
									</tr>
									<tr>
										<td>Stare comanda</td>
										<td><select id='tipComanda'>
												<option value="1">Emise</option>
												<option value="2">Respinse/sterse</option>
										</select></td>
									</tr>
									<tr>
										<td colspan='2'><input type="button"
											name="getComenziAfis" id="getComenziAfis"
											onClick="cautaComenzi();" value="Cauta" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>


			</div>

			<br>


			<div data-role="collapsible-set" data-iconpos="right" id="comenziset"></div>


		</div>
	</div>


	<div data-role="dialog" id="dialogAfisare">
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