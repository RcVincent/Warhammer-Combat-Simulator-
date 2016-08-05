<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<title></title>
<style type = "text/css">

#PageName {
	color: white;
	font-size: 250%;
	text-align: center;
	background-color: darkblue;
	font-variant: small-caps;
}

table{
		border-spacing: 10px;
}

td.label{
	text-align: right;
}

</style>
</head>

<body>
	<div id = "PageName">Weapon Profiles</div>
	<div id = "AccountInfo">
			
			<table>
				<tr>
					<td> Faction Number:</td>
					<td> ${factionID} </td>
				</tr>
			</table>
			<table>
				<tr>
					<td> Armory Number:</td>
					<td> ${armoryID} </td>
				</tr>
			
			</table>
			<table>
				<c:forEach items="${Armory}" var="armory">
				<tr>
					<td> ${Armory.WeaponID} </td>
					<td> ${Weapon.name} </td>
					<td> ${Weapon.strength} </td>
					<td> ${Weapon.AP} </td>
				</tr>
			</c:forEach>
			</table>
			<table>
				<tr>
					<td class = "label"> Total price: </td>
					<td class = "label"> $ ${total} </td>
				</tr>
				
				<tr><form action="${pageContext.servletContext.contextPath}/Homepage" method="get">
					<td><input type="Submit" name="submit" value="Click to go to Home"/></td>
					</form>
				</tr>
			</table>
			</div>
		
</body>
</html>