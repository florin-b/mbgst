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

			<select name="cmd_modif_select" id="cmd_modif_select">

			</select> <br> <select name="modif_act_select" id="modif_act_select">
				<option value="0">Selectati o actiune</option>
				<option value="1">Adaugare articole</option>
				<option value="2">Modificare date livrare</option>
			</select> <br> <br> <br>
			<div></div>


			<div id="selectArtModifDiv">
				<div class="ui-grid-a ui-responsive">
					<div class="ui-block-a">
						<form>
							<fieldset data-role="controlgroup" data-type="horizontal">

								<input type="radio" name="radio-articol" id="radio-cod"
									value="cod" checked="checked"> <label for="radio-cod">Cod
									articol</label> <input type="radio" name="radio-articol"
									id="radio-nume" value="nume"> <label for="radio-nume">Nume
									articol</label>
							</fieldset>
						</form>
					</div>
					<div class="ui-block-b" style='padding-left: 50px;'>
						<select name="select-depoz" id="select-depoz">
							<option value="V1">Vanzare 1</option>
							<option value="V2">Vanzare 2</option>
							<option value="V3">Vanzare 3</option>
							<option value="D1">Deteriorate 1</option>
							<option value="D2">Deteriorate 2</option>
							<option value="D3">Deteriorate 3</option>
						</select>
					</div>
				</div>

				<div class="ui-grid-a">
					<div class="ui-block-a" style='padding-right: 50px;'>
						<input id="codArticol" data-type="search">
					</div>
					<div class="ui-block-b" style='padding-left: 50px;'>

						<input type="button" name="cautaArticol" id="cautaArticol"
							value="Cauta" />

					</div>
				</div>

				<br>

				<div data-role="collapsible-set" data-iconpos="right"
					id="articoleset"></div>

				<br> <br>

			</div>

			<div id="selectDateLivrareModifDiv">

				<table data-role="table" data-mode="reflow" class="ui-responsive"
					id="datelivrare_modif_table">
					<thead>
						<tr>
							<td></td>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td style="width: 25%">Judet</td>
							<td><input id="numeJudet" data-type="text"></td>
						</tr>


						<tr>
							<td>Localitate</td>
							<td><input id="localitate" data-type="text"></td>
						</tr>

						<tr>
							<td>Strada</td>
							<td><input id="strada" data-type="text"></td>
						</tr>

						<tr>
							<td>Persoana de contact</td>
							<td><input id="persContact" data-type="text"></td>
						</tr>

						<tr>
							<td>Telefon</td>
							<td><input id="telPersContact" data-type="text"></td>
						</tr>

						<tr>
							<td>Tip reducere</td>
							<td><select id="selectTipReducere">
									<option value="1">1 factura (reducere in pret)</option>
									<option value="2">2 facturi</option>
									<option value="3">1 factura (reducere separat)</option>
							</select></td>
						</tr>

						<tr>
							<td>Document insotitor</td>
							<td><select id="selectDocInsotitor">
									<option value="1">Factura</option>
									<option value="2">Aviz de expeditie</option>
							</select></td>
						</tr>


						<tr>
							<td>Plata</td>
							<td><select id="selectPlata">
									<option value="B">Bilet la ordin</option>
									<option value="C">Cec</option>
									<option value="E">Plata in numerar</option>
									<option value="O">Ordin de plata</option>
							</select></td>
						</tr>

						<tr>
							<td>Responsabil incasare</td>
							<td><select id="selectResponsabil">
									<option value="AV">Agent vanzari</option>
									<option value="SO">Sofer</option>
									<option value="OF">Operator facturare</option>
							</select></td>
						</tr>

						<tr>
							<td>Transport</td>
							<td><select id="selectTransport">
									<option value="TRAP">Arabesque</option>
									<option value="TCLI">Client</option>
									<option value="TFRN">Furnizor</option>
							</select></td>
						</tr>


						<tr>
							<td>Data livrare</td>
							<td><input id="dataLivrare" type="text" readonly="readonly"
								style="position: relative; z-index: 100000;" /></td>
						</tr>

						<tr>
							<td>Observatii livrare</td>
							<td><input id="obsLivrare" data-type="text"></td>
						</tr>

						<tr>
							<td colspan=2><br> <input type="button"
								name="salveazaDateLivrare" id="salveazaDateLivrare"
								value="Salveaza date livrare" /></td>
						</tr>

					</tbody>

				</table>


			</div>







			<div id="antetCmdModif">
				<b><font color="#008B00">Date generale</font></b>
				<hr>
				<table data-role="table" style="width: 100%" id="dateGenTable"
					class="ui-responsive" data-mode="reflow">
					<thead></thead>
					<tbody>
					</tbody>
				</table>
			</div>


			<br> <br>


			<div id="articoleCmdModif">
				<b><font color="#008B00">Articole</font></b>
				<hr>
				<table data-role="table" style="width: 100%" id="articoleTable"
					class="ui-responsive" data-mode="reflow">
					<thead></thead>
					<tbody>
					</tbody>
				</table>

			</div>

			<br>


			<div id="divDateLivrareModif">
				<b><font color="#008B00">Date livrare</font></b>
				<hr>


				<div>


					<table data-role="table" data-mode="reflow" class="ui-responsive"
						id="datelivrare_table_modif">
						<thead>
							<tr>
								<td></td>
							</tr>
						</thead>

						<tbody>
							<tr id="rowJudetAfis" style="display: none">
								<td style="width: 25%">Judet</td>
								<td><div id="numeJudetAfis"></div></td>
							</tr>


							<tr id="rowLocalitateAfis" style="display: none">
								<td>Localitate</td>
								<td><div id="localitateAfis"></div></td>
							</tr>

							<tr id="rowStradaAfis" style="display: none">
								<td>Strada</td>
								<td><div id="stradaAfis"></div></td>
							</tr>

							<tr id="rowPersContactAfis" style="display: none">
								<td>Persoana de contact</td>
								<td><div id="persContactAfis"></div></td>
							</tr>

							<tr id="rowTelefonAfis" style="display: none">
								<td>Telefon</td>
								<td><div id="telPersContactAfis"></div></td>
							</tr>

							<tr id="rowTipReducereAfis" style="display: none">
								<td>Tip reducere</td>
								<td><div id="tipReducereAfis"></div></td>
							</tr>

							<tr id="rowDocInsotitorAfis" style="display: none">
								<td>Document insotitor</td>
								<td><div id="docInsotitorAfis"></div></td>
							</tr>


							<tr id="rowPlataAfis" style="display: none">
								<td>Plata</td>
								<td><div id="plataAfis"></div></td>
							</tr>

							<tr id="rowRespIncasareAfis" style="display: none">
								<td>Responsabil incasare</td>
								<td><div id="responsabilAfis"></div></td>
							</tr>

							<tr id="rowTransportAfis" style="display: none">
								<td>Transport</td>
								<td><div id="transportAfis"></div></td>
							</tr>


							<tr id="rowDataLivrareAfis" style="display: none">
								<td>Data livrare</td>
								<td><div id="dataLivrareAfis"></div></td>
							</tr>

							<tr id="rowObsLivrareAfis" style="display: none">
								<td>Observatii livrare</td>
								<td><div id="obsLivrareAfis"></div></td>
							</tr>


						</tbody>

					</table>


				</div>


			</div>


			<div class="ui-grid ui-responsive" id="opereazaCmdDiv"
				style="display: none;">


				<a href="#" id="salveazaCmdModif" class="ui-btn ui-corner-all"
					style="background: #bbefbb;">Salveaza</a>
			</div>






		</div>
	</div>

	<div id="userbean" style="visibility: hidden">${userjson}</div>

	<script
		src="<c:url value="/resources/scripts/modificare_comanda.js" />"></script>
</body>
</html>