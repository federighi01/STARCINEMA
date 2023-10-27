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

      <br><br>
      <%= "TOTALE: " + tot + "0 €" %>
      <a> <input type="submit" class="button" value="Conferma acquisto"/></a>
              <input type="hidden" name="selectedcodfilm" value="<%=film.getCod_film()%>">
              <input type="hidden" name="cod_pro" value="<%=proiezione.getCod_pro()%>">
              <%--<input type="hidden" name="formattedDate" value="<%=formattedDate%>"/>
              <input type="hidden" name="formattedTime" value="<%=formattedTime%>"/>--%>
              <input type="hidden" name="controllerAction" value="GestioneAcquisti.carrello"/>
          </form>
      </section>
      <% } else { %>
        non ci sono elementi nel carrello :(
      <% } %>
  </main>
</body>
</html>
