<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="com.starcinema.starcinema.model.mo.Recensione" %>
<%@ page import="com.starcinema.starcinema.model.mo.Proiezione" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Time" %>

<%  int i = 0;
    String formattedTime;
    String data_pro=null;
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "SchedaFilm";

    String formattedDate = (String) request.getAttribute("formattedDate");
    List<Film> films = (List<Film>) request.getAttribute("films");
    List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioni");
    Film film = (Film) request.getAttribute("film");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@include file="/include/htmlHead.inc"%>
    <style>

        /* Menù a tendina */
        select {
            width: 125px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            font-size: 16px;
        }


        select option {
            background-color: #fff;
            color: #333;
            padding: 5px;
        }

        /* CSS per il textarea dei commenti */
        textarea#commento {
            width: 50%; /* Imposta la larghezza al 100% del contenitore padre */
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            resize: none; /* Impedisce il ridimensionamento */
        }

        /* Stile per l'etichetta del commento */
        label[for="commento"] {
            font-weight: bold;
            margin-top: 10px;
        }

        /* Recensioni */

        /* Stili per la tabella */
        table {
            width: 40%; /* Imposta la larghezza della tabella al 100% del contenitore padre */
            border-collapse: collapse; /* Unisci i bordi delle celle */
            border-radius: 10px;
            overflow: hidden;
        }

        /* Stili per gli elementi delle righe della tabella */
        tr {
            border: 1px solid #ccc; /* Aggiunge un bordo a tutte le righe */
        }

        /* Stili per gli elementi delle celle dei dati (colonna di sinistra) */
        td:first-child {
            width: 50px; /* Larghezza fissa per la prima colonna (colonna di sinistra) */
        }

        /* Altri stili per le celle delle intestazioni e dei dati */
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
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
            width: 200px; /* Imposta la larghezza desiderata */
            height: 300px; /* Imposta l'altezza desiderata */
            object-fit: cover; /* Opzionale: scala e taglia l'immagine per adattarla alle dimensioni specificate */
        }

        /* Bottone per il trailer */

        .button {
            background-color: midnightblue;
        }

        /* Bottone per acquista */

        .buttonacq {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 16px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            transition-duration: 0.4s;
            cursor: pointer;
        }

        .buttonacq:hover {
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
        }

        .buttonacq {
            background-color: #008CBA;
            color: white;
            border: 2px solid white;
        }


    </style>
    <script language="javascript">

        function submitRec() {
            var f;
            f = document.insrecForm;
            f.controllerAction.value = "HomeManagement.insrec";
        }


        function mainOnLoadHandler() {
            document.insrecForm.addEventListener("submit", submitRec());
        }

        function deleteRec(cod_rec,selectedcodfilm) {
            document.deleteForm.cod_rec.value = cod_rec;
            document.deleteForm.selectedcodfilm.value = selectedcodfilm;
            document.deleteForm.submit();
        }

        function menuData(selectedcodfilm){
            var selectedDateElement = document.getElementById("dataProMenu");
            var formattedDate = selectedDateElement.value;
            if (formattedDate != null && formattedDate != "nul") {
                document.menuDataForm.selectedcodfilm.value = selectedcodfilm;
                document.menuDataForm.formattedDate.value = formattedDate;
                document.menuDataForm.submit();
            }
        }

        function submitAcq(selectedcodfilm){
            var selectedDateElement = document.getElementById("dataProMenu");
            var formattedDate = selectedDateElement.value;
            var selectedTimeElement = document.getElementById("oraProMenu");
            var formattedTime = selectedTimeElement.value;
            if (formattedDate != null && formattedDate != "nul") {
            document.submitAcqForm.selectedcodfilm.value = selectedcodfilm;
            document.submitAcqForm.formattedDate.value = formattedDate;
            document.submitAcqForm.formattedTime.value = formattedTime;
            document.submitAcqForm.submit();
            }
        }

    </script>
</head>
<body>
    <main>

        <table class="film">
            <tr>
                <td>
                    <%if (film.getImmagine()!=null) {%>
                    <img src="<%=film.getImmagine()%>">
                    <%} else {%>
                    <img src="images/imgnotfound.jpg">
                    <%}%>
                </td>
                <td>
                    <h1><a><b><%=film.getTitolo()%></b></a></h1> <br><br><br>
                    <a><b>Regista: </b><%=film.getRegista()%></a> <br>
                    <a><b>Cast: </b><%=film.getCast()%></a> <br>
                    <a><b>Genere: </b><%=film.getGenere()%></a> <br>
                    <a><b>Durata: </b><%=film.getDurata()%>'</a> <br>
                    <a><b>Nazione: </b><%=film.getNazione()%></a> <br>
                    <a><b>Anno: </b><%=film.getAnno()%></a>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;">
                    <button onclick="window.location.href='<%=film.getTrailer()%>'" class="button">Guarda il trailer</button>
                </td>
                <td></td>
            </tr>
            <tr>
                <td colspan="2">
                    <a><b>Descrizione: </b><%=film.getDescrizione()%></a>
                </td>
            </tr>
        </table>

        <br><br>
        <!-- Sezione dedicata agli utenti registrati e amministratore!-->

        <%if (loggedOn && loggedUtente.getTipo().equals("utente")) {%>
        <section id="datiacqFormSection">
            <form name="datiacqForm" action="Dispatcher" method="post">
                <!-- Menu a tendina per data_pro -->
                <label for="dataProMenu">Seleziona Data di Proiezione:</label>
                <select id="dataProMenu" name="formattedDate" class="dropdown-menu" onchange="menuData(<%=film.getCod_film()%>)">
                    <%if (formattedDate != null){%>
                    <option value="<%=formattedDate%>"><%=formattedDate%></option><%} else {%>
                    <option value="nul"></option>
                    <%
                        Date lastDataPro = null; // Memorizza l'ultima data_pro stampata
                    %>
                    <% if (film.getProiezioni() != null) { %>
                    <% for (int c = 0; c < film.getProiezioni().length; c++) {
                        Proiezione proiezione = film.getProiezioni(c);
                        if(proiezione != null){
                        Date dataPro = proiezione.getData_pro();
                        System.out.println("Data Proiezione: " + dataPro);
                        // Controlla se la data_pro è diversa dall'ultima data_pro stampata
                        if (lastDataPro == null || !lastDataPro.equals(dataPro)) {
                            // Memorizza la nuova data_pro
                            lastDataPro = dataPro;
                    %>
                    <%
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        if (dataPro != null) {
                            data_pro = dateFormat.format(dataPro);
                        }
                    %>

                    <option value="<%= data_pro %>"><%= data_pro %></option>
                    <%}%><%}%><%}%><%}%><%}%>

                </select>

                <!-- Menu a tendina per ora_pro -->
                <label for="oraProMenu">Seleziona Ora di Proiezione:</label>
                <select id="oraProMenu" class="dropdown-menu">
                    <% if (film.getProiezioni() != null) { %>
                    <% for (int c = 0; c < film.getProiezioni().length; c++) {
                        Proiezione proiezione = film.getProiezioni(c);

                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        formattedTime = timeFormat.format(proiezione.getOra_pro());
                    %>
                    <option value="<%= formattedTime %>"><%= formattedTime %></option>
                    <%}%><%}%>
                </select>

        <input type="button" id="acqButton" name="acqButton"
               class="buttonacq" value="Acquista"
               onclick="submitAcq(<%=film.getCod_film()%>)">
            </form>
        </section>
        <%}%>


        <br><br>
        <%if (loggedOn) {%>
        <!-- Possibilità di inserire recensioni -->
        <section id="insrecFormSection">
            <form name="insrecForm" action="Dispatcher" method="post">
                <h3>Lascia una recensione</h3><br>
                <input type="hidden" name="selectedcodfilm" value="<%= film.getCod_film() %>">
                <input type="hidden" name="controllerAction" value="HomeManagement.insrec"/>
                <label for="voto">Voto: </label>
                <input type="number" id="voto" name="voto" min="1" max="5" required>
                <br><br>
                <textarea id="commento" name="commento" rows="4" required placeholder="Scrivi un commento...."></textarea>
                <br><br>
                <input type="submit" class="button" value="Invia recensione">
                <input type="hidden" name="controllerAction"/>
            </form>
        </section>
        <%}%>
        <br><br><br>
        <!-- Sezione dedicata ai commenti -->
        <% if (recensioni != null) { %>
        <section id="commentSection">
            <h3>Commenti (<%=recensioni.size()%>)<br><br><br></h3>

                <% for (i = 0; i < recensioni.size(); i++) { %>

                    <table>
                    <tr>
                        <td align="right" style="width: 7%"><img id="user" src="images/user.jpg" width="23" height="20"></td>
                    <!--<b>Codrec: </b><%= recensioni.get(i).getCod_rec() %><br>-->
                    <td align="left"><b style="color: black;"><%= recensioni.get(i).getUtente().getUsername() %></b>
                        &nbsp;&nbsp;Voto: <%= recensioni.get(i).getVoto() %><br></td>
                        <td align="right" style="width: 7%"><%if (loggedUtente != null && loggedUtente.getTipo().equals("amministratore")) {%>
                            <!-- Possibilità di cancellare le recensioni -->

                            <right title="CANCELLA"><img id="trashcan" src="images/trashcan.png"
                                 onclick="deleteRec(<%=recensioni.get(i).getCod_rec()%>,<%=film.getCod_film()%>)" width="22" height="22"/></right>
                            <%}%></td>
                    </tr>
                    <tr><td></td>
                       <td align="left" colspan="3"><%= recensioni.get(i).getCommento() %><br></td>
                    </tr>
                    </table><br><br>
            <% } %>

        </section>
        <br><br>
        <% } %>
        <br><br><br>

        <form name="deleteForm" method="post" action="Dispatcher">
            <input type="hidden" name="cod_rec"/>
            <input type="hidden" name="selectedcodfilm"/>
            <input type="hidden" name="controllerAction" value="HomeManagement.deleterec"/>
        </form>

        <form name="menuDataForm" method="post" action="Dispatcher">
            <input type="hidden" name="selectedcodfilm"/>
            <input type="hidden" name="formattedDate"/>
            <input type="hidden" name="controllerAction" value="HomeManagement.menuData"/>
        </form>

        <form name="submitAcqForm" method="post" action="Dispatcher">
            <input type="hidden" name="selectedcodfilm"/>
            <input type="hidden" name="formattedDate"/>
            <input type="hidden" name="formattedTime"/>
            <input type="hidden" name="controllerAction" value="HomeManagement.acquista"/>
        </form>

    </main>
</body>
</html>
