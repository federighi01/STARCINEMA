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
    List<Proiezione> pro = (List<Proiezione>) request.getAttribute("pro");

%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/include/htmlHead.inc"%>
    <style>

        h2 {
            margin-left: 20px; /* Modifica il valore a seconda di quanto vuoi spostare il testo verso destra */
            color: black;
        }

        #findfilm form {
            background: #f7f7f7;
            width: 550px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 15px;
            margin: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        #findfilm label {
            font-weight: bold;
        }

        table {

            width: 100%;
            border-collapse: collapse;
        }

        table tr {
            border-bottom: none;
        }

        table td {
            padding: 10px;
        }

        table td:first-child {
            text-align: left;
            width: 40%;
        }

        table input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        table input[type="submit"] {
            background-color: royalblue;
            color: white;
            padding: 10px 20px;
            margin-left: 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }


        /* Style per ricerca per data pro */

        #finddata form {
            background: #f7f7f7;
            width: 550px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 15px;
            margin: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        #finddata label {
            font-weight: bold;
        }

        table input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }


        /* Da eliminare */

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


        .film {
            background-color: white;
            width: 80%;
            border: 1px solid #ccc; /* Add border style */
            border-radius: 5px; /* Add border radius */
            padding: 10px; /* Add padding */
            margin: 10px; /* Add margin */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add box shadow */
        }

        .film tr {
            border: none; /* Add border for tr */
        }

        .film td {
            border: none; /* Add border for td */
            padding: 10px; /* Customize cell padding */
            text-align: left;
            width: 40%;
        }

        .film td:first-child {
            width: 150px; /* Larghezza fissa per la prima colonna (colonna di sinistra) */
        }

        .film img {
            width: 200px; /* Imposta la larghezza desiderata */
            height: 300px; /* Imposta l'altezza desiderata */
            object-fit: cover; /* Opzionale: scala e taglia l'immagine per adattarla alle dimensioni specificate */
        }

        .data-cell {
            width: 40%; /* Imposta una larghezza relativa del 40% */
        }

        .ora-cell {
            width: 10%; /* Imposta una larghezza relativa del 30% */
        }

        /* Stile tabella se titolo o data non trovato */

        .notfound {
            width: 40%;
            border-collapse: separate;
            border-spacing: 5px; /* Aggiunge uno spazio tra le celle */
            background-color: white;
            color: black;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Effetto ombra */
            border-radius: 10px; /* Angoli arrotondati */
            margin: 10px auto 0;
        }

    </style>
        <script language="javascript">

            function viewfilm(cod_film) {
                document.schedafilmForm.selectedcodfilm.value = cod_film;
                document.schedafilmForm.submit();
            }

            function addpro(cod_film) {
                document.addproForm.selectedcodfilm.value = cod_film;
                document.addproForm.submit();
            }

            function mainOnLoadHandler() {
                document.querySelector("#schedaButton").addEventListener("click", viewfilm);
                document.querySelector("#newproButton").addEventListener("click", addpro);
            }
        </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>
    <%if (loggedOn) {%>
    <table class="film" style="width: 30%">
        <tr>
            <td style="width: 0.1%"><img src="images/user.jpg" style="width: 30px; height: 30px"></td>
            <td>Benvenuto <%=loggedUtente.getCognome()%> <%=loggedUtente.getNome()%>!</td>
        </tr>
        <% if (loggedUtente.getTipo().equals("amministratore")) {%>
        <tr>
            <td colspan="2">Utilizza gli strumenti per amministratore</td>
        </tr>
        <%}%>
    </table>
    <%}%>
    <br><br>
    <table>
        <tr>
    <td><section id="findfilm" class="clearfix">
        <form name="findfilmForm" action="Dispatcher" method="post">
            <table>
                <tr>
                    <td><label for="titolo">Cerca per titolo</label><br></td>
                </tr>
                <tr>
                    <td><input type="text" id="titolo"  name="titolo" maxlength="80" required autocomplete="off" placeholder="Inserisci titolo da cercare"></td>
                    <td align="left"><input type="submit" value="Cerca"></td>
                <input type="hidden" name="controllerAction" value="HomeManagement.view"/>
                </tr>
            </table>
        </form>
    </section></td>
    <br>
    <td><section id="finddata" class="clearfix">
        <form name="finddataForm" action="Dispatcher" method="post">
            <table>
                <tr>
                    <td colspan="2"><label for="data_pro">Cerca per data di calendario</label><br></td>
                </tr>
                <tr>
                    <td><input type="date" id="data_pro" name="data_pro"></td>
                    <td><input type="submit" value="Cerca"></td>
                </tr>
                <input type="hidden" name="controllerAction" value="HomeManagement.view">
            </table>
        </form>
    </section></td>
        </tr>
    </table>
    <br><br><br>
    <h2><b style="color: white">STARCINEMA CONSIGLIA</b></h2>
    <br><br>



    <%--view senza condizioni di ricerca--%>
    <%if (titolo==null && filmsdp==null) {%>
    <section id="films" class="clearfix">
        <%for (i = 0; i < films.size(); i++) {%>
        <table class="film">
            <tr>
                <td rowspan="3" style="text-align: center;">
                    <%if (films.get(i).getImmagine()!=null) {%>
                    <img src="<%=films.get(i).getImmagine()%>">
                    <%} else {%>
                    <img src="images/imgnotfound.jpg">
                    <%}%>
                </td>
                <td><h1><a><%=films.get(i).getTitolo()%></a></h1><br></td>
                <!-- Ciclo per stampare le date e le ore di proiezione del film corrente -->
                <td rowspan="2"><h3>Orari di Proiezione:</h3>
                    <table class="date">
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
                        <tr><td class="data-cell"><h3><%= formattedDate %></h3></td>
                            <td class="ora-cell">
                    <%}%>
                    <%
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        String formattedTime = timeFormat.format(proiezione.getOra_pro());
                    %>
                    <a><%= formattedTime %></a><br>
                    <%}%>
                            </td>
                        </tr>
                    </table>
            <tr>
            <td><a><b>Regista: </b><%=films.get(i).getRegista()%></a> <br>
            <a><b>Cast: </b><%=films.get(i).getCast()%></a> <br>
            <a><b>Genere: </b><%=films.get(i).getGenere()%></a> <br>
            <a><b>Durata: </b><%=films.get(i).getDurata()%>'</a> <br></td>
            </tr>


            <input type="hidden" name="filmTitolo" value="<%=films.get(i).getTitolo()%>">
            <input type="hidden" name="filmRegista" value="<%=films.get(i).getRegista()%>">
            <input type="hidden" name="filmCast" value="<%=films.get(i).getCast()%>">
            <input type="hidden" name="filmGenere" value="<%=films.get(i).getGenere()%>">
            <input type="hidden" name="filmDurata" value="<%=films.get(i).getDurata()%>">
            <input type="hidden" name="filmNazione" value="<%=films.get(i).getNazione()%>">
            <input type="hidden" name="filmAnno" value="<%=films.get(i).getAnno()%>">
            <input type="hidden" name="filmDescrizione" value="<%=films.get(i).getDescrizione()%>">
            <input type="hidden" name="filmTrailer" value="<%=films.get(i).getTrailer()%>">
            <tr>
            <td><section id="schedaButtonSection">
                <a> <input type="button" id="schedaButton" name="schedaButton"
                           class="button" value="Visualizza scheda film" onclick="viewfilm(<%=films.get(i).getCod_film()%>)"/></a>
            </section></td>
            <%if (loggedOn && loggedUtente.getTipo().equals("amministratore")) {%>
            <td><section id="newproButtonSection">
                <a> <input type="button" id="newproButton" name="newproButton"
                           class="button" value="Aggiungi proiezioni" onclick="addpro(<%=films.get(i).getCod_film()%>)"/></a>
            </section></td>
            <%}%>
            </tr>
        </table>
        <br><br><br>
        <%}%>
    </section>



    <%--ricerca film per titolo--%>
    <%}%>
    <% if(titolo != null && film != null){%>
    <section id="films" class="clearfix">
        <table class="film">
            <tr>
                <td rowspan="3" style="text-align: center;">
                    <%if (film.getImmagine()!=null) {%>
                    <img src="<%=film.getImmagine()%>">
                    <%} else {%>
                    <img src="images/imgnotfound.jpg">
                    <%}%>
                </td>
                <td><h1><a><%=film.getTitolo()%></a></h1></td>
                <td rowspan="2"><h3>Orari di Proiezione:</h3>
                    <table class="date">

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
                        <tr><td class="data-cell"><h3><%= formattedDate %></h3></td>
                            <td class="ora-cell">
        <%}%>
        <%
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = timeFormat.format(proiezione.getOra_pro());
        %>
        <a><%= formattedTime %></a><br>
        <%}%>
                            </td>
                        </tr>
                    </table>

            <tr>
                <td><a><b>Regista: </b><%=film.getRegista()%></a> <br>
                    <a><b>Cast: </b><%=film.getCast()%></a> <br>
                    <a><b>Genere: </b><%=film.getGenere()%></a> <br>
                    <a><b>Durata: </b><%=film.getDurata()%>'</a> <br></td>
            </tr>

        <input type="hidden" name="filmId" value="<%=film.getCod_film()%>">
        <input type="hidden" name="filmTitolo" value="<%=film.getTitolo()%>">
        <tr>
        <td><section id="scheda2ButtonSection">
            <a> <input type="button" id="scheda2Button" name="scheda2Button"
                       class="button" value="Visualizza scheda film" onclick="viewfilm(<%=film.getCod_film()%>)"/></a>
        </section></td>
            <%if (loggedOn && loggedUtente.getTipo().equals("amministratore")) {%>
            <td><section id="newpro2ButtonSection">
                <a> <input type="button" id="newpro2Button" name="newproButton"
                           class="button" value="Aggiungi proiezioni" onclick="addpro(<%=film.getCod_film()%>)"/></a>
            </section></td>
            <%}%>
        </tr>
        </table>
    </section>



    <%--ricerca film per data proiezione--%>
    <%}else if(titolo != null && film == null) {%>
    <table class="notfound">
        <tr>
            <td><b>Nessun film corrisponde al titolo inserito</b></td>
        </tr>
    </table>
    <%}%>
    <% if(filmsdp != null && titolo==null){%>
    <%if (filmsdp.size() > 0){%>
    <section id="films" class="clearfix">
        <%for (i = 0; i < filmsdp.size(); i++) {%>
        <table class="film">
            <tr>
                <td rowspan="3" style="text-align: center;">
                    <%if (filmsdp.get(i).getImmagine()!=null) {%>
                    <img src="<%=filmsdp.get(i).getImmagine()%>">
                    <%} else {%>
                    <img src="images/imgnotfound.jpg">
                    <%}%>
                </td>
                <td><h1><a><%=filmsdp.get(i).getTitolo()%></a></h1></td> <br>
                <td rowspan="2"><h3>Orari di Proiezione:</h3>
                    <table class="date">
            <%
                Date dataProiezione = null; // Inizializza la data di proiezione
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                for (c = 0; c < pro.size(); c++) {
                    // Controlla se il film e la data di proiezione corrispondono
                    if (filmsdp.get(i).getCod_film() == pro.get(c).getFilm().getCod_film()) {
                        if (dataProiezione == null || !dataProiezione.equals(pro.get(c).getData_pro())) {
                            dataProiezione = pro.get(c).getData_pro();
                            String data_pro = dateFormat.format(dataProiezione);
            %>
                        <tr><td class="data-cell"><h3><%= data_pro %></h3></td>
                            <td class="ora-cell">
            <%
                }
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String ora_pro = timeFormat.format(pro.get(c).getOra_pro());
            %>
            <a><%= ora_pro %></a><br>
            <%
                    }
                }
            %>
                            </td>
                        </tr>
                    </table>
            <tr>
                <td><a><b>Regista: </b><%=filmsdp.get(i).getRegista()%></a> <br>
                    <a><b>Cast: </b><%=filmsdp.get(i).getCast()%></a> <br>
                    <a><b>Genere: </b><%=filmsdp.get(i).getGenere()%></a> <br>
                    <a><b>Durata: </b><%=filmsdp.get(i).getDurata()%>'</a> <br></td>
            </tr>

            <input type="hidden" name="filmId" value="<%=i%>">
            <input type="hidden" name="filmTitolo" value="<%=filmsdp.get(i).getTitolo()%>">
            <tr>
            <td><section id="scheda3ButtonSection">
                <a> <input type="button" id="scheda3Button" name="scheda3Button"
                           class="button" value="Visualizza scheda film" onclick="viewfilm(<%=filmsdp.get(i).getCod_film()%>)"/></a>
            </section></td>
                <%if (loggedOn && loggedUtente.getTipo().equals("amministratore")) {%>
                <td><section id="newpro3ButtonSection">
                    <a> <input type="button" id="newpro3Button" name="newproButton"
                               class="button" value="Aggiungi proiezioni" onclick="addpro(<%=filmsdp.get(i).getCod_film()%>)"/></a>
                </section></td>
                <%}%>
            </tr>
        </table>
        <br><br><br>
        <%}%>
    </section>
    <%} else {%>
    <table class="notfound">
        <tr>
            <td><b>Nessuna proiezione per la data inserita</b></td>
        </tr>
    </table>
    <%}%>
    <%}%>

    <form name="schedafilmForm" method="post" action="Dispatcher">
        <input type="hidden" name="selectedcodfilm"/>
        <input type="hidden" name="controllerAction" value="HomeManagement.schedafilm"/>
    </form>

    <form name="addproForm" method="post" action="Dispatcher">
        <input type="hidden" name="selectedcodfilm"/>
        <input type="hidden" name="controllerAction" value="HomeManagement.newproView"/>
    </form>



</main>
</body>
<%@include file="/include/footer.inc"%>
</html>
