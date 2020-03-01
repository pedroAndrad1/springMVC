<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- A taglib tags/form serve para usarmos as features do spring em nosso form -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- A taglib tags nos auxiliara em evitar ficar mexendo no form do usuario por meio de algumas variaveis -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- O atributo enctype = multipart/form-data e para permitir o form enviar files do tipo multipart (pdf, jpg etc) -->
	<form action="/casadocodigo/produtos" method="POST" enctype="multipart/form-data">
		<div>
			<label>Título</label> 
			<form:input path="produto.titulo"/>
			<form:errors path = "produto.titulo"/>
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea path="produto.descricao" rows="10" cols="20"/>
			<form:errors path = "produto.descricao"/>
			
		</div>
		<div>
			<label>Páginas</label> 
			<form:input path="produto.paginas"/>
			<form:errors path = "produto.paginas"/>
		</div>
		<div>
			<label>Data de Lançamento</label> 
			<form:input path="produto.dataLancamento"/>
			<form:errors path = "produto.dataLancamento"/>
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<label>${tipoPreco}</label>
			
			<!-- Vou passar valores para um atributo array em um objeto, entao usarei [] o status.index como iterador. 
        	      Além disso, como o atributo que recebera esse array e um array de objetos, preciso usar "." para dizer
        	      que atributo desse objeto recebera o valor-->
			
			<form:input path="produto.precos[${status.index }].valor"/>
			
			<!-- Usar type = "text" no input acima gera um NumberFormatException. Ainda nao sei o pq. -->
			
			<form:hidden path="produto.precos[${status.index}].tipo" value = "${tipoPreco}" />

		</c:forEach>
		<div>
			<label>Sumário</label>
			<input type="file" name="sumario"/>
		</div>

		<button type="submit">Cadastrar</button>
	</form>

</body>
</html>