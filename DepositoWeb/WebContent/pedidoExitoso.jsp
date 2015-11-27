<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pedido Exitoso</title>
</head>
<body>
<% int nroPedido = (int) request.getAttribute("nroPedido"); %>
<h1>Pedido Generado Exitosamente</h1>
<br>
<a>Se ha generado exitosamente el Pedido a Fábrica bajo el número: <%=nroPedido%>.</a>
<br>
<br>
<a href="index.jsp">Regresar al Indice.</a>
</body>
</html>