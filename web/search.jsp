<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean class="images.SearchBean" id="search" scope="request"/>
<jsp:setProperty name="search" property="*"/>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Similarity search</title>
    </head>
    
    <body>
        <form>
            Query image: <input type="text" name="query" value="${param['query']}"/>
            <input type="submit" />            
        </form>
        
        <c:forEach items="${search.results}" var="result">
            <span style="position: relative; padding-right: 20px">
                <span style="position: absolute">${result.distance}</span>
                <img src="images/${result.object.locatorURI}" style="max-width: 100px" />
            </span>
        </c:forEach>
    </body>
</html>
