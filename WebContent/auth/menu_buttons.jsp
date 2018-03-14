


<%@ taglib prefix="menuoptions" uri="/WEB-INF/tlds/navigator.tld"%>


<style>
ul.twocolumns {
	display: inline-block;
}

ul.twocolumns li {
	float: left;
	list-style: outside none none;
	width: 50%;
}
</style>



<p align="center">${param.numeuser}</p>
<br>

<ul data-role="listview" data-icon="false" id="listMenu">
	<menuoptions:navigator tipUser="${param.tipuser}">
		<li><a href="${navdetails.link}" data-ajax="false">${navdetails.text}</a></li>
	</menuoptions:navigator>
</ul>




