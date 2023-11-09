<%@page session="false"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>

<%  int c = 0;
    String dataPro = null;
    String oraPro = null;

    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Situazionesale";

    Utente utente = (Utente) request.getAttribute("utente");
    Integer num_sala = (Integer) request.getAttribute("num_sala");
    String data_pro = (String) request.getAttribute("data_pro");
    List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
    List<Sala> sale = (List<Sala>) request.getAttribute("sale");
    List<Proiezione> proiezioni = (List<Proiezione>) request.getAttribute("proiezioni");
    List<Proiezione> proiezioni_ora = (List<Proiezione>) request.getAttribute("proiezioni_ora");
%>

<html>
<head>
    <title>Area Utente</title>
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
            text-align: center;
        }

        /* Menù a tendina */
        select {
            width: 180px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            font-size: 16px;
            text-align: center;
            display: inline-block;
            margin-top: 100px;
            margin-right: 30px;
        }

        select option {
            background-color: #fff;
            color: #333;
            padding: 5px;
        }






    </style>
    <script language="javascript">

        function menuSala(){
            var selectedSalaElement = document.getElementById("Num_salaMenu");
            var num_sala = selectedSalaElement.value;
            if(num_sala != null && num_sala != "nul"){
                document.menuSalaForm.num_sala.value = num_sala;
                document.menuSalaForm.submit();
            }
        }

        function menuData(data_pro,num_sala){
            var selectedDataElement = document.getElementById("DataProMenu");
            var data_pro = selectedDataElement.value;
            if(data_pro != null && data_pro != "nul") {
                document.menuDataForm.data_pro.value = data_pro;
                document.menuDataForm.num_sala.value = num_sala;
                document.menuDataForm.submit();
            }
        }

    </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>


    <section id="finddata" class="clearfix">
        <form name="finddataForm" action="Dispatcher" method="post">

            <%if (sale != null) {%>
            <label for="Num_salaMenu">SALA </label>
            <select id="Num_salaMenu" name="num_sala" onchange="menuSala()">
                <option value="nul">Seleziona una sala</option>
                <%for (c = 0; c < sale.size(); c++) {%>
                    <option value="<%=sale.get(c).getNum_sala()%>"><%=sale.get(c).getNum_sala()%></option>
                <%}%><%}%>
            </select>

            <!-- Il menù a tendina per le date di proiezione compare solo dopo aver
             selezionato il numero della sala -->
            <%if (proiezioni != null) {%>
            <!-- Viene visualizzato il numero della sala selezionato -->
            <%if (num_sala != null){%>
            <label for="Num_salaMenu">SALA </label>
            <select id="Num_salaMenu" name="num_sala">
                <option value="<%=num_sala%>"><%=num_sala%></option>
            </select>
            <%}%>


            <!-- Menù a tendina per le date di proiezione -->
            <label for="DataProMenu">Data proiezione </label>
            <select id="DataProMenu" name="data_pro" onchange="menuData(<%=proiezioni.get(c).getData_pro()%>,<%=num_sala%>)">
                <option value="nul">Seleziona una data</option>
                <%for (c = 0; c < proiezioni.size(); c++) {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    dataPro = dateFormat.format(proiezioni.get(c).getData_pro());

                %>
                    <option value="<%=proiezioni.get(c).getData_pro()%>"><%=dataPro%></option>
                <%}%><%}%>
            </select>


            <!-- Il menù a tendina per le ore di proiezione compare solo dopo aver
             selezionato la data di proiezione -->
            <%if (proiezioni_ora != null) {%>
            <!-- Viene visualizzato il numero della sala selezionato -->
            <%if (num_sala != null){%>
            <label for="Num_salaMenu">SALA </label>
            <select id="Num_salaMenu" name="num_sala">
                <option value="<%=num_sala%>"><%=num_sala%></option>
            </select>
            <%}%>


            <!-- Viene visualizzata la data di proiezione selezionata -->
            <%if (data_pro != null){%>
            <label for="DataProMenu">Data proiezione </label>
            <select id="DataProMenu" name="data_pro">
            <%
                //Creo una data per poi convertirla in una stringa nel formato desiderato
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date data = sdf.parse(data_pro);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String datapro = dateFormat.format(data);

            %>
                <option value="<%=data_pro%>"><%=datapro%></option>
            </select>
            <%}%>


            <label for="OraProMenu">Ora proiezione</label>
            <select id="OraProMenu" name="ora_pro">
                <option value="nul">Seleziona un'ora</option>
                <%for (c = 0; c < proiezioni_ora.size(); c++) {

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    oraPro = timeFormat.format(proiezioni_ora.get(c).getOra_pro());

                %>
                <option value="<%=proiezioni_ora.get(c).getOra_pro()%>"><%=oraPro%></option>
                <%}%>
            </select>

            <input type="submit" class="button" value="Cerca">
            <input type="hidden" name="num_sala" value="<%=num_sala%>"/>
            <input type="hidden" name="data_pro" value="<%=data_pro%>"/>
            <input type="hidden" name="ora_pro"/>
            <input type="hidden" name="controllerAction" value="SituazioneSale.cercaSala"/>
            <%}%>

        </form>
    </section>

    <!-- Visualizzazione sala e relativi posti (dopo aver premuto il bottone) -->
    <%if (composizioni != null) { %>

    <%if (num_sala != null) { %>
    Sala n. <%=num_sala%>
    <%}%>

    <br><br>
    <%for (int i = 0; i < composizioni.size(); i++) {%>
        <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
            <input type="checkbox" name="selectedposti" value="<%= composizioni.get(i).getPosto().getNum_posto() %>"
                    <% if (composizioni.get(i).isOccupato()) { %>
                       disabled="disabled"
                    <% } %>
    <% } %>
    <% } %>


            <form name="menuSalaForm" method="post" action="Dispatcher">
                <input type="hidden" name="num_sala"/>
                <input type="hidden" name="controllerAction" value="SituazioneSale.menuSala"/>
            </form>

            <form name="menuDataForm" method="post" action="Dispatcher">
                <input type="hidden" name="data_pro"/>
                <input type="hidden" name="num_sala"/>
                <input type="hidden" name="controllerAction" value="SituazioneSale.menuData"/>
            </form>

            <form name="cercaSalaForm" method="post" action="Dispatcher">
                <input type="hidden" name="num_sala"/>
                <input type="hidden" name="data_pro"/>
                <input type="hidden" name="ora_pro"/>
                <input type="hidden" name="controllerAction" value="SituazioneSale.cercaSala"/>
            </form>

</main>
</body>
</html>
