<%@page import="jdk.nashorn.internal.ir.annotations.Ignore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <body>
  <%@page import="java.util.*" %>
  <%@page import="vo.*" %>
   
  <table class="table table-bordered">
			<thead>
			
			<tr>
				<th>ID SOLPE</th>
				<th>Fecha</th>
				<th>Seleccionar</th>
				<th>ID Articulo</th>
				<th>Descripcion Articulo</th>
				<th>Stock Actual</th>
				<th>Stock Solicitado</th>
				<th>Stock a Solicitar</th>
			</tr>
			
			</thead>
			
			<tbody>
			
			<% @SuppressWarnings("unchecked")
		  	List<SolicitudDePedidoVO> miLista = (List<SolicitudDePedidoVO>) request.getAttribute("listadoSolicitudesPedidoPendientes");%>
			<%for(int i=0; i < miLista.size();i++){%>
				<tr>
					<th><%=miLista.get(i).getIdSolicitudDePedido()%></th>
				</tr>
			<%}%>
			
			</tbody>
  </table>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script> 
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>