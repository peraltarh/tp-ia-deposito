<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manejo de Pedidos</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<script>
	function realizaProceso(){
	        $.ajax({	     
	        	   data: ({
	        		   	  generico : $("#resultado").val(),        		   	  
	        	          }),          
	        	    url:   'Index',
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
  
<h1>FABRICA</h1>
<br>
<button type="submit" class="btn btn-default" onclick="realizaProceso();">Listar Pedidos</button>
<br>
<span id="resultado"></span>  	


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>  
</body>
</html>