<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
<h1>Welcome to Admin page</h1>
<a href="doLogout">Logout</a>
<form action="/doLogout" method="post">
 <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
 <button>Logout</button>
</form>
</body>
</html>