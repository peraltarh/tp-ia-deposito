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

<script>
	function confirmarEnvioSolicitudPedido(){
        $.ajax({	               
        	   data: ({         		
            	      i : $("#i").val()        
            	    }),
        	    url:   'ConfirmarEnvioSolicitudPedido',
                type:  'get',
                beforeSend: function () {
                        $("#resultado").html("Procesando, espere por favor...");
                },
                success:  function (response) {
                        $("#resultado").html(response);
                }
        });
}
	</script>


  <body>
  <%@page import="java.util.*" %>
  <%@page import="vo.*" %>
	
  <table class="table table-bordered">
	  <thead>
		  <tr>
		  	<th>Nro Item</th>
		  	<th>Articulo</th>
		  	<th>Cantidad Solicitada</th>	
		  	<th>Cantidad Disponible</th>	  	
		  	<th>Cantidad Enviar</th>
		  </tr>
	  </thead>
	  	<tbody>  
		  	<% @SuppressWarnings("unchecked")
		  	SolicitudDePedidoVO solicitudVO = (SolicitudDePedidoVO) request.getAttribute("solicitudVO");
		  	ArrayList<Integer> cantidadesStock = (ArrayList<Integer>) request.getAttribute("cantidadesStock");%>
		  	<%int i = 0; %>
		  	<% for (ItemPedidoVO itemVO: solicitudVO.getItemsPedido()) { 	%>			
				<tr>		
					<td> <%=i%> </td>
					<td> <%=itemVO.getArticulo().getDescripcion()%> </td>
					<td> <%=itemVO.getCantidad() %> </td>
					<td> <%=cantidadesStock.get(i) %> </td>
					<td> <input id="cantidadEnviar<%=i%>" type="text" value="<%=cantidadesStock.get(i)-itemVO.getCantidad()%>"></td>
				</tr>
		  	<%
		  	i++; }%>
	  	</tbody>
  </table>

<button id="enviarBtn" onclick="confirmarEnvioSolicitudPedido()"></button>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script> 
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>