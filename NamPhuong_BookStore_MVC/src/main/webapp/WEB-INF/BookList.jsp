<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Nam Phuong's BookStore </h1>
	 <c:forEach items = "${books}" var="book"> <%-- Bên Controller: bean books(xanh dương) được request truyền qua view là 1 cái List . Mapping 1:1, tự động set thuộc tính tương ứng--%>
	 	<p>${book.id}, ${book.name}, ${book.describe}, ${book.price}</p>       <%-- dấu . là một cách viết tắt để lấy hàm getCode, getName --%>     
	 </c:forEach>
	
</body>
</html>