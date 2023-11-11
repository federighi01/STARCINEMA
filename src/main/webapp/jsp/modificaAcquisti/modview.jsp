<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Proiezione" %>
<%@ page import="com.starcinema.starcinema.model.mo.Composizione" %>
<%@ page import="java.util.List" %>

<%  int c = 0;

    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Modificaacquisti";

    String cod_pro_old = (String) request.getAttribute("cod_pro_old");
    String cod_film_old = (String) request.getAttribute("cod_film_old");
    String num_posto_old = (String) request.getAttribute("num_posto_old");

    System.out.println("bcjbid"+cod_pro_old);
    System.out.println("bcjbid"+cod_film_old);
    System.out.println("bcjbid"+num_posto_old);


    Utente utente = (Utente) request.getAttribute("utente");
    List<Film> films = (List<Film>) request.getAttribute("films");
    List<Proiezione> proiezioni = (List<Proiezione>) request.getAttribute("proiezioni");
    List<Proiezione> proiezioni_data = (List<Proiezione>) request.getAttribute("proiezioni_data");
    List<Proiezione> proiezioni_ora = (List<Proiezione>) request.getAttribute("proiezioni_ora");
    List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
    String titolo = (String) request.getAttribute("titolo");
    Integer num_sala = (Integer) request.getAttribute("num_sala");
    String data_pro = (String) request.getAttribute("data_pro");
    String ora_pro = (String) request.getAttribute("ora_pro");



%>

<!DOCTYPE html>
<html>
<head>
    <title>Modifica Acquisto</title>
    <%@include file="/include/htmlHead.inc"%>
    <style>

        body, html {
            background-image: linear-gradient(101deg, rgba(254, 254, 254, 0.05) 0%, rgba(254, 254, 254, 0.05) 69%,rgba(160, 160, 160, 0.05) 69%, rgba(160, 160, 160, 0.05) 100%),linear-gradient(239deg, rgba(102, 102, 102, 0.02) 0%, rgba(102, 102, 102, 0.02) 60%,rgba(67, 67, 67, 0.02) 60%, rgba(67, 67, 67, 0.02) 100%),linear-gradient(121deg, rgba(169, 169, 169, 0.06) 0%, rgba(169, 169, 169, 0.06) 89%,rgba(189, 189, 189, 0.06) 89%, rgba(189, 189, 189, 0.06) 100%),linear-gradient(371deg, rgba(213, 213, 213, 0.04) 0%, rgba(213, 213, 213, 0.04) 45%,rgba(66, 66, 66, 0.04) 45%, rgba(66, 66, 66, 0.04) 100%),linear-gradient(228deg, rgba(223, 223, 223, 0.01) 0%, rgba(223, 223, 223, 0.01) 82%,rgba(28, 28, 28, 0.01) 82%, rgba(28, 28, 28, 0.01) 100%),linear-gradient(48deg, rgba(20, 20, 20, 0.06) 0%, rgba(20, 20, 20, 0.06) 62%,rgba(136, 136, 136, 0.06) 62%, rgba(136, 136, 136, 0.06) 100%),linear-gradient(245deg, rgba(206, 206, 206, 0.09) 0%, rgba(206, 206, 206, 0.09) 58%,rgba(6, 6, 6, 0.09) 58%, rgba(6, 6, 6, 0.09) 100%),linear-gradient(349deg, rgba(162, 162, 162, 0.07) 0%, rgba(162, 162, 162, 0.07) 27%,rgba(24, 24, 24, 0.07) 27%, rgba(24, 24, 24, 0.07) 100%),linear-gradient(231deg, rgba(166, 166, 166, 0.04) 0%, rgba(166, 166, 166, 0.04) 5%,rgba(210, 210, 210, 0.04) 5%, rgba(210, 210, 210, 0.04) 100%),linear-gradient(135deg, rgba(79,213,255, 0.9),rgba(60,135,255, 0.96),rgb(58,183,244));
        }

        main {
            background-image: linear-gradient(101deg, rgba(254, 254, 254, 0.05) 0%, rgba(254, 254, 254, 0.05) 69%,rgba(160, 160, 160, 0.05) 69%, rgba(160, 160, 160, 0.05) 100%),linear-gradient(239deg, rgba(102, 102, 102, 0.02) 0%, rgba(102, 102, 102, 0.02) 60%,rgba(67, 67, 67, 0.02) 60%, rgba(67, 67, 67, 0.02) 100%),linear-gradient(121deg, rgba(169, 169, 169, 0.06) 0%, rgba(169, 169, 169, 0.06) 89%,rgba(189, 189, 189, 0.06) 89%, rgba(189, 189, 189, 0.06) 100%),linear-gradient(371deg, rgba(213, 213, 213, 0.04) 0%, rgba(213, 213, 213, 0.04) 45%,rgba(66, 66, 66, 0.04) 45%, rgba(66, 66, 66, 0.04) 100%),linear-gradient(228deg, rgba(223, 223, 223, 0.01) 0%, rgba(223, 223, 223, 0.01) 82%,rgba(28, 28, 28, 0.01) 82%, rgba(28, 28, 28, 0.01) 100%),linear-gradient(48deg, rgba(20, 20, 20, 0.06) 0%, rgba(20, 20, 20, 0.06) 62%,rgba(136, 136, 136, 0.06) 62%, rgba(136, 136, 136, 0.06) 100%),linear-gradient(245deg, rgba(206, 206, 206, 0.09) 0%, rgba(206, 206, 206, 0.09) 58%,rgba(6, 6, 6, 0.09) 58%, rgba(6, 6, 6, 0.09) 100%),linear-gradient(349deg, rgba(162, 162, 162, 0.07) 0%, rgba(162, 162, 162, 0.07) 27%,rgba(24, 24, 24, 0.07) 27%, rgba(24, 24, 24, 0.07) 100%),linear-gradient(231deg, rgba(166, 166, 166, 0.04) 0%, rgba(166, 166, 166, 0.04) 5%,rgba(210, 210, 210, 0.04) 5%, rgba(210, 210, 210, 0.04) 100%),linear-gradient(135deg, rgba(79,213,255, 0.9),rgba(60,135,255, 0.96),rgb(58,183,244));
        }

        /* Menù a tendina */
        select {
            width: 160px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            font-size: 16px;
            text-align: center;
            display: inline-block;
            margin-top: 100px;
            margin-right: 10px;
        }

        select option {
            background-color: #fff;
            color: #333;
            padding: 5px;
        }


        /* Style per le composizioni */

        table {
            width: 5%;
            border-collapse: separate;
            border-spacing: 5px; /* Aggiunge uno spazio tra le celle */
            background-color: white;
            color: black;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Effetto ombra */
            border-radius: 10px; /* Angoli arrotondati */
            margin: 10px auto 0;
        }

        #checkboxContainer {
            background-color: black;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 350px;
            max-width: 670px;
            margin: 0 auto;
            text-align: center;
            position: relative;
        }

        /* Stile per i checkbox */
        .checkbox-label input[type="checkbox"] {
            margin-top: 10px;
            margin-left: 5px;
        }

        /* Stile per l'immagine SCHERMO */
        #schermo {
            align: center;
            top: 100%;
            left: 80%;
            transform: translate(-0%, -50%);
            max-width: 80%;
            max-height: 50%;
        }

    </style>
    <script language="javascript">

        function menuFilm(){
            var selectedFilmElement = document.getElementById("TitolofilmsMenu");
            var titolo = selectedFilmElement.value;
            if(titolo != null && titolo != "nul"){
                document.menuFilmForm.titolo.value = titolo;
                document.menuFilmForm.submit();
            }
        }

        function menuSala(titolo){
            var selectedSalaElement = document.getElementById("Num_salaMenu");
            var num_sala = selectedSalaElement.value;
            if(num_sala != null && num_sala != "nul"){
                document.menuSalaForm.titolo.value = titolo;
                document.menuSalaForm.num_sala.value = num_sala;
                document.menuSalaForm.submit();
            }
        }

        function menuData(titolo,num_sala){
            var selectedDataElement = document.getElementById("DataProMenu");
            var data_pro = selectedDataElement.value;
            if(data_pro != null && data_pro != "nul") {
                document.menuDataForm.data_pro.value = data_pro;
                document.menuDataForm.titolo.value = titolo;
                document.menuDataForm.num_sala.value = num_sala;
                document.menuDataForm.submit();
            }
        }

        function menuOra(titolo,num_sala,data_pro){
            var selectedOraElement = document.getElementById("OraProMenu");
            var ora_pro = selectedOraElement.value;
            if(ora_pro != null && ora_pro != "nul") {
                document.menuOraForm.ora_pro.value = ora_pro;
                document.menuOraForm.titolo.value = titolo;
                document.menuOraForm.num_sala.value = num_sala;
                document.menuOraForm.data_pro.value = data_pro;
                document.menuOraForm.submit();
            }
        }


        function checkSelections() {
            var selectedCheckboxes = document.querySelectorAll('input[type="checkbox"]:checked');
            if (selectedCheckboxes.length > 1) {
                alert("Puoi selezionare al massimo 1 posto.");
                return false;
            }
            console.log("La funzione checkSelections() è stata chiamata.");
            return true;
        }

    </script>
</head>
<body>
<%@include file="/include/header.inc"%>
    <main>

            <section id="modview" class="clearfix">
                <form name="modviewForm" action="Dispatcher" method="post" onsubmit="return checkSelections();">

                    <!-- Visualizzazione film -->
                    <%if (films != null) {%>
                    <label for="TitolofilmsMenu"><b style="color: white">FILM </b></label>
                    <select id="TitolofilmsMenu" name="titolo" onchange="menuFilm()">

                        <option value="nul">Seleziona un film</option>
                        <%for (c = 0; c < films.size(); c++) {%>
                        <option value="<%=films.get(c).getTitolo()%>"><%=films.get(c).getTitolo()%></option>
                        <%}%><%}%>
                    </select>




                    <!-- Il menù a tendina per il numero sala compare solo dopo aver
                                 selezionato il titolo del film -->
                    <%if (proiezioni != null) {%>
                    <!-- Viene visualizzato il titolo del film selezionato -->
                    <%if (titolo != null){%>
                    <label for="TitolofilmsMenu"><b style="color: white">FILM </b></label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>


                    <!-- Menù a tendina per il numero della sala -->
                    <label for="Num_salaMenu"><b style="color: white">SALA </b></label>
                    <select id="Num_salaMenu" name="num_sala" onchange="menuSala('<%=titolo%>')">
                        <option value="nul"></option>
                        <%for (c = 0; c < proiezioni.size(); c++) {%>
                        <option value="<%=proiezioni.get(c).getSala().getNum_sala()%>"><%=proiezioni.get(c).getSala().getNum_sala()%></option>
                        <%}%><%}%>
                    </select>



                    <!-- Il menù a tendina per le date di proiezione compare solo dopo aver
             selezionato il numero della sala -->
                    <%if (proiezioni_data != null) {%>
                    <!-- Viene visualizzato il titolo del film selezionato -->
                    <%if (titolo != null){%>
                    <label for="TitolofilmsMenu"><b style="color: white">FILM </b></label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>


                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (num_sala != null){%>
                    <label for="Num_salaMenu"><b style="color: white">SALA </b></label>
                    <select id="Num_salaMenu" name="num_sala">
                        <option value="<%=num_sala%>"><%=num_sala%></option>
                    </select>
                    <%}%>


                    <label for="DataProMenu"><b style="color: white">Data proiezione </b></label>
                    <select id="DataProMenu" name="data_pro" onchange="menuData('<%=titolo%>','<%=num_sala%>')">
                        <option value="nul"></option>
                        <%for (c = 0; c < proiezioni_data.size(); c++) {%>
                        <option value="<%=proiezioni_data.get(c).getData_pro()%>"><%=proiezioni_data.get(c).getData_pro()%></option>
                        <%}%>
                    </select>
                    <%}%>



                    <!-- Il menù a tendina per le ore di proiezione compare solo dopo aver
             selezionato la data di proiezione -->
                    <%if (proiezioni_ora != null) {%>
                    <!-- Viene visualizzato il titolo del film selezionato -->
                    <%if (titolo != null){%>
                    <label for="TitolofilmsMenu"><b style="color: white">FILM </b></label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>


                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (num_sala != null){%>
                    <label for="Num_salaMenu"><b style="color: white">SALA </b></label>
                    <select id="Num_salaMenu" name="num_sala">
                        <option value="<%=num_sala%>"><%=num_sala%></option>
                    </select>
                    <%}%>


                    <!-- Viene visualizzato la data di proiezione selezionata -->
                    <%if (data_pro != null){%>
                    <label for="DataProMenu"><b style="color: white">Data proiezione </b></label>
                    <select id="DataProMenu" name="data_pro">
                        <option value="<%=data_pro%>"><%=data_pro%></option>
                    </select>
                    <%}%>


                    <label for="OraProMenu"><b style="color: white">Ora proiezione </b></label>
                    <select id="OraProMenu" name="ora_pro" onchange="menuOra('<%=titolo%>','<%=num_sala%>','<%=data_pro%>')">
                        <option value="nul"></option>
                        <%for (c = 0; c < proiezioni_ora.size(); c++) {%>
                        <option value="<%=proiezioni_ora.get(c).getOra_pro()%>"><%=proiezioni_ora.get(c).getOra_pro()%></option>
                        <%}%>
                    </select>
                    <%}%>



                    <!-- I posti della sala compaiono solo dopo aver
             selezionato l'ora di proiezione -->
                    <%if (composizioni != null) {%>
                    <!-- Viene visualizzato il titolo del film selezionato -->
                    <%if (titolo != null){%>
                    <label for="TitolofilmsMenu"><b style="color: white">FILM </b></label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>


                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (num_sala != null){%>
                    <label for="Num_salaMenu"><b style="color: white">SALA </b></label>
                    <select id="Num_salaMenu" name="num_sala">
                        <option value="<%=num_sala%>"><%=num_sala%></option>
                    </select>
                    <%}%>


                    <!-- Viene visualizzato la data di proiezione selezionata -->
                    <%if (data_pro != null){%>
                    <label for="DataProMenu"><b style="color: white">Data proiezione </b></label>
                    <select id="DataProMenu" name="data_pro">
                        <option value="<%=data_pro%>"><%=data_pro%></option>
                    </select>
                    <%}%>


                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (ora_pro != null){%>
                    <label for="OraProMenu"><b style="color: white">Ora proiezione </b></label>
                    <select id="OraProMenu" name="ora_pro">
                        <option value="<%=ora_pro%>"><%=ora_pro%></option>
                    </select>
                    <%}%>
                    <br>

                    <table>
                        <tr>
                            <td><b>SALA <%=num_sala%></b></td>
                        </tr>
                    </table>

                    <br>

                    <section id="postiFormSection">
                        <form name="postiForm" action="Dispatcher" method="post">
                            <div id="checkboxContainer">
                            <% int d = 0; %>
                    <%for (int i = 0; i < composizioni.size(); i++) {%>
                            <% d++; %>
                            <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
                        <input type="checkbox" name="selectedposto" value="<%= composizioni.get(i).getPosto().getNum_posto() %>"
                                <% if (composizioni.get(i).isOccupato()) { %>
                               disabled="disabled"
                    <% } %>
                                <!-- Vai a capo dopo ogni 20 checkbox -->
                                    <% if ((i + 1) % 20 == 0) { %><br/><% } %>
                                <!-- Aggiungi uno spazio tra il quarto e il quinto checkbox -->
                                    <% if (d==4) { %>&nbsp;&nbsp;<% } %>
                                    <% if (d==20) { d=0; } %>
                                <!-- Aggiungi uno spazio tra il sedicesimo e il diciasettesimo checkbox -->
                                    <% if (d==16) { %>&nbsp;&nbsp;<% } %>
                    <% } %>
                                <br><br>
                                <center title="SCHERMO"><img id="schermo" src="images/schermo_cinema.png"></center>
                                <center title="SCHERMO">SCHERMO</center>
                            </div>


                            <!-- Passaggio dati da modificare e nuovi dati -->
                            <a><center><input type="submit" class="button" value="Conferma modifiche"></center></a>
                                <input type="hidden" name="titolo">
                                <input type="hidden" name="num_sala">
                                <input type="hidden" name="data_pro">
                                <input type="hidden" name="ora_pro">

                                <input type="hidden" name="cod_film_old" value="<%=cod_film_old%>">
                                <input type="hidden" name="cod_pro_old" value="<%=cod_pro_old%>">
                                <input type="hidden" name="num_posto_old" value="<%=num_posto_old%>">

                                <input type="hidden" name="controllerAction" value="ModificaAcquisti.updatemod"/>
                        </form>
                    </section>
                    <%}%>

                </form>
            </section>





        <form name="menuFilmForm" method="post" action="Dispatcher">
            <input type="hidden" name="titolo"/>

            <input type="hidden" name="cod_film_old" value="<%=cod_film_old%>">
            <input type="hidden" name="cod_pro_old" value="<%=cod_pro_old%>">
            <input type="hidden" name="num_posto_old" value="<%=num_posto_old%>">

            <input type="hidden" name="controllerAction" value="ModificaAcquisti.menuFilm"/>
        </form>

        <form name="menuSalaForm" method="post" action="Dispatcher">
            <input type="hidden" name="titolo"/>
            <input type="hidden" name="num_sala">

            <input type="hidden" name="cod_film_old" value="<%=cod_film_old%>">
            <input type="hidden" name="cod_pro_old" value="<%=cod_pro_old%>">
            <input type="hidden" name="num_posto_old" value="<%=num_posto_old%>">

            <input type="hidden" name="controllerAction" value="ModificaAcquisti.menuSala"/>
        </form>

        <form name="menuDataForm" method="post" action="Dispatcher">
            <input type="hidden" name="data_pro"/>
            <input type="hidden" name="titolo"/>
            <input type="hidden" name="num_sala"/>

            <input type="hidden" name="cod_film_old" value="<%=cod_film_old%>">
            <input type="hidden" name="cod_pro_old" value="<%=cod_pro_old%>">
            <input type="hidden" name="num_posto_old" value="<%=num_posto_old%>">

            <input type="hidden" name="controllerAction" value="ModificaAcquisti.menuData"/>
        </form>

        <form name="menuOraForm" method="post" action="Dispatcher">
            <input type="hidden" name="ora_pro"/>
            <input type="hidden" name="titolo"/>
            <input type="hidden" name="num_sala"/>
            <input type="hidden" name="data_pro"/>

            <input type="hidden" name="cod_film_old" value="<%=cod_film_old%>">
            <input type="hidden" name="cod_pro_old" value="<%=cod_pro_old%>">
            <input type="hidden" name="num_posto_old" value="<%=num_posto_old%>">

            <input type="hidden" name="controllerAction" value="ModificaAcquisti.menuOra"/>
        </form>

    </main>
</body>
</html>
