<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Sales</title>
        <script type="text/javascript"
                src="<s:url value='/resources/libs/jquery/jquery.min.js' />" >
        </script>
        <script type="text/javascript"
                src="<s:url value='/resources/libs/jqplot/jquery.jqplot.min.js' />" >
        </script>
        <link href="<s:url value='/resources/libs/jqplot/jquery.jqplot.min.css'/>"
              rel="stylesheet" type="text/css" />

        <script type="text/javascript"
                src="<s:url value='/resources/js/sales.js' />" >
        </script>
        <link href="<s:url value='/resources/css/styles.css'/>"
              rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form>
            <label for="countrySelect">Country:</label>
            <select id="countrySelect" >
                <c:forEach var="country" items="${countries}">
                    <option value="${country.code}">${country.name}</option>
                </c:forEach>
            </select>
        </form>

        <div id="chartError">Failed to contact server</div>
        <div id="chart"></div>

    </body>
</html>
