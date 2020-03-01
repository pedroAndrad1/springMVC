<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
					<td>
						<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">
								${produto.titulo} 
						</a>
					</td>
					<td>${produto.descricao }</td>
					<td>${produto.paginas }</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

</body>
</html>