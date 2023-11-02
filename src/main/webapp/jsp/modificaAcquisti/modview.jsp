<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="com.starcinema.starcinema.model.mo.Proiezione" %>


<%  int c = 0;

    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Modificaacquisti";

    Utente utente = (Utente) request.getAttribute("utente");
    List<Film> films = (List<Film>) request.getAttribute("films");
    List<Proiezione> proiezioni = (List<Proiezione>) request.getAttribute("proiezioni");
    List<Proiezione> proiezioni_data = (List<Proiezione>) request.getAttribute("proiezioni_data");
    List<Proiezione> proiezioni_ora = (List<Proiezione>) request.getAttribute("proiezioni_ora");
    String titolo = (String) request.getAttribute("titolo");
    Integer num_sala = (Integer) request.getAttribute("num_sala");
    String data_pro = (String) request.getAttribute("data_pro");

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
            console.log("jswb ",titolo);
            var selectedDataElement = document.getElementById("DataProMenu");
            var data_pro = selectedDataElement.value;
            if(data_pro != null && data_pro != "nul") {
                document.menuDataForm.data_pro.value = data_pro;
                document.menuDataForm.titolo.value = titolo;
                document.menuDataForm.num_sala.value = num_sala;
                document.menuDataForm.submit();
            }
        }

    </script>
</head>
<body>
<%@include file="/include/header.inc"%>
    <main>

            <section id="modview" class="clearfix">
                <form name="modviewForm" action="Dispatcher" method="post">

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

                    <!-- Viene visualizzato il numero della sala selezionato -->
                    <%if (data_pro != null){%>
                    <label for="DataProMenu">Data proiezione </label>
                    <select id="DataProMenu" name="data_pro">
                        <option value="<%=data_pro%>"><%=data_pro%></option>
                    </select>
                    <%}%>
                    <br>

                    <label for="OraProMenu">Cerca per ora di calendario </label>
                    <select id="OraProMenu" name="ora_pro" onchange="menuOra(<%=titolo%>,<%=num_sala%>,<%=data_pro%>)">
                        <option value="nul"></option>
                        <%for (c = 0; c < proiezioni_ora.size(); c++) {%>
                        <option value="<%=proiezioni_ora.get(c).getOra_pro()%>"><%=proiezioni_ora.get(c).getOra_pro()%></option>
                        <%}%>
                    </select>
                    <%}%>


                </form>
            </section>

        <form name="menuFilmForm" method="post" action="Dispatcher">
            <input type="hidden" name="titolo"/>
            <input type="hidden" name="controllerAction" value="ModificaAcquisti.menuFilm"/>
        </form>

        <form name="menuSalaForm" method="post" action="Dispatcher">
            <input type="hidden" name="titolo"/>
            <input type="hidden" name="num_sala">
            <input type="hidden" name="controllerAction" value="ModificaAcquisti.menuSala"/>
        </form>

        <form name="menuDataForm" method="post" action="Dispatcher">
            <input type="hidden" name="data_pro"/>
            <input type="hidden" name="titolo"/>
            <input type="hidden" name="num_sala"/>
            <input type="hidden" name="controllerAction" value="ModificaAcquisti.menuData"/>
        </form>

    </main>
</body>
</html>
