<%@page session="false"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%
    double tot=0;
    String formattedDate=null;
    String formattedTime=null;

    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Carrello";

    Utente utente = (Utente) request.getAttribute("utente");
    List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
    Film film = (Film) request.getAttribute("film");
    Proiezione proiezione = (Proiezione) request.getAttribute("proiezione");
    Biglietto biglietto = (Biglietto) request.getAttribute("biglietto");
    Abbonamento abbonamento = (Abbonamento) request.getAttribute("abbonamento");
    Acquista_abb acquista_abb = (Acquista_abb) request.getAttribute("acquista_abb");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <%@include file="/include/htmlHead.inc"%>
    <script language="javascript">




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
      <section id="payFormSection">
          <form name="payForm" action="Dispatcher" method="post">
            <% if (composizioni != null && film != null) { %>
                <% for (int i = 0; i < composizioni.size(); i++) { %>
                    <%= formattedDate %><br><%= formattedTime %><br>
                    <%= film.getTitolo()%>
                    <input type="hidden" name="payments" value="<%= composizioni.get(i).getPosto().getNum_posto() %>">
                    <%= composizioni.get(i).getPosto().getNum_posto() %>

                    <%= "Prezzo: " + biglietto.getPrezzo() + "0 €" %><br>
                    <%="Codice proiezione: " + composizioni.get(i).getProiezione().getCod_pro()%>
                    <%=biglietto.getTipo()%><br><br>
                    <%
                        tot+= biglietto.getPrezzo();
                    %>
                <% } %>



               <!-- Controllo se utente ha un abbonamento -->
              <%if (acquista_abb != null && abbonamento != null) {%>
              <br><br>
              <%= "TOTALE: 0 €" %><br>
              <%= "Utilizzo abbonamento, ingressi disponibili: " + acquista_abb.getNum_ingressi()%>
                    <!-- Bottone per acquistare tramite abbonamento -->
                    <a> <input type="submit" class="button" value="Conferma acquisto con abbonamento"/></a>
                        <input type="hidden" name="selectedcodfilm" value="<%=film.getCod_film()%>">
                        <input type="hidden" name="cod_pro" value="<%=proiezione.getCod_pro()%>">
                        <input type="hidden" name="cod_b" value="<%=biglietto.getCod_b()%>">
                        <input type="hidden" name="cod_abb" value="<%=abbonamento.getCod_abb()%>">
                        <input type="hidden" name="cod_acq_abb" value="<%=acquista_abb.getCod_acq_abb()%>">
                        <input type="hidden" name="num_ingressi" value="<%=acquista_abb.getNum_ingressi()%>">
                        <input type="hidden" name="controllerAction" value="GestioneAcquisti.carrello_ut_abb"/>
                <%} else {%>
              <br><br>
              <%= "TOTALE: " + tot + "0 €" %>

              <!-- Menù a tendina per metodo di pagamento -->
              <label for="metodopMenu">Seleziona metodo di pagamento:</label>
              <select id="metodopMenu" name="metodo_p">
                  <option value="Paypal">Paypal</option>
                  <option value="PostePay">PostePay</option>
                  <option value="Visa">Visa</option>
                  <option value="Mastercard">Mastercard</option>
              </select>

              <div class="field clearfix">
                  <label for="num_carta">Numero carta</label>
                  <input type="text" id="num_carta" name="num_carta"
                         value=""
                         required size="20" maxlength="50"/>
              </div><br>

                    <!-- Bottone per acquistare biglietto senza abbonamento -->
                    <a> <input type="submit" class="button" value="Conferma acquisto"/></a>
                        <input type="hidden" name="selectedcodfilm" value="<%=film.getCod_film()%>">
                        <input type="hidden" name="cod_pro" value="<%=proiezione.getCod_pro()%>">

              <input type="hidden" name="controllerAction" value="GestioneAcquisti.carrello"/>
              <%}%>
          </form>
      </section>

      <section id="payabbFormSection">
          <form name="payabbForm" action="Dispatcher" method="post">
      <% } else if (abbonamento != null){ %>
              Abbonamento valido per 10 ingressi<br>
              <%= "Prezzo: " + abbonamento.getPrezzo() + "0 €" %><br><br>
      <%= "TOTALE: " + abbonamento.getPrezzo() + "0 €" %>

              <!-- Menù a tendina per metodo di pagamento -->
              <label for="metodopMenu">Seleziona metodo di pagamento:</label>
              <select id="metodopMenu" name="metodo_p">
                  <option value="Paypal">Paypal</option>
                  <option value="PostePay">PostePay</option>
                  <option value="Visa">Visa</option>
                  <option value="Mastercard">Mastercard</option>
              </select>

              <div class="field clearfix">
                  <label for="numero_carta">Numero carta</label>
                  <input type="text" id="numero_carta" name="numero_carta"
                         value=""
                         required size="20" maxlength="50"/>
              </div><br>

              <a> <input type="submit" class="button" value="Conferma acquisto"/></a>
              <input type="hidden" name="cod_abb" value="<%=abbonamento.getCod_abb()%>">
              <input type="hidden" name="controllerAction" value="GestioneAcquisti.carrelloabb"/>
      <%} else {%>
              non ci sono elementi nel carrello :(
      <%}%>
          </form>
      </section>

  </main>
</body>
</html>
