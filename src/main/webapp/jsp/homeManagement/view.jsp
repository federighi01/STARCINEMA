<%@page session="false"%>
<%@page import="com.starcinema.starcinema.model.mo.Utente"%>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.starcinema.starcinema.model.mo.Proiezione" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%  int i = 0, c = 0;

    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Home";


    String titolo = (String) request.getAttribute("titolo");
    List<Film> films = (List<Film>) request.getAttribute("films");
    Film film = (Film) request.getAttribute("film");
    List<Film> filmsdp = (List<Film>) request.getAttribute("filmsdp");

%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/include/htmlHead.inc"%>
    <style>
        #films {
            margin: 12px 0;
        }

        #films article {
            float: left;
            width: 250px;
            border-width: 1px;
            border-style: solid;
            border-radius: 10px;
            border-color: #a3271f;
            padding: 10px 8px 10px 20px;
            margin: 0 18px 16px 0;
            background: linear-gradient(to right,#fdfbfb,#ebedee);
            box-shadow: 0 3px 2px #777;
        }

        #films article h1 a {
            color: #a3271f;
        }
    </style>
        <script language="javascript">

            function viewfilm(cod_film) {
                document.schedafilmForm.selectedcodfilm.value = cod_film;
                document.schedafilmForm.submit();
            }

            function mainOnLoadHandler() {
                document.querySelector("#schedaButton").addEventListener("click", viewfilm);
                //anche scheda3button?
            }
        </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>
    <%if (loggedOn) {%>
    Benvenuto <%=loggedUtente.getCognome()%> <%=loggedUtente.getNome()%>!<br/>
    work in progress...
    <%} else {%>
    Benvenuto.
    Fai il logon per accedere all'area riservata.
    <%}%>
    <br><br>
    <section id="findfilm" class="clearfix">
        <form name="findfilmForm" action="Dispatcher" method="post">
            <label for="titolo">Cerca per titolo</label>
            <input type="text" id="titolo"  name="titolo" maxlength="80" required>

            <input type="submit" value="Cerca">
            <input type="hidden" name="controllerAction" value="HomeManagement.view"/>
        </form>
    </section>
    <br>
    <section id="finddata" class="clearfix">
        <form name="finddataForm" action="Dispatcher" method="post">
            <label for="data_pro">Cerca per data di calendario</label>
            <input type="date" id="data_pro" name="data_pro">

            <input type="submit" value="Cerca">
            <input type="hidden" name="controllerAction" value="HomeManagement.view">
        </form>
    </section>
    <br><br><br>
    STARCINEMA CONSIGLIA
    <br><br>



    <%--view senza condizioni di ricerca--%>
    <%if (titolo==null && filmsdp==null) {%>
    <section id="films" class="clearfix">
        <%for (i = 0; i < films.size(); i++) {%>
        <article>
            <h1><a><%=films.get(i).getTitolo()%></a></h1> <br>
            <a><b>Regista: </b><%=films.get(i).getRegista()%></a> <br>
            <a><b>Cast: </b><%=films.get(i).getCast()%></a> <br>
            <a><b>Genere: </b><%=films.get(i).getGenere()%></a> <br>
            <a><b>Durata: </b><%=films.get(i).getDurata()%>'</a> <br>


            <!-- Ciclo per stampare le date e le ore di proiezione del film corrente -->
            <h2>Orari di Proiezione:</h2>
            <%
                Date lastDataPro = null; // Memorizza l'ultima data_pro stampata
            %>
            <% for (c = 0; c < films.get(i).getProiezioni().length; c++) {
                Proiezione proiezione = films.get(i).getProiezioni(c);
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

            <%--<%if (loggedOn && loggedUtente.getTipo().equals("utente")) {%>--%>


            <input type="hidden" name="filmTitolo" value="<%=films.get(i).getTitolo()%>">
            <input type="hidden" name="filmRegista" value="<%=films.get(i).getRegista()%>">
            <input type="hidden" name="filmCast" value="<%=films.get(i).getCast()%>">
            <input type="hidden" name="filmGenere" value="<%=films.get(i).getGenere()%>">
            <input type="hidden" name="filmDurata" value="<%=films.get(i).getDurata()%>">
            <input type="hidden" name="filmNazione" value="<%=films.get(i).getNazione()%>">
            <input type="hidden" name="filmAnno" value="<%=films.get(i).getAnno()%>">
            <input type="hidden" name="filmDescrizione" value="<%=films.get(i).getDescrizione()%>">
            <input type="hidden" name="filmTrailer" value="<%=films.get(i).getTrailer()%>">

            <section id="schedaButtonSection">
                <a> <input type="button" id="schedaButton" name="schedaButton"
                           class="button" value="Visualizza scheda film" onclick="viewfilm(<%=films.get(i).getCod_film()%>)"/></a>
            </section>
            <%--<%}%>--%>
        </article>
        <br><br><br>
        <%}%>
    </section>



    <%--ricerca film per titolo--%>
    <%}%><% if(titolo != null && film != null){%>
    <section id="films" class="clearfix">
    <article>
        <h1><a><%=film.getTitolo()%></a></h1> <br>
        <a><b>Regista: </b><%=film.getRegista()%></a> <br>
        <a><b>Cast: </b><%=film.getCast()%></a> <br>
        <a><b>Genere: </b><%=film.getGenere()%></a> <br>
        <a><b>Durata: </b><%=film.getDurata()%>'</a> <br>

        <h2>Orari di Proiezione:</h2>
        <%
            Date lastDataPro = null; // Memorizza l'ultima data_pro stampata
        %>
        <% for (c = 0; c < film.getProiezioni().length; c++) {
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


        <input type="hidden" name="filmId" value="<%=film.getCod_film()%>">
        <input type="hidden" name="filmTitolo" value="<%=film.getTitolo()%>">

        <section id="scheda2ButtonSection">
            <a> <input type="button" id="scheda2Button" name="scheda2Button"
                       class="button" value="Visualizza scheda film" onclick="viewfilm(<%=film.getCod_film()%>)"/></a>
        </section>
        <%--<%}%>--%>
    </article>
    </section>



    <%--ricerca film per data proiezione--%>
    <%}%><% if(filmsdp != null && titolo==null){%>
    <section id="films" class="clearfix">
        <%for (i = 0; i < filmsdp.size(); i++) {%>
        <article>
            <h1><a><%=filmsdp.get(i).getTitolo()%></a></h1> <br>
            <a><b>Regista: </b><%=filmsdp.get(i).getRegista()%></a> <br>
            <a><b>Cast: </b><%=filmsdp.get(i).getCast()%></a> <br>
            <a><b>Genere: </b><%=filmsdp.get(i).getGenere()%></a> <br>
            <a><b>Durata: </b><%=filmsdp.get(i).getDurata()%>'</a> <br>
            <%--<a><b>Nazione: </b><%=filmsdp.get(i).getNazione()%></a> <br>
            <a><b>Anno: </b><%=filmsdp.get(i).getAnno()%></a> <br>
            <a><b>Descrizione: </b><%=filmsdp.get(i).getDescrizione()%></a> <br>
            <a href=<%=filmsdp.get(i).getTrailer()%>>Clicca qui per il trailer</a>--%>
            <%--<%if (loggedOn && loggedUtente.getTipo().equals("utente")) {%>--%>

            <input type="hidden" name="filmId" value="<%=i%>">
            <input type="hidden" name="filmTitolo" value="<%=filmsdp.get(i).getTitolo()%>">

            <section id="scheda3ButtonSection">
                <a> <input type="button" id="scheda3Button" name="scheda3Button"
                           class="button" value="Visualizza scheda film" onclick="viewfilm(<%=filmsdp.get(i).getCod_film()%>)"/></a>
            </section>
            <%--<%}%>--%>
        </article>
        <br><br><br>
        <%}%>
    </section>

    <%}%>
    <form name="schedafilmForm" method="post" action="Dispatcher">
        <input type="hidden" name="selectedcodfilm"/>
        <input type="hidden" name="controllerAction" value="HomeManagement.schedafilm"/>
    </form>



</main>
</body>
<%--<%@include file="/include/footer.inc"%>--%>
</html>
