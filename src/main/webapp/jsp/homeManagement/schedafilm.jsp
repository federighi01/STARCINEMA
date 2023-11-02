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
        <h1>Scheda Film</h1>
        <br><br>
        <a><b>Titolo: </b><%=film.getTitolo()%></a></h1> <br>
        <a><b>Regista: </b><%=film.getRegista()%></a> <br>
        <a><b>Cast: </b><%=film.getCast()%></a> <br>
        <a><b>Genere: </b><%=film.getGenere()%></a> <br>
        <a><b>Durata: </b><%=film.getDurata()%>'</a> <br>
        <a><b>Nazione: </b><%=film.getNazione()%></a> <br>
        <a><b>Anno: </b><%=film.getAnno()%></a> <br>
        <a><b>Descrizione: </b><%=film.getDescrizione()%></a> <br>
        <a href=<%=film.getTrailer()%>>Clicca qui per il trailer</a>
        <br><br>
        <!-- Sezione dedicata agli utenti registrati e amministratore!-->

        <%if (loggedOn && loggedUtente.getTipo().equals("utente")) {%>
        <section id="datiacqFormSection">
            <form name="datiacqForm" action="Dispatcher" method="post">
                <!-- Menu a tendina per data_pro -->
                <label for="dataProMenu">Seleziona Data di Proiezione:</label>
                <select id="dataProMenu" name="formattedDate" onchange="menuData(<%=film.getCod_film()%>)">
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
                <select id="oraProMenu">
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
               class="button" value="Acquista"
               onclick="submitAcq(<%=film.getCod_film()%>)">
            </form>
        </section>
        <%}%>



        <%if (loggedOn) {%>
        <!-- Possibilità di inserire recensioni -->
        <section id="insrecFormSection">
            <form name="insrecForm" action="Dispatcher" method="post">
                <h3>Lascia una recensione</h3>
                <input type="hidden" name="selectedcodfilm" value="<%= film.getCod_film() %>">
                <input type="hidden" name="controllerAction" value="HomeManagement.insrec"/>
                <label for="voto">Voto: </label>
                <input type="number" id="voto" name="voto" min="1" max="5" required>
                <br>
                <label for="commento">Recensione: </label>
                <textarea id="commento" name="commento" rows="4" required></textarea>
                <br>
                <input type="submit" class="button" value="Invia recensione">
                <input type="hidden" name="controllerAction"/>
            </form>
        </section>
        <%}%>

        <!-- Sezione dedicata ai commenti -->
        <% if (recensioni != null) { %>
        <section id="commentSection">
            <h3>Commenti<br></h3>
            <ul>
                <% for (i = 0; i < recensioni.size(); i++) { %>
                <li>

                    <b>Codrec: </b><%= recensioni.get(i).getCod_rec() %><br>
                    <b>Utente: </b><%= recensioni.get(i).getUtente().getUsername() %><br>
                    <b>Voto: </b><%= recensioni.get(i).getVoto() %><br>
                    <b>Commento: </b><%= recensioni.get(i).getCommento() %><br>
                    <%if (loggedUtente != null && loggedUtente.getTipo().equals("amministratore")) {%>
                    <!-- Possibilità di cancellare le recensioni -->

                        <img id="trashcan" src="images/trashcan.png"
                             onclick="deleteRec(<%=recensioni.get(i).getCod_rec()%>,<%=film.getCod_film()%>)" width="22" height="22"/>
                    <%}%>
                </li>
                <% } %>
            </ul>
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
