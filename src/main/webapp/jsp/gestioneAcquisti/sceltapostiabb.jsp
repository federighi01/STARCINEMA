<%@page session="false"%>
<%@ page import="java.util.List" %>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%
    String formattedDate=null;
    String formattedTime=null;

    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Sceltaposti";

    Utente utente = (Utente) request.getAttribute("utente");
    Film film = (Film) request.getAttribute("film");
    Proiezione proiezione = (Proiezione) request.getAttribute("proiezione");
    List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
    Sala sala = (Sala) request.getAttribute("sala");
    Biglietto biglietto = (Biglietto) request.getAttribute("biglietto");
    Abbonamento abbonamento = (Abbonamento) request.getAttribute("abbonamento");
    Acquista_abb acquista_abb = (Acquista_abb) request.getAttribute("acquista_abb");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Scelta posti</title>
    <%@include file="/include/htmlHead.inc"%>
    <script language="javascript">

        function checkSelections() {
            var selectedCheckboxes = document.querySelectorAll('input[type="checkbox"]:checked');
            if (selectedCheckboxes.length > 1) {
                alert("Puoi selezionare al massimo 1 posto.");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<%@include file="/include/headeracq.inc"%>
<main>
    <%
        if(proiezione != null) {
            Date dataPro = proiezione.getData_pro();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate = dateFormat.format(dataPro);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            formattedTime = timeFormat.format(proiezione.getOra_pro());
        }
    %>


    <%if (sala != null) { %>
    Sala n. <%=sala.getNum_sala()%>
    <%}%>
    Seleziona il posto
    <br><br>

    <section id="postiFormSection">
        <form name="postiForm" action="Dispatcher" method="post" onsubmit="return checkSelections();">
            <%for (int i = 0; i < composizioni.size(); i++) {%>
            <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
                <input type="checkbox" name="selectedposti" value="<%= composizioni.get(i).getPosto().getNum_posto() %>"
                        <% if (composizioni.get(i).isOccupato()) { %>
                       disabled="disabled"
                    <% } %>
                    <% } %>
                <br><br>

                    <%if (acquista_abb != null) {%>
                    <%=abbonamento.getCod_abb()%>cds <%=acquista_abb.getCod_acq_abb()%>cr
                    <%}%>

                <a> <input type="submit" class="button" value="Conferma selezione"/></a>
                <input type="hidden" name="num_sala" value="<%=sala.getNum_sala()%>">
                <input type="hidden" name="selectedcodfilm" value="<%=film.getCod_film()%>">
                <input type="hidden" name="cod_pro" value="<%=proiezione.getCod_pro()%>">
                <input type="hidden" name="formattedDate" value="<%=formattedDate%>"/>
                <input type="hidden" name="formattedTime" value="<%=formattedTime%>"/>
                <input type="hidden" name="controllerAction" value="GestioneAcquisti.sceltapostiabb"/>
        </form>
    </section>

</main>
</body>
</html>
