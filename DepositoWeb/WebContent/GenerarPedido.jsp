<%@page import="modelo.EnumSolicitudDePedido"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	<script>
		function getSolicitudesPendientes(){
		        $.ajax({	               
		        	   data: ({
		        		   	  generico : $("#solicitudesPendientes").val()        		   	  
		        	          }),
		        	    url:   'GenerarPedido',
		                type:  'get',
		                beforeSend: function () {
		                	 $("#solicitudesPendientes").html("Procesando, espere por favor...");
		                },
		                success:  function (response) {
		                        $("#solicitudesPendientes").html(response);
		                }
		        });
		}
		function postPedidoAFabrica(){
	        $.ajax({	               
	        	   data: ({
	        		   	  generico : $("#nroPedido").val()        		   	  
	        	          }),
	        	    url:   'GenerarPedido',
	                type:  'post',
	                beforeSend: function () {
	                	 $("#nroPedido").html("Generando un Pedido, espere por favor...");
	                },
	                success:  function (response) {
	                        $("#nroPedido").html(response);
	                }
	        });
		}
	</script>


<h1><b>Generar Pedido</b></h1>
<br>
<button type="submit" class="btn btn-default" onclick="getSolicitudesPendientes()">Listar Solicitudes Pendientes</button>
<br>
<span id="solicitudesPendientes"></span>
<br>
<button type="submit" class="btn btn-default" onclick="postPedidoAFabrica()">Generar Pedido a Fabrica</button>
<br>
<span id="nroPedido"></span>
<br>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>