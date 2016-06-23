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
			<tr>
				<td class = "label">Infantry: </td>
				<td> <input tpye = "text" "name = "infantry" size = "12"/> </td>
			</tr>
			<tr> 
				<td class = "label"> Weapon Skill: </td>
				<td> <input type = "text" name = "WS" size = "12"/> </td>
			</tr>
			
			<tr>
				<td class = "label"> Balistic Skill: </td>
				<td> <input type = "text" name = "BS" size = "12" /> </td>
			</tr>
			
			<tr>
				<td class = "label"> Strength: </td>
				<td> <input type = "text" name = "Strength" size = "12" /> </td>	
			</tr>
			
			<tr>
				<td class = "label"> Toughness: </td>
				<td><input type = "text" name = "Toughness" size = "12" /> </td>
			</tr>
			
			<tr>
				<td class = "label"> Wounds: </td>
				<td><input type = "text" name = "Wounds" size = "12" /> </td>
			</tr>
			
			<tr>
				<td class = "label"> Initiative: </td>
				<td><input type = "text" name = "Initiative" size = "12" /> </td>
			</tr>
			
			<tr>
				<td class = "label"> Attacks: </td>
				<td><input type = "text" name = "Attacks" size = "12" /> </td>
			</tr>
			
			<tr>
				<td class = "label"> Leadership: </td>
				<td><input type = "text" name = "Leadership" size = "12" /> </td>
			</tr>
			
			<tr>
				<td class = "label"> Save: </td>
				<td><input type = "text" name = "Save" size = "12" /> </td>
			</tr>
			
		</table>
		</div>
			<input type="Submit" name="submit" value="Add Infantry" />
		</form>
	<!-- May add a method later for removing infantry profiles -->	
	
</body>
</html>