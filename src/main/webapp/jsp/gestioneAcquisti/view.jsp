<%@page session="false"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>

<%
    String formattedDate=null;
    String formattedTime=null;

    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Metodoingresso";

    Film film = (Film) request.getAttribute("film");
    Proiezione proiezione = (Proiezione) request.getAttribute("proiezione");
    Biglietto biglietto = (Biglietto) request.getAttribute("biglietto");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Acquista</title>
    <%@include file="/include/htmlHead.inc"%>
    <script language="javascript">

        function acqbiglietto(selectedcodfilm,num_sala,cod_pro){
            document.acqbigliettoForm.selectedcodfilm.value = selectedcodfilm;
            document.acqbigliettoForm.num_sala.value = num_sala;
            document.acqbigliettoForm.cod_pro.value = cod_pro;
            document.acqbigliettoForm.submit();
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

        %>
        <%= formattedDate %><br><%= formattedTime %><br>
        <%=proiezione.getSala().getNum_sala()%><br><%=proiezione.getCod_pro()%><%}%>
        <section id="bigliettoButtonSection">
            <a> <input type="button" id="bigliettoButton" name="bigliettoButton"
                       class="button" value="Acquisto biglietto"
                       onclick="acqbiglietto(<%=film.getCod_film()%>,<%=proiezione.getSala().getNum_sala()%>,<%=proiezione.getCod_pro()%>)"/></a>
        </section><br>

        <section id="acqabbButtonSection">
            <a> <input type="button" id="acqabbButton" name="acqabbButton"
                       class="button" value="Acquisto abbonamento" onclick="addpro()"/></a>
        </section><br>

        <section id="useabbButtonSection">
            <a> <input type="button" id="useabbButton" name="useabbButton"
                       class="button" value="Utilizzo abbonamento" onclick="addpro()"/></a>
        </section>

        <form name="acqbigliettoForm" method="post" action="Dispatcher">
            <input type="hidden" name="selectedcodfilm"/>
            <input type="hidden" name="num_sala"/>
            <input type="hidden" name="cod_pro"/>
            <input type="hidden" name="formattedDate" value="<%=formattedDate%>"/>
            <input type="hidden" name="formattedTime" value="<%=formattedTime%>"/>
            <input type="hidden" name="controllerAction" value="GestioneAcquisti.sceltapostiView"/>
        </form>

    </main>
</body>
</html>
