<%@page import="jdk.nashorn.internal.ir.annotations.Ignore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		  	<th>Nombre</th>
		  	<th>Codigo</th>
		  	<th>Descripci�n</th>
		  	<th>Marca</th>
		  	<th>Origen</th>
		  	<th>Ficha T�cnica</th>
		  	<th>Precio</th>	
		  	<th>Stock</th>
		  	<th>Modificar Stock</th>	  	
		  </tr>
	  </thead>
	  	<tbody>  
		  	<% @SuppressWarnings("unchecked")
		  	List<ArticuloVO> articulos = (List<ArticuloVO>) request.getAttribute("listaArticulos");%>
		  	<% for (ArticuloVO unArticulo: articulos) {
		  	%>			
				<tr>		
					<td> <%=unArticulo.getNombre()%> </td>
					<td> <%=unArticulo.getCodigoArticulo()%> </td>
					<td> <%=unArticulo.getDescripcion()%> </td>
					<td> <%=unArticulo.getMarca()%> </td>
					<td> <%=unArticulo.getOrigen()%> </td>
					<td> <%=unArticulo.getFichaTecnica()%> </td>
					<td> <%=unArticulo.getPrecio()%> </td>
					<td> <%=unArticulo.getStock()%> </td>
					<td> <a href="modificarStockArticulo.jsp?idStock=<%=unArticulo.getIdStock()%>">Actualizar Stock</a> </td>									
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