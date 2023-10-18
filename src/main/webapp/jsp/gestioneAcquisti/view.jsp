<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="com.starcinema.starcinema.model.mo.Proiezione" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Metodoingresso";

    Film film = (Film) request.getAttribute("film");
    Proiezione proiezione = (Proiezione) request.getAttribute("proiezione");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Acquista</title>
    <%@include file="/include/htmlHead.inc"%>
    <script language="javascript">

        function acqbiglietto(selectedcodfilm){
            document.acqbigliettoForm.selectedcodfilm.value = selectedcodfilm;
            document.acqbigliettoForm.submit();
        }


    </script>
</head>
<body>
    <main>
        <%
            Date dataPro = proiezione.getData_pro();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = dateFormat.format(dataPro);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = timeFormat.format(proiezione.getOra_pro());
        %>
        <%= formattedDate %><br><%= formattedTime %><br>
        <section id="bigliettoButtonSection">
            <a> <input type="button" id="bigliettoButton" name="bigliettoButton"
                       class="button" value="Acquisto biglietto" onclick="acqbiglietto(<%=film.getCod_film()%>)"/></a>
        </section><br>
        <section id="abbButtonSection">
            <a> <input type="button" id="abbButton" name="abbButton"
                       class="button" value="Utilizzo abbonamento" onclick="addpro()"/></a>
        </section>


        <form name="acqbigliettoForm" method="post" action="Dispatcher">
            <input type="hidden" name="selectedcodfilm"/>
            <input type="hidden" name="controllerAction" value="GestioneAcquisti.view"/>
        </form>

    </main>
</body>
</html>
