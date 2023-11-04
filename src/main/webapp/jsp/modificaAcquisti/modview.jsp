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
                    <label for="TitolofilmsMenu">Inserisci titolo film </label>
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
                    <label for="TitolofilmsMenu">Inserisci titolo film </label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>
                    <br>

                    <!-- Menù a tendina per il numero della sala -->
                    <label for="Num_salaMenu">Seleziona numero sala </label>
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
                    <label for="TitolofilmsMenu">Inserisci titolo film </label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>
                    <br>

                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (num_sala != null){%>
                    <label for="Num_salaMenu">Numero sala </label>
                    <select id="Num_salaMenu" name="num_sala">
                        <option value="<%=num_sala%>"><%=num_sala%></option>
                    </select>
                    <%}%>
                    <br>

                    <label for="DataProMenu">Cerca per data di calendario </label>
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
                    <label for="TitolofilmsMenu">Inserisci titolo film </label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>
                    <br>

                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (num_sala != null){%>
                    <label for="Num_salaMenu">Numero sala </label>
                    <select id="Num_salaMenu" name="num_sala">
                        <option value="<%=num_sala%>"><%=num_sala%></option>
                    </select>
                    <%}%>
                    <br>

                    <!-- Viene visualizzato la data di proiezione selezionata -->
                    <%if (data_pro != null){%>
                    <label for="DataProMenu">Data proiezione </label>
                    <select id="DataProMenu" name="data_pro">
                        <option value="<%=data_pro%>"><%=data_pro%></option>
                    </select>
                    <%}%>
                    <br>

                    <label for="OraProMenu">Cerca per ora di calendario </label>
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
                    <label for="TitolofilmsMenu">Inserisci titolo film </label>
                    <select id="TitolofilmsMenu" name="titolo">
                        <option value="<%=titolo%>"><%=titolo%></option>
                    </select>
                    <%}%>
                    <br>

                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (num_sala != null){%>
                    <label for="Num_salaMenu">Numero sala </label>
                    <select id="Num_salaMenu" name="num_sala">
                        <option value="<%=num_sala%>"><%=num_sala%></option>
                    </select>
                    <%}%>
                    <br>

                    <!-- Viene visualizzato la data di proiezione selezionata -->
                    <%if (data_pro != null){%>
                    <label for="DataProMenu">Data proiezione </label>
                    <select id="DataProMenu" name="data_pro">
                        <option value="<%=data_pro%>"><%=data_pro%></option>
                    </select>
                    <%}%>
                    <br>

                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (ora_pro != null){%>
                    <label for="OraProMenu">Ora proiezione </label>
                    <select id="OraProMenu" name="ora_pro">
                        <option value="<%=ora_pro%>"><%=ora_pro%></option>
                    </select>
                    <%}%>
                    <br><br><br><br>

                    Sala n. <%=num_sala%>

                    <br><br>

                    <section id="postiFormSection">
                        <form name="postiForm" action="Dispatcher" method="post">
                    <%for (int i = 0; i < composizioni.size(); i++) {%>
                            <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
                        <input type="checkbox" name="selectedposto" value="<%= composizioni.get(i).getPosto().getNum_posto() %>"
                                <% if (composizioni.get(i).isOccupato()) { %>
                               disabled="disabled"
                    <% } %>
                                <!-- Vai a capo dopo ogni 20 checkbox -->
                                    <% if ((i + 1) % 20 == 0) { %><br/><% } %>
                    <% } %>
                            <!-- Passaggio dati da modificare e nuovi dati -->
                            <a><input type="submit" class="button" value="Conferma modifiche"></a>
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
