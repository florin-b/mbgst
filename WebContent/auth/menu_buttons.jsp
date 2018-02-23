

<%@ taglib prefix="menuoptions" uri="/WEB-INF/tlds/navigator.tld"%>

<p align="center">${param.numeuser}</p>


<ul data-role="listview" data-icon="false">
	<menuoptions:navigator tipUser="${param.tipuser}">
		<li><a href="${navdetails.link}" data-ajax="false">${navdetails.text}</a></li>
	</menuoptions:navigator>
</ul>




