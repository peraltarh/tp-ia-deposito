<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entregar Solicitud Pedido</title>
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
	        	    url:   'EntregarSolicitudPedido',
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

<h1>Entregar Solicitud Pedido</h1>
<br>

	<%
		session.setAttribute("idSolicitud", request.getParameter("idSolicitud"));
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
</body>
</html>