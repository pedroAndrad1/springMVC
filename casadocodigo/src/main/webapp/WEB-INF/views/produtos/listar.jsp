<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${sucesso }</p>
	<table>
		
		<thead>
			<tr>
				<td>Título</td>
				<td>Descrição</td>
				<td>Páginas</td>
			</tr>
		</thead>
		<tbody>
				
			<c:forEach items="${produtos }" var="produto">
					
					<tr>
						<td>${produto.titulo }</td>
						<td>${produto.descricao }</td>
						<td>${produto.paginas }</td>
					</tr>
			
			</c:forEach>	
			
		</tbody>
	
	</table>
	
</body>
</html>