<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title> Add an infantry</title>
<style type= "text/css">
.error {
	color: blue;
}
td.label {
	text-align: right;
}

button {
	margin-top: 2px;
}

#PageName {
	color: gold;
	font-size: 200%;
	text-align: center;
	background-color: darkgreen;
	font-variant: small-caps;
}

#logon {
	margin: 50px;
	border: 1px solid orange;
	width: 400px;
	padding: 10px;
}
#Content {
	float: left;
}

</style>
</head>

<body>
	<div id = "PageName">Add infantry profiles</div>
	<form action = "${pageContext.servletContext.contextPath }/AddInfantry" method = "post">
		<div id = "Add an infantry template to the database">
		<table>
			<c:if test="${!empty message }">
				<tr>
					<td><class = "error">${message}</class></td>
				</tr>			
			</c:if>
			
			
		</table>
		</div>
	
	</form>

</body>
</html>