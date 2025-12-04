<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background: #f5f6fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-container {
	width: 350px;
	background: white;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
}

h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

input {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: 1px solid #ddd;
	border-radius: 8px;
	outline: none;
	font-size: 16px;
}

input:focus {
	border-color: #4a90e2;
}

button {
	width: 100%;
	padding: 12px;
	background: #4a90e2;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	font-size: 16px;
	margin-top: 10px;
}

button:hover {
	background: #3b79c9;
}

.error {
	color: red;
	text-align: center;
	margin-top: 10px;
}

.logout-msg {
	color: green;
	text-align: center;
	margin-top: 10px;
}
</style>
</head>

<body>


<!-- jab hum galat id password dal dete hai aur usko submit karte hai tab spring security hume ek error naam ka object deti hai -->
<%

   String s = (String) request.getParameter("error") ;
   if(s!=null){
	   %>
	   	   <p style="color:red" align="center">Incorrect Username or Password</p>
   <% }%>


	<div class="login-container">
		<h2>Login</h2>

		<form method="post" action="/doLogin">

			<input type="text" name="username" placeholder="Enter Username" required>
			<input type="password" name="password" placeholder="Enter Password" required>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">

			<button >Login</button>
		</form>
	</div>
</body>
</html>