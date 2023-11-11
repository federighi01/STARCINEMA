<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>

<%
    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "AcqCompletato";

    Utente utente = (Utente) request.getAttribute("utente");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Acquisto completato</title>
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

        .acqcomp img {
            width: 100px;
            height: 100px;
            object-fit: cover; /* Scala e taglia l'immagine per adattarla alle dimensioni specificate */
        }

    </style>
</head>
<body>
<main>
    <section id="AcqCompletatoFormSection">
        <form name="AcqCompletatoForm" action="Dispatcher" method="post">
            <table class="acqcomp" style="margin-top: 140px; margin-left: 380px">
                <tr>
                    <td><img src="images/tick.png"></td>
                    <td><h2><b style="color: white">Acquisto effettuato con successo!</b></h2></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <a href="Dispatcher?controllerAction=HomeManagement.view">
                            <input <%=menuActiveLink.equals("Home")?"class=\"active\"":""%>
                                    type="submit" class="button" value="Torna alla HOME"/>
                        </a>
                    </td>
                </tr>
            </table>
        </form>
    </section>
</main>
</body>
</html>
