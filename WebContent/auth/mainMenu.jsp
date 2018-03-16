<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; " pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Meniu principal</title>


<meta name="viewport" content="width=device-width, initial-scale=1">



<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<script src="<c:url value="/resources/scripts/main_menu.js" />"></script>



</head>
<body>
	<div data-role="page" id="main_menu" data-theme="a" data-url="">

		<div data-role="header" data-theme="a">
			<h1>Meniu</h1>
		</div>

		<div data-role="content" class="ui-content">




			<jsp:include page="menu_buttons.jsp">
				<jsp:param name="tipuser" value="${user.tipAngajat}" />
				<jsp:param name="numeuser" value="${user.nume}" />
			</jsp:include>



		</div>




	</div>

	<div id="userbean" style="visibility: hidden">${userjson}</div>

</body>
</html>
