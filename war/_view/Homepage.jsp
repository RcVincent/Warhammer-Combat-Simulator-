<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Random Number Generator Home</title>
<style type = "text/css">
#PageName {
	color: green;
	font-size: 200%;
	text-align: center;
	background-color: darkblue;
	font-variant: small-caps;
}

#LinkContent {



}

#LinkName {



}

#ContactBody {



}

#Content {

}

div.fixed {



}

button {


}

.error {


}


</style>
</head>
<body>
	<div id="fb-root"></div>
	<script>
			(function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0];
				if (d.getElementById(id))
					return;
				js = d.createElement(s);
				js.id = id;
				js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6";
				fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));
		</script>
	</div>
	<c:if test="${! empty errorMessage}">
		<div class="error">${errorMessage}</div>
	</c:if>
	
	<div id="Content">
		<div id="LinkContent">
			<div id="LinkName">Search</div>
			<table>
				<tr>
					<form action="${pageContext.servletContext.contextPath}/Homepage"
						method="post">
						<td class="label"></td>
					</form>
				</tr>
			</table>
		</div>
	<br>
		<div id="LinkContent">
			<div id="LinkName">Your Account</div>
			<div>
				<form action="${pageContext.servletContext.contextPath}/your-account"
					method="post">
					<table>
						<tr>
							<td><input type="Submit" name="submit"
								value="Click to view Account Info" /></td>
						</tr>
					</table>
				</form>
			</div>
			</div>
		<br>
		<div class="fb-like" data-href="http://localhost:8081/lab03/Homepage"
			data-width="50" data-layout="button" data-action="recommend"
			data-show-faces="false" data-share="false"></div>
		<br>
		<script>
			!function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
						.test(d.location) ? 'http' : 'https';
				if (!d.getElementById(id)) {
					js = d.createElement(s);
					js.id = id;
					js.src = p + '://platform.twitter.com/widgets.js';
					fjs.parentNode.insertBefore(js, fjs);
				}
			}(document, 'script', 'twitter-wjs');
		</script>
	</div>
	
	<div class="fixed">
		<button>
			<a href="/lab03/Login"><type="button">Logout </a>
		</button>
</body>	
</html>