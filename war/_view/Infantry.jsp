<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>${infantry}</title>
<style type = "text/css"> 

#PageName {
	color: white;
	font-size: 250%;
	text-align: center;
	background-color: darkblue;
	font-variant: small-caps;
}

#LinkContent {
	float: left;
	border: 1px solid darkblue;
	width: 400px;
	margin-left: 13px;
	margin-top: 13px;
	margin-bottom: 13px;
}

#LinkName {
	color: darkblue;
	font-size: 150%;
	border-bottom: 2px solid darkblue;
	width: 350px;
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
	
	<c:if test="${! empty errorMessage}">
		<div class="error">${errorMessage}</div>
	</c:if>

		<div id="PageName">${infantry}</div>
		
	
	<div id="Content">
		<div id="LinkContent">
			<div id="LinkName">Home</div>
			<form action="${pageContext.servletContext.contextPath}/Homepage"
				method="get">
				<table>
					<tr>
						<td><input type="Submit" name="submit"
							value="Click to go to Homepage" /></td>
					</tr>
				</table>
			</form>
		</div>

		<br>
		
		
		<div id="LinkContent">
			<div id="LinkName">Faction</div>
			<form action="${pageContext.servletContext.contextPath}/Faction"
				method="get">
				<table>
					<tr>
						<td><input type="Submit" name="submit"
							value="Click to view their faction" /></td>
					</tr>
				</table>
			</form>
		</div>
		
		
		<c:if test="${ empty utype}">
		<br>
		<div id="LinkContent">
			<div id="LinkName">Add ${infantry} to Favorites</div>
			<form action="${pageContext.servletContext.contextPath}/Favorites" method="get">
				<table>
					<tr>
						<input type="hidden" name="infantry" id="infantry" value="${infantry}">
						<td><input type="Submit" name="submit" value="Click to add to favorites" /></td>
					</tr>
				</table>
			</form>
		</div>
		</c:if>
		
		<div class="fixed">
			<button>
				<a href="/lab03/Login">Logout</a>
			</button>
		</div>
</body>
</html>