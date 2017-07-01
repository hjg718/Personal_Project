<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>영화검색</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
</script>
<style>
</style>
</head>
<body>
<form action="<c:url value="/movie/search" />" method="post" >
<input type="hidden" name="${_csrf.parameterName }"	value="${_csrf.token }">
<input type="text" name="keyword">
<button type="submit">검색</button>
</form>
</body>
</html>