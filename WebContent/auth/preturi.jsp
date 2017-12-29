<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Preturi</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


<script src="<c:url value="/resources/scripts/preturi.js" />"></script>


<style>
#listDiv {
	height: 450px;
	overflow: scroll;
}
</style>

</head>


<body>


	<div data-role="page" id="preturi" data-theme="a" data-url="">


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
			<h1>Preturi</h1>
			<a href="#left-panel" data-theme="d" data-icon="arrow-r"
				data-iconpos="notext" data-shadow="false" data-iconshadow="false"
				class="ui-icon-nodisc">Meniu</a>
		</div>





		<div data-role="content" class="ui-content">



			<form>
				<fieldset data-role="controlgroup" data-type="horizontal">

					<input type="radio" name="radio-articol" id="radio-cod" value="cod"
						checked="checked"> <label for="radio-cod">Cod
						articol</label> <input type="radio" name="radio-articol" id="radio-nume"
						value="nume"> <label for="radio-nume">Nume articol</label>
				</fieldset>
			</form>


			<div class="ui-grid-a">
				<div class="ui-block-a">
					<input id="codArticol" data-type="search">
				</div>
				<div class="ui-block-b">

					<input type="button" name="salveazaAuto" id="cautaArticol"
						onClick="cautaArticol();" value="Cauta" />

				</div>
			</div>

			<br>

			<div class="ui-grid-a">
				<div class="ui-block-a">
					<div id="listDiv">
						<ul id="list-articole" data-role="listview" data-inset="true">

						</ul>
					</div>
				</div>
				<div class="ui-block-b" id='pretResult'>

					<div>
						<div id='numeArtSel'></div>
						<br>
						<table data-role="table" id="pret-table" data-mode="reflow"
							class="ui-responsive table-stroke">
							<thead>
								<tr>
									<th data-priority="1">Pret</th>
									<th data-priority="2">Um</th>

								</tr>
							</thead>
							<tbody id='pretArticol'>
							</tbody>
						</table>

					</div>

				</div>
			</div>


		</div>
	</div>

	<div id="userbean" style="visibility: hidden">${userjson}</div>

</body>
</html>