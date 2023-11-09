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
    Acquista_abb acquista_abb = (Acquista_abb) request.getAttribute("acquista_abb");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Acquista</title>
    <%@include file="/include/htmlHead.inc"%>
    <style>

        body, html {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        main {
            width: 100%;
            height: 100%;
            overflow: auto;
        }

        /* Style scheda film */

        .film {
            width: 80%;
            border: 1px solid #ccc; /* Add border style */
            border-radius: 5px; /* Add border radius */
            padding: 10px; /* Add padding */
            margin: 10px; /* Add margin */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add box shadow */
        }

        .film img {
            width: 160px;
            height: 250px;
            object-fit: cover; /* Scala e taglia l'immagine per adattarla alle dimensioni specificate */
        }

        .film td,tr {
            border: none;
        }

        .pro td, tr {
            border: none;
        }

    </style>
    <script language="javascript">

        function acqbiglietto(selectedcodfilm,num_sala,cod_pro){
            document.acqbigliettoForm.selectedcodfilm.value = selectedcodfilm;
            document.acqbigliettoForm.num_sala.value = num_sala;
            document.acqbigliettoForm.cod_pro.value = cod_pro;
            document.acqbigliettoForm.submit();
        }

        function acqbigliettoabb(selectedcodfilm,num_sala,cod_pro){
            document.acqbigliettoabbForm.selectedcodfilm.value = selectedcodfilm;
            document.acqbigliettoabbForm.num_sala.value = num_sala;
            document.acqbigliettoabbForm.cod_pro.value = cod_pro;
            document.acqbigliettoabbForm.submit();
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
        <%}%>

        <table class="film" style="margin: auto">
            <tr>
                <td rowspan="2" style="text-align: center;">
                    <%if (film.getImmagine()!=null) {%>
                    <img src="<%=film.getImmagine()%>">
                    <%} else {%>
                    <img src="images/imgnotfound.jpg">
                    <%}%>
                </td>
                <td style="padding-top: 40px; padding-bottom: 0px;">
                    <h3><a><b><%=film.getTitolo()%></b></a></h3> <br><br><br>
                </td>
                <td style="text-align: center; background-color: cornflowerblue; border-color: midnightblue" colspan="1">
                    <table class="pro" style="width: 100%;">
                        <tr style="text-align: center;">
                            <td style="color: white; background-color: royalblue;"><b><h2><%= formattedDate %></h2></b></td>
                        </tr>
                        <tr><td></td></tr>
                        <tr><td></td></tr>
                        <tr>
                            <td style="color: white;"><b><h2><%= formattedTime %></h2></b></td>
                        </tr>
                    </table>
                </td>

            </tr>
            <tr>
                <td>
                    <a><b>Regista: </b><%=film.getRegista()%></a> <br>
                    <a><b>Cast: </b><%=film.getCast()%></a> <br>
                    <a><b>Genere: </b><%=film.getGenere()%></a> <br>
                    <a><b>Durata: </b><%=film.getDurata()%>'</a> <br>
                    <a><%=film.getNazione()%></a>,<a><%=film.getAnno()%></a><br><br>
                </td>
                <td style="color: white; background-color: royalblue; text-align: center;"><b><h2>SALA <%=proiezione.getSala().getNum_sala()%></h2></b></td>
            </tr>
        </table>

        <table style="margin: auto">
            <tr>
                <td>
                    <section id="bigliettoButtonSection">
                        <a> <input type="button" id="bigliettoButton" name="bigliettoButton"
                                   class="button" value="Acquisto biglietto"
                                   onclick="acqbiglietto(<%=film.getCod_film()%>,<%=proiezione.getSala().getNum_sala()%>,<%=proiezione.getCod_pro()%>)"/></a>
                    </section>
                </td>
                <!-- Se l'utente ha già un abbonamento, non può comprarne un'altro -->
                <%if (acquista_abb == null || acquista_abb.isDeleted()) {%>
                <td>
                <section id="acqabbFormSection">
                    <form name="acqabbForm" action="Dispatcher" method="post">
                        <a> <input type="submit" class="button" value="Acquisto abbonamento"/></a>
                        <input type="hidden" name="controllerAction" value="GestioneAcquisti.acqabb"/>
                    </form>
                </section>
                </td>
                <%}%>
                <!-- Se l'utente possiede un abbonamento, può utilizzarlo-->
                <%if (acquista_abb != null && !acquista_abb.isDeleted()) {%>
                <td>
                <section id="useabbFormSection">
                    <a> <input type="button" id="useabbButton" name="useabbButton"
                               class="button" value="Acquisto biglietto tramite abbonamento"
                               onclick="acqbigliettoabb(<%=film.getCod_film()%>,<%=proiezione.getSala().getNum_sala()%>,<%=proiezione.getCod_pro()%>)"/></a>
                </section>
                </td>
                <%}%>
            </tr>
        </table>






        <form name="acqbigliettoForm" method="post" action="Dispatcher">
            <input type="hidden" name="selectedcodfilm"/>
            <input type="hidden" name="num_sala"/>
            <input type="hidden" name="cod_pro"/>
            <input type="hidden" name="formattedDate" value="<%=formattedDate%>"/>
            <input type="hidden" name="formattedTime" value="<%=formattedTime%>"/>
            <input type="hidden" name="controllerAction" value="GestioneAcquisti.sceltapostiView"/>
        </form>

        <form name="acqbigliettoabbForm" method="post" action="Dispatcher">
            <input type="hidden" name="selectedcodfilm"/>
            <input type="hidden" name="num_sala"/>
            <input type="hidden" name="cod_pro"/>
            <input type="hidden" name="formattedDate" value="<%=formattedDate%>"/>
            <input type="hidden" name="formattedTime" value="<%=formattedTime%>"/>
            <input type="hidden" name="controllerAction" value="GestioneAcquisti.sceltapostiViewabb"/>
        </form>

    </main>
</body>
</html>
