<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Artículo</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <%@page import="java.util.*" %>
  <%@page import="vo.*" %>
	<script>
	function realizaProceso(){
	        $.ajax({	               
	        	   data: ({
	        		   	  nombre : $("#nombre").val(),
	          	          descripcion : $("#descripcion").val(),
	        	          marca : $("#marca").val(),
	        	          precio : $("#precio").val(),
	        	          url : $("#url").val(),
	        	          origen : $("#origen").val(),
	        	          ficha : $("#ficha").val(),
	        	          categoria : $("#categoria").val(),
	        	          cantidad : $("#cantidad").val()	        		   	  
	        	          }),
	        	    url:   'CrearArticuloSVL',
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
<a href="IngresoCrearArticuloSVL">Crear nuevo Artículo</a>
<a href="busquedaArticulos.jsp">Buscar Artículo</a>

<h1>Crear nuevo Articulo</h1>
<br>
	<table>
		<tr>
			<td><label for="nombre">Nombre:</label></td>
			<td><input type="text" class="form-control" id="nombre">
			</td>
		</tr>
		<tr>
			<td><label for="descripcion">Descripción:</label></td>
			<td><input type="text" class="form-control" id="descripcion">
			</td>
		</tr>
		<tr>
			<td><label for="marca">Marca:</label></td>
			<td><input type="text" class="form-control" id="marca">
			</td>
		</tr>
		<tr>
			<td><label for="precio">Precio:</label></td>
			<td><input type="text" class="form-control" id="precio">
			</td>
		</tr>
		<tr>
			<td><label for="url">Url:</label></td>
			<td><input type="text" class="form-control" id="url"></td>
		</tr>
		<tr>
			<td><label for="origen">Origen:</label></td>
			<td><input type="text" class="form-control" id="origen">
			</td>
		</tr>
		<tr>
			<td><label for="ficha">Ficha Técnica:</label></td>
			<td><input type="text" class="form-control" id="ficha">
			</td>
		</tr>
		<tr>
			<td><label for="categoria">Categoría:</label></td>
		 	<td>
				<select class="form-control" id="categoria">				  
				  <% List<CategoriaVO> categorias =(List<CategoriaVO>) request.getAttribute("categorias");%>
			  	  <% for (CategoriaVO unaCategoria: categorias) {
			  	  %>								 		
				     <option value=<%=unaCategoria.getIdCategoria()%>> <%=unaCategoria.getNombre()%> </option>						 													
			  	  <%}%>					  
				</select>
			</td>
		</tr>
		<tr>
			<td><label for="cantidad">Cantidad:</label></td>
			<td><input type="text" class="form-control" id="cantidad">
			</td>
		</tr>
		<tr>
		<td>
		<button type="submit" class="btn btn-default" onclick="realizaProceso();">Crear Articulo</button>
		</td>
		</tr>
	</table>		  
		
	<span id="resultado"></span>  	  
	

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>