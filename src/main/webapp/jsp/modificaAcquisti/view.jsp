<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%  int c = 0;
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
    <script language="javascript">

        function datitochange(cod_film_old, cod_pro_old, num_posto_old){
            console.log(cod_film_old);
            document.viewacqForm.cod_film_old.value = cod_film_old;
            document.viewacqForm.cod_pro_old.value = cod_pro_old;
            document.viewacqForm.num_posto_old.value = num_posto_old;

            console.log(document.viewacqForm.cod_film_old.value);
            document.viewacqForm.submit();
        }

    </script>
</head>
<body>
<%@include file="/include/header.inc"%>
    <main>

        <section id="viewacq" class="clearfix">
            <form name="viewacqForm" action="Dispatcher" method="post">
        <%if (acquisti != null /*&& !acquisti.isEmpty() && acquisti.equals("")*/ && proiezioni != null && films != null) {%>
        <%= "Hai effettuato " + acquisti.size() + " acquisti"%><br><br><br>
            <%for (int i = 0; i < acquisti.size(); i++) {%>
                <%if (acquisti.get(i).getUtente().getUsername().equals(loggedUtente.getUsername())) {%>
                <% c++;%>
                <%= "Acquisto n° " + c%><br>


                <b>Data di proiezione: </b> <%=proiezioni.get(i).getData_pro()%><br>
                <b>Ora di proiezione: </b> <%=proiezioni.get(i).getOra_pro()%><br>
                <b>Titolo del film: </b> <%=films.get(i).getTitolo()%><br>
                <b>Numero sala: </b> <%=proiezioni.get(i).getSala().getNum_sala()%><br>
                <b>Posto acquistato: </b><%=acquisti.get(i).getPosto().getNum_posto()%>

                <!-- Invio dati da modificare -->
                <input type="button" class="button" value="Modifica acquisto"
                       onclick="datitochange('<%=acquisti.get(i).getFilm().getCod_film()%>','<%=acquisti.get(i).getProiezione().getCod_pro()%>','<%=acquisti.get(i).getPosto().getNum_posto()%>')">
                <br><br>
                <%}%>
            <%}%>
        <%} else {%>
            Nessun acquisto effettuato.
        <%}%>
                <input type="hidden" name="cod_film_old">
                <input type="hidden" name="cod_pro_old">
                <input type="hidden" name="num_posto_old">
                <input type="hidden" name="controllerAction" value="ModificaAcquisti.modview"/>
            </form>
        </section>

    </main>
</body>
</html>
