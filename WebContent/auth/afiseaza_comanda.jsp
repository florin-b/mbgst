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

#datelivrareTable tbody td, th {
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
			<h1>Afisare comanda</h1>
			<a href="#left-panel" data-theme="d" data-icon="arrow-r"
				data-iconpos="notext" data-shadow="false" data-iconshadow="false"
				class="ui-icon-nodisc">Meniu</a>
		</div>


		<div data-role="content" class="ui-content">



			<div class="ui-grid">

				<div data-role="collapsible-set" data-content-theme="d"
					id="optiuni_div">
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
										<td colspan='2'><input type="button" name="getComenzi"
											id="cautaArticol" onClick="cautaComenzi();" value="Cauta" /></td>
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

	<div id="userbean" style="visibility: hidden">${userjson}</div>
</body>
</html>