<%@page session="false"%>
<%@ page import="java.util.List" %>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%
    String formattedDate=null;
    String formattedTime=null;

    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Sceltaposti";

    Utente utente = (Utente) request.getAttribute("utente");
    Film film = (Film) request.getAttribute("film");
    Proiezione proiezione = (Proiezione) request.getAttribute("proiezione");
    List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
    Sala sala = (Sala) request.getAttribute("sala");
    Biglietto biglietto = (Biglietto) request.getAttribute("biglietto");
    Abbonamento abbonamento = (Abbonamento) request.getAttribute("abbonamento");
    Acquista_abb acquista_abb = (Acquista_abb) request.getAttribute("acquista_abb");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Scelta posti</title>
    <%@include file="/include/htmlHead.inc"%>
    <style>

        /* Stile per la tabella */
        table {
            width: 40%;
            border-collapse: separate;
            border-spacing: 5px; /* Aggiunge uno spazio tra le celle */
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Effetto ombra */
            border-radius: 10px; /* Angoli arrotondati */
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

        function checkSelections() {
            var selectedCheckboxes = document.querySelectorAll('input[type="checkbox"]:checked');
            if (selectedCheckboxes.length > 1 || selectedCheckboxes.length < 1) {
                alert("Devi selezionare 1 posto.");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<%@include file="/include/headeracq.inc"%>
<main>
    <%
        if(proiezione != null) {
            Date dataPro = proiezione.getData_pro();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate = dateFormat.format(dataPro);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            formattedTime = timeFormat.format(proiezione.getOra_pro());
        }
    %>


    <%if (sala != null) { %>
    <table>
        <tr>
            <td>
                SALA <%=sala.getNum_sala()%>:&nbsp;
                <%}%>
                Seleziona i posti (Massimo 1 selezione)
            </td>
        </tr>
    </table>

    <br><br>

    <section id="postiFormSection">
        <div id="checkboxContainer">
        <form name="postiForm" action="Dispatcher" method="post" onsubmit="return checkSelections();">

            <% int d = 0; %>
            <%for (int i = 0; i < composizioni.size(); i++) {%>
            <% d++; %>
            <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
                <input type="checkbox" name="selectedposti" value="<%= composizioni.get(i).getPosto().getNum_posto() %>"
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

                <a><center><input style="margin-bottom: 100px" type="submit" class="button" value="Conferma selezione"/></center></a>
                <input type="hidden" name="num_sala" value="<%=sala.getNum_sala()%>">
                <input type="hidden" name="selectedcodfilm" value="<%=film.getCod_film()%>">
                <input type="hidden" name="cod_pro" value="<%=proiezione.getCod_pro()%>">
                <input type="hidden" name="formattedDate" value="<%=formattedDate%>"/>
                <input type="hidden" name="formattedTime" value="<%=formattedTime%>"/>
                <input type="hidden" name="controllerAction" value="GestioneAcquisti.sceltapostiabb"/>
        </form>
    </section>

</main>
</body>
<%@include file="/include/footer.inc"%>
</html>
