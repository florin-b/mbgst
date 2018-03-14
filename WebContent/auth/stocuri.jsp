<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stocuri</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


<link rel="stylesheet" href="resources/css/stocuri.css">


<script src="<c:url value="/resources/scripts/stocuri.js" />"></script>


<style>
#listDiv {
	height: 450px;
	overflow: scroll;
}

#stocTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#stocTable td {
	padding: 2px;
}
</style>

</head>


<body>


	<div data-role="page" id="stocuri" data-theme="a" data-url="">


		
		<div data-role="header" data-theme="a">
			<h1>Stocuri</h1>
			<a href="main" data-ajax="false" data-theme="d" data-icon="arrow-l"
				data-iconpos="notext" data-shadow="true" data-iconshadow="true"
				class="ui-icon-nodisc"></a>
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

			<div data-role="collapsible-set" data-iconpos="right"
				id="articoleset"></div>





		</div>
	</div>

	<div id="userbean" style="visibility: hidden">${userjson}</div>

</body>
</html>