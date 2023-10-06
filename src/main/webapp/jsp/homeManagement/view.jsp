<%@page session="false"%>
<%@page import="com.starcinema.starcinema.model.mo.Utente"%>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="java.util.List" %>

<%  int i = 0, c = 0;

    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Home";

    List<Film> films = (List<Film>) request.getAttribute("films");
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/include/htmlHead.inc"%>
    <style>
        #contacts {
            margin: 12px 0;
        }

        #contacts article {
            float: left;
            width: 250px;
            border-width: 1px;
            border-style: solid;
            border-radius: 10px;
            border-color: #a3271f;
            padding: 10px 8px 10px 20px;
            margin: 0 18px 16px 0;
            background: linear-gradient(to right,#fdfbfb,#ebedee);
            box-shadow: 0 3px 2px #777;
        }

        #contacts article h1 a {
            color: #a3271f;
        }
    </style>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>
    <%if (loggedOn) {%>
    Benvenuto <%=loggedUtente.getCognome()%> <%=loggedUtente.getNome()%>!<br/>
    work in progress...
    <%} else {%>
    Benvenuto.
    Fai il logon per accedere all'area riservata.
    <%}%>
    <br><br><br>
    STARCINEMA CONSIGLIA
    <br><br>
    <section id="films" class="clearfix">
        <%for (i = 0; i < films.size(); i++) {%>
        <article>
            <h1><a><%=films.get(i).getTitolo()%></a></h1> <br>
            <a><b>Regista: </b><%=films.get(i).getRegista()%></a> <br>
            <a><b>Genere: </b><%=films.get(i).getGenere()%></a> <br>
            <a><b>Durata: </b><%=films.get(i).getDurata()%>'</a> <br>
            <a><b>Nazione: </b><%=films.get(i).getNazione()%></a> <br>
            <a><b>Anno: </b><%=films.get(i).getAnno()%></a> <br>
            <a><b>Descrizione: </b><%=films.get(i).getDescrizione()%></a> <br>
            <a href=<%=films.get(i).getTrailer()%>>Clicca qui per il trailer</a>
            <% c++; %>
        </article>
        <br><br><br>
        <%}%><%=c%>
    </section>

</main>
<%--<%@include file="/include/footer.inc"%>--%>
</html>
