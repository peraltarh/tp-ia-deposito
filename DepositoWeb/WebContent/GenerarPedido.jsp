<%@page import="modelo.EnumSolicitudDePedido"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.*" %>
  <%@page import="vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitud de Pedido</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<h1><b>Generar Pedido</b></h1>
<br>
<form method="post">
  <table class="table table-bordered">
	<thead>
	
	<tr>
		<th>ID SOLPE</th>
		<th>Fecha</th>
		<th>ID Articulo</th>
		<th>Descripcion Articulo</th>
		<th>Stock Actual</th>
		<th>Stock Solicitado</th>
		<th>Stock a Solicitar</th>
		<th>Seleccionar</th>
	</tr>
	
	</thead>
	
	<tbody>
	
	<% @SuppressWarnings("unchecked")
  	List<SolicitudDePedidoVO> solpes = (List<SolicitudDePedidoVO>) request.getAttribute("listaSolPes");%>
	<%for(int i=0; i < solpes.size();i++){%>
		<% List <ItemPedidoVO> itemsPedido = (List<ItemPedidoVO>) solpes.get(i).getItemsPedido();%>
			<%for(int j=0; j< itemsPedido.size();j++){%>
				<tr>
					<th><%=solpes.get(i).getIdSolicitudDePedido()%></th>
					<th><%=solpes.get(i).getFecha()%>
					<th><%=itemsPedido.get(j).getArticulo().getIdArticulo()%>
					<th><%=itemsPedido.get(j).getArticulo().getDescripcion()%>
					<th><%=itemsPedido.get(j).getArticulo().getStock()%>
					<th><%=itemsPedido.get(j).getCantidad()%>
					<th><input name= "stockSolicitado"></input></th>
					<th><input type="checkbox" name = "solpesSeleccionadas" value=<%=solpes.get(i).getIdSolicitudDePedido() + "-" + itemsPedido.get(j).getIdItemPedido()%>></input></th>
				</tr>
			<%}%>
	<%}%>
	</tbody>
  </table>
  <br>
  <button type="submit" class="btn btn-default">Generar Pedido a Fabrica</button>
</form>
<br>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>