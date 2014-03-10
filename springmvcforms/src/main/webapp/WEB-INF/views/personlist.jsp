<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person List</title>
        <link href="<s:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <p>
            <c:forEach var="person" items="${list}">
                <span class="box-with-border"><c:out value="${person.firstName} ${person.lastName} (${person.age})" /></span>
            </c:forEach>
        </p>
        
        <sf:form method="POST" modelAttribute="newPerson" cssClass="personForm">
            <table>
                <tr>
                    <td><sf:label path="firstName">First name:</sf:label></td>
                    <td><sf:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><sf:label path="lastName">Last name:</sf:label></td>
                    <td><sf:input path="lastName"/></td>
                </tr>
                <tr>
                    <td><sf:label path="age">Age:</sf:label></td>
                    <td><sf:input path="age" /></td>
                </tr>
                <tr><td></td><td><input type="submit" value="Add" /></td></tr>
            </table>
        </sf:form>
        
    </body>
</html>
