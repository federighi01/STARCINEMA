<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Sala" %>

<%
  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Nuovospettacolo";

  Utente utente = (Utente) request.getAttribute("utente");
  Long selectedcodfilm = (Long) request.getAttribute("selectedcodfilm");
%>

<html>
<head>
  <title>Nuovo spettacolo</title>
  <%@include file="/include/htmlHead.inc"%>
  <style>

    .mex {
      width: 40%;
      text-align: center;
      border-collapse: separate;
      border-spacing: 5px; /* Aggiunge uno spazio tra le celle */
      background-color: white;
      color: black;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Effetto ombra */
      border-radius: 10px; /* Angoli arrotondati */
      margin: 10px auto 0;
      margin-bottom: -20px;
    }

    .input-container {
      display: flex;
      flex-direction: column;
      height: 100%;
    }

    .input-container .field {
      margin-bottom: 10px; /* Aggiunge spazio tra i campi */
    }

    .input-container label {
      width: 120px; /* Imposta una larghezza fissa per le etichette */
      display: inline-block;
    }

    .input-container input[type="text"],
    .input-container input[type="password"],
    .input-container input[type="date"] {
      width: 30%; /* Imposta la larghezza al 100% per adattarsi al contenitore */
      padding: 5px; /* Aggiunge spazio interno ai campi di input */
      border: 1px solid #ccc;
    }

  </style>
  <script language="javascript">

    function submitIns() {
      var f;
      f = document.insForm;
      f.controllerAction.value = "HomeManagement.newspect";
    }

    function submitInspro(cod_film) {
      var numSala = document.getElementById("num_sala").value;
      var dataPro = document.getElementById("data_pro").value;
      var oraPro = document.getElementById("ora_pro").value;
      document.addproForm.selectedcodfilm.value = cod_film;
      document.addproForm.num_sala.value = numSala;
      document.addproForm.data_pro.value = dataPro;
      document.addproForm.ora_pro.value = oraPro;
      document.addproForm.submit();
    }

    function goback() {
      document.backForm.submit();
    }

    function mainOnLoadHandler() {
      document.insForm.addEventListener("submit", submitIns());

      document.insForm.backButton.addEventListener("click", goback);
    }

  </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>

  <!-- Se viene cliccato nella barra di navigazione "Nuovo spettacolo" -->
  <%if (selectedcodfilm == null) {%>

  <table class="mex">
    <tr>
      <td>
        Inserisci un nuovo film al catalogo
      </td>
    </tr>
  </table>



  <section id="insFormSection" style="margin-top: 90px; margin-left: 420px">
    <form name="insForm" action="Dispatcher" method="post" class="input-container">

      <div class="field clearfix">
        <label for="titolo"><b style="color: white">Titolo</b></label>
        <input type="text" id="titolo" name="titolo"
               value=""
               required size="20" maxlength="80" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="regista"><b style="color: white">Regista</b></label>
        <input type="text" id="regista" name="regista"
               value=""
               required size="20" maxlength="45" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="cast"><b style="color: white">Cast</b></label>
        <input type="text" id="cast" name="cast"
               value=""
               required size="20" maxlength="100" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="genere"><b style="color: white">Genere</b></label>
        <input type="text" id="genere" name="genere"
               value=""
               required size="20" maxlength="45" autocomplete="off"/>
      </div><br>

      <div class="field clearfix">
        <label for="durata"><b style="color: white">Durata</b></label>
        <input type="text" id="durata" name="durata"
               value=""
               required size="20" maxlength="50" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="nazione"><b style="color: white">Nazione</b></label>
        <input type="text" id="nazione" name="nazione"
               value=""
               required size="20" maxlength="45" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="anno"><b style="color: white">Anno</b></label>
        <input type="text" id="anno" name="anno"
               value=""
               required size="20" maxlength="50" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="descrizione"><b style="color: white">Descrizione</b></label>
        <input type="text" id="descrizione" name="descrizione"
               value=""
               required size="20" maxlength="1024" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="trailer"><b style="color: white">Trailer</b></label>
        <input type="text" id="trailer" name="trailer"
               value=""
               required size="20" maxlength="256" autocomplete="off"/>
      </div><br>
      <div class="field clearfix">
        <label for="immagine"><b style="color: white">Locandina</b></label>
        <input type="text" id="immagine" name="immagine"
               value=""
               required size="20" maxlength="6000" autocomplete="off"/>
      </div><br>

      <div class="field clearfix">
        <label>&#160;</label>
        <input type="submit" class="button" value="Invia"/>
        <input type="button" name="backButton" class="button" value="Annulla"/>
      </div>
      <input type="hidden" name="controllerAction"/>
    </form>

  </section>
  <%}%>

  <!-- Quando viene premuto il bottone "aggiungi proiezioni" nella home -->
  <%if (selectedcodfilm != null) {%>

  <table class="mex">
    <tr>
      <td>
        Inserisci una nuova proiezione al film selezionato
      </td>
    </tr>
  </table>


  <section id="insproFormSection" style="margin-top: 90px; margin-left: 420px; margin-bottom: -180px">
    <form name="insproForm" action="Dispatcher" method="post" class="input-container">


      <div class="field clearfix">
        <label for="num_sala"><b style="color: white">Numero sala</b></label>
        <input type="text" id="num_sala" name="num_sala"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="data_pro"><b style="color: white">Data proiezione</b></label>
        <input type="date" id="data_pro" name="data_pro"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="ora_pro"><b style="color: white">Ora proiezione</b></label>
        <input type="time" id="ora_pro" name="ora_pro"
               value=""
               required size="20" maxlength="50"/>
      </div><br>

      <div class="field clearfix">
        <label>&#160;</label>
        <input type="button" id="newproButton" name="newproButton"
               class="button" value="Invia" onclick="submitInspro(<%=selectedcodfilm%>)">
        <input type="button" name="backButton" class="button" value="Annulla" onclick="goback()"/>
      </div>
      <input type="hidden" name="controllerAction"/>
    </form>

  </section>
  <%}%>

  <form name="backForm" method="post" action="Dispatcher">
    <input type="hidden" name="controllerAction" value="HomeManagement.view"/>
  </form>

  <form name="addproForm" method="post" action="Dispatcher">
    <input type="hidden" name="selectedcodfilm"/>
    <input type="hidden" name="num_sala"/>
    <input type="hidden" name="data_pro"/>
    <input type="hidden" name="ora_pro"/>
    <input type="hidden" name="controllerAction" value="HomeManagement.newpro"/>
  </form>


</main>
</body>
<%@include file="/include/footer.inc"%>
</html>