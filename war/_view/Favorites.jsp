<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Favorites </title>
<style type = "text/css">

#PageName {
	color: blue;
	font-size: 200%;
	text-align: center;
	background-color: white;
	font-variant: small-caps;
}

.Address {
	font-size: 15px;
	font-color: black;
}
#Factions {
	float: left;
	border: 1px solid darkblue;
	width: 400px;
	margin-left: 13px;
	margin-top: 13px;
	margin-bottom: 13px;
}

#FactionName {
	color: darkblue;
	font-size: 150%;
	border-bottom: 2px solid darkblue;
	width: 300px;
	margin-left: 13px;
	margin_bottom: 20px;
}

#Infantry {
	float: left;
	border: 1px solid darkblue;
	width: 400px;
	margin-left: 13px;
	margin-top: 13px;
	margin-bottom: 13px;
}

#InfantryName {
	color: darkgreen;
	font-size: 150%;
	border-bottom: 2px solid darkgreen;
	width: 300px;
	margin-left: 13px;
	margin_bottom: 20px;
}

#Content {
	float: left;
}
button {
	margin-top: 3px;
	margin-left: 5px;
	margin-bottom: 5px;
}
div.fixed {
	position: fixed;
	bottom: 10px;
	right: 10px;
}

button {
	margin-top: 3px;
	margin-left: 5px;
	margin-bottom: 5px;
}

</style>
</head>

<body>
		<div id="PageName">Favorites Page</div>
	<div id = "Content">
		<div id = "Factions">
			<c:forEach items = "${faction}" var = "faction">
				<div id = "Factions">
					<div id = "faction_name">${ faction.faction_name }</div>
					<table>
						<tr>
							<form action="${pageContext.servletContext.contextPath}/Faction" method="post">
								<input type="hidden" name="restaurant" id="restaurant" value="${restaurant.name}">
								<td><input type="submit" value="Click to view restaurant"></td>
							</form>
						</tr>
					</table>
				</div>
				<br>
			</c:forEach>
			
			<form action="${pageContext.servletContext.contextPath}/Homepage" method="get">
					<input type="Submit" name="submit" value="Click to go to Homepage"/>
			</form>
		</div>
	</div>	
	
		<div class="fixed">
			<button>
				<a href="/lab03/Login">Logout</a>
			</button>
		</div>
</body>
</html>