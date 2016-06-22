<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Create Account</title>
<style type="text/css">
.error {
	color: red;
}

td.label {
	text-align: right;
}

#PageName {
	color: gold;
	font-size: center;
	text-align: center;
	background-color: black;
	font-variant: small-caps;
}

#createAccount {
	margin: 50px auto;
	border: 1px solid darkgreen;
	width: 400px;
	padding: 10px;
}
</style>
</head>

<body>
	<c:if test = "${empty errorMessage }">
		<div class = "error">${errorMessage}</div>
	</c:if>
	
	<div id="PageName">Create Your Account Brother</div>
	<form action="${pageContext.servletContext.contextPath}/CreateAccount" method = "post">
		<div id="createAccount">
			<table>
				<tr>
					<td class = "label"> First name: </td>
					<td><input type = "text" name = "firstname" size = "12" value = "${username}" required/> </td>
				</tr>
			
			</table>
			<input type = "Submit" name = "submit" value = "Create Account"/>
		</div>
	
	
	
	</form>
</body>
</html>