<%--
  Created by IntelliJ IDEA.
  User: sunxingyu
  Date: 2018/3/24
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";
    pageContext.setAttribute("basePath",basePath);
    String name = "uri";
    System.out.println(name);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${hello}
    ${pageContext.request.contextPath}
   <c:forEach items="${users}" var="li">
       ${li}
   </c:forEach>
    <c:if test="${hello}==''">
        <c:out value="ss"/>
    </c:if>
</body>
</html>
