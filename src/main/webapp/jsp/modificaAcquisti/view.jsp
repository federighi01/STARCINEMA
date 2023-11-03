<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%  int c = 1;
    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Modificaacquisti";

    Utente utente = (Utente) request.getAttribute("utente");
    List<Acquista> acquisti = (List<Acquista>) request.getAttribute("acquisti");
    List<Film> films = (List<Film>) request.getAttribute("films");
    List<Proiezione> proiezioni = (List<Proiezione>) request.getAttribute("proiezioni");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Modifica acquisti</title>
    <%@include file="/include/htmlHead.inc"%>
</head>
<body>
<%@include file="/include/header.inc"%>
    <main>

        <section id="viewacq" class="clearfix">
            <form name="viewacqForm" action="Dispatcher" method="post">
        <%if (acquisti != null && !acquisti.isEmpty()) {%>
        <%= "Hai effettuato " + acquisti.size() + " acquisti"%><br><br><br>
            <%for (int i = 0; i < acquisti.size(); i++) {%>
                <% c+=i;%>
                <%= "Acquisto n° " + c%><br>

                <b>Data di proiezione: </b> <%=proiezioni.get(i).getData_pro()%><br>
                <b>Ora di proiezione: </b> <%=proiezioni.get(i).getOra_pro()%><br>
                <b>Titolo del film: </b> <%=films.get(i).getTitolo()%><br>
                <b>Numero sala: </b> <%=proiezioni.get(i).getSala().getNum_sala()%><br>
                <b>Posto acquistato: </b><%=acquisti.get(i).getPosto().getNum_posto()%>

                <input type="submit" class="button" value="Modifica acquisto">
                <input type="hidden" name="controllerAction" value="ModificaAcquisti.modview"/><br><br>
            <%}%>
        <%} else {%>
            Nessun acquisto effettuato.
        <%}%>

            </form>
        </section>

    </main>
</body>
</html>
