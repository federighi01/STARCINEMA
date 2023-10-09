<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "AreaRiservata";
%>

<html>
<head>
    <title>Area Utente</title>
    <%@include file="/include/htmlHead.inc"%>
</head>
<body>
<%@include file="/include/header.inc"%>
    <main>

    </main>
</body>
</html>
