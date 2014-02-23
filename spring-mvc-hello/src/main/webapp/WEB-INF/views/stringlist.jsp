<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>String items</title>
        <link href="<s:url value='/resources/css/styles.css'/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <p>
            <c:forEach var="item" items="${items}">
                <span class="box-with-border">${item}</span>
            </c:forEach>
        </p>
        
        <sf:form method="POST" action="stringlist-add" modelAttribute="newItem" >
            <sf:label path="text">New item:</sf:label><sf:input path="text" />
            <input type="submit" value="Add" />
        </sf:form>
        
    </body>
</html>
