<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<title>Detré Marion</title>
</head>
<body>
	<fieldset>
		<legend>Réservation de paniers</legend> 
			<c:url var="URL_RESERVER" value="/index.html"></c:url>
			<form method="post" action="${URL_RESERVER}">
			
				<label>Personne de contact</label>
				<input type="text" name="nom"></br>
				
				<label>Téléphone</label>
				<input type="text" name="telephone"></br></br>
				
				<table>
					<thead>
						<tr>
							<th>Description</th>
							<th>Prix</th>
							<th>Réservé</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${liste_paniers}" var="panier" varStatus="vs">
							<tr>
								<td><c:out value="${panier.description}"/></td>
								<td><c:out value="${panier.prix}"/></td>
								<td><input type="checkbox" name="paniers_reserves" value="${vs.index}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table></br>
				<input type="submit" value="Enregistrer la réservation">
			</form>
	</fieldset>
	<fieldset>
		<legend>Message</legend>
			<c:choose>
				<c:when test="${operationReussie==true}">
					<label><font color="green">${ message }</font></label>
				</c:when>
				<c:otherwise>
					<label><font color="red">${ message }</font></label>
				</c:otherwise>
			</c:choose>
	</fieldset>
</body>
</html>