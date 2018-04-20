<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

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


<link rel="stylesheet" href="resources/css/creare_comanda.css">




<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<style>
.greybackcolor {
	background: yellow;
}

#pretTable, #stocTable, #clientTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#pretTable td, #pretTable th {
	padding: 8px;
}

#stocTable td, #stocTable th {
	padding: 8px;
}

#pretTable th, #stocTable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}

#datelivrare_table, #clientTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#clientTable td, #clientTable th {
	padding: 8px;
}

#datelivrare_table td, #datelivrare_table th {
	padding: 8px;
}

#datelivrare_table tr:nth-child(even) {
	background-color: #f2f2f2;
}

#datelivrare_table th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}

hr {
	border-top: 1px solid #008B00;
}
</style>

</head>


<body>

	<div data-role="page" id="crearecomanda" data-theme="a" data-url="">



		<div data-role="header" data-theme="a">
			<h1>Създаване поръчка</h1>
			<a href="main" data-ajax="false" data-theme="d" data-icon="arrow-l"
				data-iconpos="notext" data-shadow="true" data-iconshadow="true"
				class="ui-icon-nodisc"></a>
		</div>


		<div data-role="content" class="ui-content">

			<select name="creare_comanda_select" id="creare_comanda_select">
				<option value="0">Изберете</option>
				<option value="1">Клиент</option>
				<option value="2">Артикули</option>
				<option value="3">Дата доставка</option>
			</select> <br>

			<div id='selectClientDiv' style="display: none">

				<div class="ui-grid-a ui-responsive" id='divCautaClient'>
					<div class="ui-block-a" style='padding-right: 50px;'>
						<input id="numeClient" data-type="search" value="ДЕМАТ ООД">
					</div>

					<div class="ui-block-b" style='padding-left: 50px;'>
						<input type="button" name=cautaClient id="cautaClient"
							value="Търси клиент" />
					</div>
				</div>

				<br>
				<div data-role="collapsible-set" data-iconpos="right"
					id="clientiset"></div>
			</div>


			<div id="selectArticoleDiv" style="display: none">
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


			<br>


			<div id="selectDateLivrareDiv" style="display: none">
				<table data-role="table" data-mode="reflow" class="ui-responsive"
					id="datelivrare_table">
					<thead>
						<tr>
							<td></td>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td style="width: 25%">Област</td>
							<td><select id='codJudet'>
									<option value='00'>Област</option>
									<option value='01'>БЛАГОЕВГРАДСКА</option>
									<option value='02'>БУРГАСКА</option>
									<option value='03'>ДОБРИЧСКА</option>
									<option value='04'>ГАБРОВСКА</option>
									<option value='05'>ХАСКОВСКА</option>
									<option value='06'>КЪРДЖАЛИЙСКА</option>
									<option value='07'>КЮСТЕНДИЛСКА</option>
									<option value='08'>ЛОВЕШКА</option>
									<option value='09'>МОНТАНАНСКА</option>
									<option value='10'>ПАЗАРДЖИШКА</option>
									<option value='11'>ПЕРНИШКА</option>
									<option value='12'>ПЛЕВЕНСКА</option>
									<option value='13'>ПЛОВДИВСКА</option>
									<option value='14'>РАЗГРАДСКА</option>
									<option value='15'>РУСЕНСКА</option>
									<option value='16'>ШУМЕНСКА</option>
									<option value='17'>СИЛИСТРЕНСКА</option>
									<option value='18'>СЛИВЕНСКА</option>
									<option value='19'>СМОЛЯНСКА</option>
									<option value='20'>СОФИЯ</option>
									<option value='21'>СОФИЙСКА</option>
									<option value='22'>СТАРОЗАГОРСКА</option>
									<option value='23'>ТЪРГОВИШКА</option>
									<option value='24'>ВАРНЕНСКА</option>
									<option value='25'>ВЕЛИКОТЪРНОВСКА</option>
									<option value='26'>ВИДИНСКА</option>
									<option value='27'>ВРАЧАНСКА</option>
									<option value='28'>ЯМБОЛСКА</option>
							</select></td>

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

									<option value="E">Плащане в брой</option>
									<option value="O">Платежно нареждане</option>
									<option value="Z">Плащане при доставка</option>
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
									<option value="TCLI">Клиент</option>
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
								name="salveazaDateLivrare" id="salveazaDateLivrare"
								value="Запази данни доставка" /></td>
						</tr>

					</tbody>

				</table>


			</div>


			<br> <br>
			<div id="divClient" style="display: none">
				<b><font color="#008B00">Client</font></b>
				<hr>

				<div id='clientComanda'></div>
			</div>

			<br> <br>
			<div id="divArticole" style="display: none">
				<b><font color="#008B00">Артикули</font></b>
				<hr>

				<ul id='listArticoleCreare' data-role='listview' data-inset='true'></ul>

				<br>
				<div style="display: inline; float: right;" id='divTotalCmd'>
					<b>0.00 RON</b>
				</div>

				<div style="display: inline; float: right; padding-right: 10px">
					<b>Total comanda </b>
				</div>

			</div>

			<br> <br>

			<div id="divDateLivrare" style="display: none">
				<b><font color="#008B00">Дата доставка</font></b>
				<hr>


				<div>
					<table data-role="table" data-mode="reflow" class="ui-responsive"
						id="datelivrare_table_afis">
						<thead>
							<tr>
								<td></td>
							</tr>
						</thead>

						<tbody>
							<tr id="rowJudetAfis" style="display: none">
								<th style="width: 25%">Област</th>
								<td><div id="numeJudetAfis"></div></td>
							</tr>


							<tr id="rowLocalitateAfis" style="display: none">
								<th>Населено място</th>
								<td><div id="localitateAfis"></div></td>
							</tr>

							<tr id="rowStradaAfis" style="display: none">
								<th>Улица</th>
								<td><div id="stradaAfis"></div></td>
							</tr>

							<tr id="rowPersContactAfis" style="display: none">
								<th>Лице за контакт</th>
								<td><div id="persContactAfis"></div></td>
							</tr>

							<tr id="rowTelefonAfis" style="display: none">
								<th>Tелефон</th>
								<td><div id="telPersContactAfis"></div></td>
							</tr>

							<tr id="rowTipReducereAfis" style="display: none">
								<th>ВИД ОТСТЪПКА</th>
								<td><div id="tipReducereAfis"></div></td>
							</tr>

							<tr id="rowDocInsotitorAfis" style="display: none">
								<th>Придружаващ документ</th>
								<td><div id="docInsotitorAfis"></div></td>
							</tr>


							<tr id="rowPlataAfis" style="display: none">
								<th>Плащане</th>
								<td><div id="plataAfis"></div></td>
							</tr>

							<tr id="rowRespIncasareAfis" style="display: none">
								<th>Отговорник събиране пари</th>
								<td><div id="responsabilAfis"></div></td>
							</tr>

							<tr id="rowTransportAfis" style="display: none">
								<th>Транспорт</th>
								<td><div id="transportAfis"></div></td>
							</tr>


							<tr id="rowDataLivrareAfis" style="display: none">
								<th>Дата доставка</th>
								<td><div id="dataLivrareAfis"></div></td>
							</tr>

							<tr id="rowObsLivrareAfis" style="display: none">
								<th>Забележки доставка</th>
								<td><div id="obsLivrareAfis"></div></td>
							</tr>




						</tbody>

					</table>


				</div>

			</div>


			<br>
			<div id='divSalveazaComanda' style="display: none">

				<a href="#" id="salveazaComanda" class="ui-btn ui-corner-all"
					style="background: #bbefbb;">Изтрй поръчка</a>

			</div>





		</div>
	</div>


	<div data-role="dialog" id="dialogCreare">
		<div data-role="header">
			<h1>
				<div id="tipAlertC"></div>
			</h1>
		</div>
		<div data-role="content">
			<div id="textAlertC"></div>
		</div>
	</div>



	<div id="userbean" style="visibility: hidden">${userjson}</div>

	<script
		src="<c:url value="/resources/scripts/creare_comanda/creeaza_comanda.js" />"></script>

	<script
		src="<c:url value="/resources/scripts/creare_comanda/creeaza_comanda_articole.js" />"></script>

	<script
		src="<c:url value="/resources/scripts/creare_comanda/creeaza_comanda_livrare.js" />"></script>

	<script
		src="<c:url value="/resources/scripts/creare_comanda/creeaza_comanda_date.js" />"></script>

	<script
		src="<c:url value="/resources/scripts/creare_comanda/creeaza_comanda_commons.js" />"></script>

	<script
		src="<c:url value="/resources/helper_scritps/helper_articole.js" />"></script>





</body>
</html>