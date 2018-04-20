<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; " pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Afisare comanda</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>



<script src="<c:url value="/resources/scripts/afiseaza_comanda.js" />"></script>

<link rel="stylesheet" href="resources/css/modificare_comanda.css">
<link rel="stylesheet" href="resources/css/creare_comanda.css">


<style>
.zebra {
	background-color: EAEAEA;
}

#listDiv {
	height: 400px;
	overflow: scroll;
}

#datelivrareTable, # tbody td, th {
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



		<div data-role="header" data-theme="a">
			<h1>Промяна поръчка</h1>
			<a href="main" data-ajax="false" data-theme="d" data-icon="arrow-l"
				data-iconpos="notext" data-shadow="true" data-iconshadow="true"
				class="ui-icon-nodisc"></a>
		</div>


		<div data-role="content" class="ui-content">



			<select name="cmd_modif_select" id="cmd_modif_select">

			</select> <br> <select name="modif_act_select" id="modif_act_select">
				<option value="0">Изберете</option>
				<option value="1">Adaugare articole</option>
				<option value="2">Modificare date livrare</option>
			</select> <br> <br> <br>



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
							<td style="width: 25%">Област</td>
							<td><input id="numeJudet" data-type="text"></td>
						</tr>


						<tr>
							<td>Населено място</td>
							<td><input id="localitate" data-type="text"></td>
						</tr>

						<tr>
							<td>Улица</td>
							<td><input id="strada" data-type="text"></td>
						</tr>

						<tr>
							<td>Лице за контакт</td>
							<td><input id="persContact" data-type="text"></td>
						</tr>

						<tr>
							<td>Tелефон</td>
							<td><input id="telPersContact" data-type="text"></td>
						</tr>

						<tr>
							<td>ВИД ОТСТЪПКА</td>
							<td><select id="selectTipReducere">
									<option value="1">ФАКТУРА (ОТСТЪПКА В ЦЕНАТА)</option>
									<option value="2">ДВЕ ФАКТУРИ</option>
									<option value="3">ЕДНА ФАКТУРА</option>
							</select></td>
						</tr>

						<tr>
							<td>Придружаващ документ</td>
							<td><select id="selectDocInsotitor">
									<option value="1">Фактура</option>
									<option value="2">Протокол</option>
							</select></td>
						</tr>


						<tr>
							<td>Плащане</td>
							<td><select id="selectPlata">
									<option value="B">Bilet la ordin</option>
									<option value="C">Cec</option>
									<option value="E">Плащане в брой</option>
									<option value="O">Платежно нареждане</option>
							</select></td>
						</tr>

						<tr>
							<td>Отговорник събиране пари</td>
							<td><select id="selectResponsabil">
									<option value="AV">Търговски агент</option>
									<option value="SO">Шофьор</option>
									<option value="OF">Оператор фактуриране</option>
							</select></td>
						</tr>

						<tr>
							<td>Транспорт</td>
							<td><select id="selectTransport">
									<option value="TRAP">Будмакс</option>
									<option value="TCLI">Клиент</option>
									<option value="TFRN">Furnizor</option>
							</select></td>
						</tr>


						<tr>
							<td>Дата доставка</td>
							<td><input id="dataLivrare" type="text" readonly="readonly"
								style="position: relative; z-index: 100000;" /></td>
						</tr>

						<tr>
							<td>Забележки доставка</td>
							<td><input id="obsLivrare" data-type="text"></td>
						</tr>

						<tr>
							<td colspan=2><br> <input type="button"
								name="saveDateLivr" id="saveDateLivr"
								value="Запази данни доставка" /></td>
						</tr>

					</tbody>

				</table>


			</div>







			<div id="antetCmdModif">
				<div id="headerDateGen"></div>

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
					class="ui-responsive " data-mode="reflow">
					<thead></thead>
					<tbody>
					</tbody>
				</table>
				<br>
				<ul id='listArticoleModif' data-role='listview' data-inset='true'></ul>


			</div>

			<br> <br>


			<div id="divDateLivrareModif">

				<div id="headerDateLivrare"></div>
				<hr>

				<table data-role="table" style="width: 100%"
					id="dateLivrareModifTable" class="ui-responsive" data-mode="reflow">
					<thead></thead>
					<tbody>
					</tbody>
				</table>




			</div>


		</div>


		<div class="ui-grid ui-responsive" id="opereazaCmdDiv"
			style="display: none;">

			<table style='width: 100%;'>
				<tr>

					<td style='width: 30%; padding: 20px;'><a href="#"
						id="stergeCmdModif" class="ui-btn ui-corner-all"
						style="background: #FFD399;">Изтрй поръчка</a></td>

					<td style='width: 50%; padding: 20px;'><a href="#"
						id="salveazaCmdModif" class="ui-btn ui-corner-all"
						style="background: #99FFD3;">Запази поръчка</a></td>
				</tr>
			</table>
		</div>





	</div>


	<div data-role="dialog" id="dialogModificare">
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

	<script
		src="<c:url value="/resources/scripts/modificare_comanda/modificare_comanda.js" />"></script>

	<script
		src="<c:url value="/resources/scripts/modificare_comanda/modificare_comanda_articole.js" />"></script>

	<script
		src="<c:url value="/resources/scripts/modificare_comanda/modificare_comanda_livrare.js" />"></script>

	<script src="<c:url value="/resources/scripts/common_scripts.js" />"></script>

	<script
		src="<c:url value="/resources/helper_scritps/helper_livrare.js" />"></script>

	<script
		src="<c:url value="/resources/common_scripts/articole_common.js" />"></script>

	<script
		src="<c:url value="/resources/helper_scritps/helper_articole.js" />"></script>



</body>
</html>