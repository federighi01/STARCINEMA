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
        }


        table {
            width: 40%;
            border: 1px solid #ccc; /* Add border style */
            border-radius: 5px; /* Add border radius */
            padding: 10px; /* Add padding */
            margin: 10px; /* Add margin */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add box shadow */
        }

        table td,tr {
            border: none;
        }


        table.empty {
            border-collapse: collapse;
            border: none;
            box-shadow: none;
        }

        table.empty td {
            border: none;
        }

        /* Style table pagamenti */

        .pay {
            width: 95%;
            margin-top: 20px;
        }



        .input-container {
            display: flex;
            flex-direction: column;
        }

        .input-container .field {
            margin-bottom: 10px; /* Aggiunge spazio tra i campi */
        }

        .input-container label {
            width: 120px; /* Imposta una larghezza fissa per le etichette */
            display: inline-block;
        }

        .input-container input[type="text"] {
            width: 40%; /* Imposta la larghezza al 100% per adattarsi al contenitore */
            padding: 5px; /* Aggiunge spazio interno ai campi di input */
            border: 1px solid #ccc;
        }


        /* Menù a tendina */
        select {
            width: 125px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            font-size: 16px;
            text-align: center;
        }


        select option {
            background-color: #fff;
            color: #333;
            padding: 5px;
        }

    </style>
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
          <form name="payForm" action="Dispatcher" method="post" class="input-container">
            <% if (composizioni != null && film != null) { %>
                <% for (int i = 0; i < composizioni.size(); i++) { %>
              <table>
                  <tr>
                      <td>
                          <input type="hidden" name="payments" value="<%= composizioni.get(i).getPosto().getNum_posto() %>">
                          <div style="background-color: dodgerblue; color: white; padding: 5px; display: inline-block;">
                              <%= composizioni.get(i).getPosto().getNum_posto() %>
                          </div>
                          PLATEA
                      </td>
                      <td style="text-align: right">
                          <span><%=biglietto.getTipo()%></span>&nbsp;<span style="color: royalblue;"><%=biglietto.getPrezzo() + "0 €" %></span>
                      </td>
                  </tr>
              </table>
              <%--
              <%= film.getTitolo()%>
                    <%= formattedDate %><br><%= formattedTime %><br>



                    <br>
                    <%="Codice proiezione: " + composizioni.get(i).getProiezione().getCod_pro()%>
                    --%>
                    <br><br>
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


              <table class="pay" style="background-color: azure">
                  <tr>
                      <td colspan="2" style="vertical-align: top; height: 50px; color: black"><%= "TOTALE: " + tot + "0 €" %></td><br>
                      <td rowspan="3" style="text-align: right">
                          <!-- Bottone per acquistare biglietto senza abbonamento -->
                          <a> <input type="submit" class="button" value="Conferma acquisto"/></a>
                          <input type="hidden" name="selectedcodfilm" value="<%=film.getCod_film()%>">
                          <input type="hidden" name="cod_pro" value="<%=proiezione.getCod_pro()%>">

                          <input type="hidden" name="controllerAction" value="GestioneAcquisti.carrello"/>
                      </td>
                  </tr>
                  <tr>
                      <td>
                          <!-- Menù a tendina per metodo di pagamento -->
                          <label for="metodopMenu">Metodo di pagamento:</label>
                          <select id="metodopayMenu" name="metodo_p">
                              <option value="Paypal">Paypal</option>
                              <option value="PostePay">PostePay</option>
                              <option value="Visa">Visa</option>
                              <option value="Mastercard">Mastercard</option>
                          </select>
                      </td>
                  </tr>
                  <tr>
                      <td style="margin-top: 20px">
                          <div class="field clearfix">
                              <label for="num_carta">Numero carta</label>
                              <input type="text" id="num_carta" name="num_carta"
                                     value=""
                                     required size="20" maxlength="50"/>
                          </div>
                      </td>
                  </tr>
              </table>
              <br>
              <%}%>
          </form>
      </section>


      <section id="payabbFormSection">
          <form name="payabbForm" action="Dispatcher" method="post">
      <% } else if (composizioni != null){ %>
              <% if (abbonamento != null && composizioni.size() == 0) {%>
              Abbonamento valido per 10 ingressi<br>
              <%= "Prezzo: " + abbonamento.getPrezzo() + "0 €" %><br><br>

            <table class="pay" style="background-color: azure">
                <tr>
                    <td colspan="2" style="vertical-align: top; height: 50px; color: black"><%= "TOTALE: " + abbonamento.getPrezzo() + "0 €" %></td><br>
                    <td rowspan="3" style="text-align: right">
                        <a> <input type="submit" class="button" value="Conferma acquisto"/></a>
                        <input type="hidden" name="cod_abb" value="<%=abbonamento.getCod_abb()%>">
                        <input type="hidden" name="controllerAction" value="GestioneAcquisti.carrelloabb"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <!-- Menù a tendina per metodo di pagamento -->
                        <label for="metodopMenu">Seleziona metodo di pagamento:</label>
                        <select id="metodopMenu" name="metodo_p">
                            <option value="Paypal">Paypal</option>
                            <option value="PostePay">PostePay</option>
                            <option value="Visa">Visa</option>
                            <option value="Mastercard">Mastercard</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="field clearfix">
                            <label for="numero_carta">Numero carta</label>
                            <input type="text" id="numero_carta" name="numero_carta"
                                   value=""
                                   required size="20" maxlength="50"/>
                        </div>
                    </td>
                </tr>
            </table>

        <%}%>
      <%} else {%>
              <table class="empty" style="margin-top: 50px; margin-left: 380px">
                  <tr>
                      <td style="text-align: center; padding-right: 30px"><h1>Carrello vuoto</h1></td>
                  </tr>
                  <tr>
                      <td style="text-align: center; padding-right: 30px"><img src="images/emptycart.png"></td>
                  </tr>
              </table>
      <%}%>
          </form>
      </section>

  </main>
</body>
</html>
