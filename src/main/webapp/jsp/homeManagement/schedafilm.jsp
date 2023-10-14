<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="com.starcinema.starcinema.model.mo.Recensione" %>
<%@ page import="com.starcinema.starcinema.model.mo.Proiezione" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%  int i = 0;
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "SchedaFilm";

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
            document.insrecForm.submit();
        }

        /*function goback() {
            document.backForm.submit();
        }*/

        function validateForm() {
            var voto = document.getElementById("voto").value;
            var commento = document.getElementById("commento").value;

            if (commento != null && voto == null) {
                alert("Per commentare devi inserire il voto.");
                return false; // Blocca l'invio del modulo se è stato scritto un commento senza voto
            }

            // Altrimenti, il modulo verrà inviato normalmente
            return true;
        }

        function mainOnLoadHandler() {
            document.querySelector("#insrecButton").addEventListener("click", submitRec);
            /*document.recForm.addEventListener("submit", function (event) {
                if (!validateForm()) {
                    event.preventDefault(); // Blocca l'invio del modulo se la validazione fallisce
                }
                submitRec()
            });*/

            //document.regForm.backButton.addEventListener("click", goback);
        }

        function deleteRec(cod_rec,selectedcodfilm) {
            document.deleteForm.cod_rec.value = cod_rec;
            document.deleteForm.selectedcodfilm.value = selectedcodfilm;
            document.deleteForm.submit();
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
        <select id="dataProMenu">
            <option value="data1">Data 1</option>
            <option value="data2">Data 2</option>
            <option value="data3">Data 3</option>
        </select>

        <!-- Menu a tendina per ora_pro -->
        <label for="oraProMenu">Seleziona Ora di Proiezione:</label>
        <select id="oraProMenu">
            <option value="ora1">Ora 1</option>
            <option value="ora2">Ora 2</option>
            <option value="ora3">Ora 3</option>
        </select>
        <input type="button" id="acqButton" name="acqButton"
               class="button" value="Acquista" onclick="submitAcq()">
            </form>
        </section>
        <%}%>

        <h2>Orari di Proiezione:</h2>
        <%
            Date lastDataPro = null; // Memorizza l'ultima data_pro stampata
        %>
        <% for (int c = 0; c < film.getProiezioni().length; c++) {
            Proiezione proiezione = film.getProiezioni(c);
            Date dataPro = proiezione.getData_pro();

            // Controlla se la data_pro è diversa dall'ultima data_pro stampata
            if (lastDataPro == null || !lastDataPro.equals(dataPro)) {
                // Memorizza la nuova data_pro
                lastDataPro = dataPro;
        %>
        <%
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = dateFormat.format(dataPro);
        %>
        <h3>Data di Proiezione: <%= formattedDate %></h3>
        <%}%>
        <%
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = timeFormat.format(proiezione.getOra_pro());
        %>
        <a>Ora di Proiezione: <%= formattedTime %></a><br>
        <%}%>




        <%if (loggedOn) {%>
        <!-- Possibilità di inserire recensioni -->
        <section id="insrecFormSection">
            <form name="insrecForm" action="Dispatcher" method="post" onsubmit="return validateForm()">
                <h3>Lascia una recensione</h3>
                <input type="hidden" name="selectedcodfilm" value="<%= film.getCod_film() %>">
                <input type="hidden" name="controllerAction" value="HomeManagement.insrec"/>
                <label for="voto">Voto: </label>
                <input type="number" id="voto" name="voto" min="1" max="5" required>
                <br>
                <label for="commento">Recensione: </label>
                <textarea id="commento" name="commento" rows="4" required></textarea>
                <br>
                <input type="button" id="insrecButton" name="insrecButton"
                       class="button" value="Invia recensione" onclick="submitRec()">

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

    </main>
</body>
</html>
