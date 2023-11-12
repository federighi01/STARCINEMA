<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
    <style>

        body, html {
            background-image: linear-gradient(101deg, rgba(254, 254, 254, 0.05) 0%, rgba(254, 254, 254, 0.05) 69%,rgba(160, 160, 160, 0.05) 69%, rgba(160, 160, 160, 0.05) 100%),linear-gradient(239deg, rgba(102, 102, 102, 0.02) 0%, rgba(102, 102, 102, 0.02) 60%,rgba(67, 67, 67, 0.02) 60%, rgba(67, 67, 67, 0.02) 100%),linear-gradient(121deg, rgba(169, 169, 169, 0.06) 0%, rgba(169, 169, 169, 0.06) 89%,rgba(189, 189, 189, 0.06) 89%, rgba(189, 189, 189, 0.06) 100%),linear-gradient(371deg, rgba(213, 213, 213, 0.04) 0%, rgba(213, 213, 213, 0.04) 45%,rgba(66, 66, 66, 0.04) 45%, rgba(66, 66, 66, 0.04) 100%),linear-gradient(228deg, rgba(223, 223, 223, 0.01) 0%, rgba(223, 223, 223, 0.01) 82%,rgba(28, 28, 28, 0.01) 82%, rgba(28, 28, 28, 0.01) 100%),linear-gradient(48deg, rgba(20, 20, 20, 0.06) 0%, rgba(20, 20, 20, 0.06) 62%,rgba(136, 136, 136, 0.06) 62%, rgba(136, 136, 136, 0.06) 100%),linear-gradient(245deg, rgba(206, 206, 206, 0.09) 0%, rgba(206, 206, 206, 0.09) 58%,rgba(6, 6, 6, 0.09) 58%, rgba(6, 6, 6, 0.09) 100%),linear-gradient(349deg, rgba(162, 162, 162, 0.07) 0%, rgba(162, 162, 162, 0.07) 27%,rgba(24, 24, 24, 0.07) 27%, rgba(24, 24, 24, 0.07) 100%),linear-gradient(231deg, rgba(166, 166, 166, 0.04) 0%, rgba(166, 166, 166, 0.04) 5%,rgba(210, 210, 210, 0.04) 5%, rgba(210, 210, 210, 0.04) 100%),linear-gradient(135deg, rgba(79,213,255, 0.9),rgba(60,135,255, 0.96),rgb(58,183,244));        }

        main {
            background-image: linear-gradient(101deg, rgba(254, 254, 254, 0.05) 0%, rgba(254, 254, 254, 0.05) 69%,rgba(160, 160, 160, 0.05) 69%, rgba(160, 160, 160, 0.05) 100%),linear-gradient(239deg, rgba(102, 102, 102, 0.02) 0%, rgba(102, 102, 102, 0.02) 60%,rgba(67, 67, 67, 0.02) 60%, rgba(67, 67, 67, 0.02) 100%),linear-gradient(121deg, rgba(169, 169, 169, 0.06) 0%, rgba(169, 169, 169, 0.06) 89%,rgba(189, 189, 189, 0.06) 89%, rgba(189, 189, 189, 0.06) 100%),linear-gradient(371deg, rgba(213, 213, 213, 0.04) 0%, rgba(213, 213, 213, 0.04) 45%,rgba(66, 66, 66, 0.04) 45%, rgba(66, 66, 66, 0.04) 100%),linear-gradient(228deg, rgba(223, 223, 223, 0.01) 0%, rgba(223, 223, 223, 0.01) 82%,rgba(28, 28, 28, 0.01) 82%, rgba(28, 28, 28, 0.01) 100%),linear-gradient(48deg, rgba(20, 20, 20, 0.06) 0%, rgba(20, 20, 20, 0.06) 62%,rgba(136, 136, 136, 0.06) 62%, rgba(136, 136, 136, 0.06) 100%),linear-gradient(245deg, rgba(206, 206, 206, 0.09) 0%, rgba(206, 206, 206, 0.09) 58%,rgba(6, 6, 6, 0.09) 58%, rgba(6, 6, 6, 0.09) 100%),linear-gradient(349deg, rgba(162, 162, 162, 0.07) 0%, rgba(162, 162, 162, 0.07) 27%,rgba(24, 24, 24, 0.07) 27%, rgba(24, 24, 24, 0.07) 100%),linear-gradient(231deg, rgba(166, 166, 166, 0.04) 0%, rgba(166, 166, 166, 0.04) 5%,rgba(210, 210, 210, 0.04) 5%, rgba(210, 210, 210, 0.04) 100%),linear-gradient(135deg, rgba(79,213,255, 0.9),rgba(60,135,255, 0.96),rgb(58,183,244));        }


        table.empty {
            border-collapse: collapse;
            border: none;
            box-shadow: none;
        }

        table.empty td {
            border: none;
        }

        .mex {
            width: 40%;
            text-align: center;
            border-collapse: separate;
            border-spacing: 5px; /* Aggiunge uno spazio tra le celle */
            background-color: white;
            color: black;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Effetto ombra */
            border-radius: 10px; /* Angoli arrotondati */
            margin: 10px auto 0;
            margin-bottom: -20px;
        }

        .acq {
            background-color: white;
            border-collapse: collapse;
            width: 55%;
            border: 2px solid #ddd;
            border-radius: 10px; /* Angoli arrotondati */
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Effetto ombra */
        }

        .acq td {
            padding: 10px; /* Aggiunge uno spazio interno di 10px a ogni cella */
        }

        .acq td, tr {
            border: none;
        }

        .acq input.button {
            margin: 0 auto; /* Aggiunge un margine destro di 10px al pulsante */
        }

        .acq img {
            width: 200px;
            height: 200px;
            object-fit: cover; /* Scala e taglia l'immagine per adattarla alle dimensioni specificate */
        }

    </style>
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
        <%if (acquisti != null && proiezioni != null && films != null) {%>

                <%--<table class="mex">
                    <tr>
                        <td>
                            <%= "Hai effettuato " + acquisti.size() + " acquisti"%>
                        </td>
                    </tr>
                </table>
                <br><br><br>--%>
            <%for (int i = 0; i < acquisti.size(); i++) {%>
                <%if (acquisti.get(i).getUtente().getUsername().equals(loggedUtente.getUsername())) {%>
                <% c++;%> <!-- contatore per vedere se l'utente loggato ha effettuato acquisti -->
                <table class="acq">
                    <tr>
                        <td style="text-align: center; color: black; background-color: burlywood" colspan="3"><h3><b><%= "Acquisto n° " + c%></b></h3></td>
                    </tr>
                    <tr>
                        <td rowspan="5"><img src="images/ticket.jpg"></td>
                        <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                           String dataPro = dateFormat.format(proiezioni.get(i).getData_pro()); %>
                        <td><b>Data di proiezione: </b> <%=dataPro%><br></td>
                    </tr>
                    <tr>
                        <% SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                           String oraPro = timeFormat.format(proiezioni.get(i).getOra_pro()); %>
                        <td><b>Ora di proiezione: </b> <%=oraPro%><br></td>
                    </tr>
                    <tr>
                        <td><b>Titolo del film: </b> <%=films.get(i).getTitolo()%><br></td>
                    </tr>
                    <tr>
                        <td><b>Numero sala: </b> <%=proiezioni.get(i).getSala().getNum_sala()%><br></td>
                        <td rowspan="2">
                            <!-- Invio dati da modificare -->
                            <input type="button" class="button" value="Modifica acquisto"
                                   onclick="datitochange('<%=acquisti.get(i).getFilm().getCod_film()%>','<%=acquisti.get(i).getProiezione().getCod_pro()%>','<%=acquisti.get(i).getPosto().getNum_posto()%>')">
                        </td>
                    </tr>
                    <tr>
                        <td><b>Posto acquistato: </b><%=acquisti.get(i).getPosto().getNum_posto()%></td>
                    </tr>
                </table>

                <br><br>
                <%}%>
            <%}%>
                <% if (c == 0) {%>
                <table class="empty" style="margin-top: 50px; margin-left: 470px">
                    <tr>
                        <td style="text-align: center; margin-bottom: 100px; padding-right: 30px"><h1><b style="color: white">Nessun acquisto effettuato</b></h1></td>
                    </tr>
                    <tr>
                        <td style="text-align: center; padding-right: 30px"><img src="images/emptycart.png"></td>
                    </tr>
                </table>
                <%}%>
        <%}%>
                <input type="hidden" name="cod_film_old">
                <input type="hidden" name="cod_pro_old">
                <input type="hidden" name="num_posto_old">
                <input type="hidden" name="controllerAction" value="ModificaAcquisti.modview"/>
            </form>
        </section>

    </main>
</body>
<%@include file="/include/footer.inc"%>
</html>
