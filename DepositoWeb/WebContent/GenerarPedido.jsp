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
	function realizaProceso(){
	        $.ajax({	               
	        	   data: ({
	        		   	  generico : $("#resultado").val()        		   	  
	        	          }),
	        	    url:   'GenerarPedido',
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


<h1><b>Generar Pedido</b></h1>
<br>
<button type="submit" class="btn btn-default" onclick="realizaProceso()">Listar Solicitudes Pendientes</button>
<br>
<span id="resultado"></span>
<br>
<button type="submit" class="btn btn-default" >Solicitar a Fabrica</button>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>