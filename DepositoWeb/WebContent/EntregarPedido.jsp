<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entregar Pedido</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<script>
	function realizaProceso(){
	        $.ajax({	               
	        	   data: ({ 
	        		   	  cantidad : $("#cantidad").val()        		   	  
	        	          }),
	        	    url:   'EntregarPedido',
	                type:  'post',
	                beforeSend: function () {
	                        $("#resultado").html("Procesando, espere por favor...");
	                },
	                success:  function (response) {
	                        $("#resultado").html(response);
	                }
	        });
	}
	</script>  

<h1>DEPOSITO</h1>
<a href="index.jsp">Indice</a>

<h1>Entregar Pedido</h1>
<br>

	<%
		session.setAttribute("idPedido", request.getParameter("idPedido"));
	%>
	
	<table class="table table-bordered">
	  <thead>
		  <tr>
		  	<th>ID PEDIDO</th>
		  	<th>Fecha</th>
		  	<th>Modificar Stock</th>	  	
		  </tr>
	  </thead>
	  	<tbody>  
<%-- 		  	<% @SuppressWarnings("unchecked") --%>
<%-- <%-- 		  	List<PedidoVO> pedidos = (List<PedidoVO>) request.getAttribute("listadoPedidosPendientes");%> --%> --%>
<%-- 		  	<% for (PedidoVO unPedido: pedidos) { --%>
<%-- <%-- 		  	%>			 --%> --%>
<!-- 				<tr>		 -->
<%-- 					<td> <%=unPedido.getIdPedido()%> </td> --%>
<%-- 					<td> <%=unPedido.getFechaSolicitud() %> </td> --%>
<%-- 					<td> <a href="EntregarPedido.jsp?idPedido=<%=unPedido.getIdPedido()%>">Entregar Pedido</a> </td>									 --%>
<!-- 				</tr> -->
<%-- 		  	<%}%> --%>
	  	</tbody>
  </table>
	
	
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td><label for="cantidad">Nueva Cantidad:</label></td> -->
<!-- 			<td><input type="text" class="form-control" id="cantidad"> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><button type="submit" class="btn btn-default" -->
<!-- 					onclick="realizaProceso();">Modificar Stock</button></td> -->
<!-- 			<td><button type="submit" class="btn btn-default" -->
<!-- 					onclick="history.back()">Nueva Busqueda</button></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<!-- 	<span id="resultado"></span> -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>