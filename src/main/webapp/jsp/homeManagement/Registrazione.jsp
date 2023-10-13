<%@page session="false"%>
<%@page import="com.starcinema.starcinema.model.mo.Utente"%>
<%
    boolean loggedOn = false;
    String menuActiveLink = "Registrazione";
    String applicationMessage = (String) request.getAttribute("applicationMessage");

    Utente utente = (Utente) request.getAttribute("utente");
%>
<html>
<head>
    <title>Registrazione</title>
    <%@include file="/include/htmlHead.inc"%>
    <script language="javascript">

        function submitReg() {
            var f;
            f = document.regForm;
            f.controllerAction.value = "HomeManagement.reg";
        }

        function goback() {
            document.backForm.submit();
        }

        function validateForm() {
            var email = document.getElementById("email").value;
            var email_c = document.getElementById("email_c").value;

            if (email !== email_c) {
                alert("L'indirizzo email di conferma non corrisponde all'indirizzo email principale.");
                return false; // Blocca l'invio del modulo se l'email di conferma non corrisponde
            }

            // Altrimenti, il modulo verrà inviato normalmente
            return true;
        }

        function mainOnLoadHandler() {
            document.regForm.addEventListener("submit", function (event) {
                if (!validateForm()) {
                    event.preventDefault(); // Blocca l'invio del modulo se la validazione fallisce
                }
                submitReg()
            });

            document.regForm.backButton.addEventListener("click", goback);
        }

    </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>
    <section id="regFormSection">
        <form name="regForm" action="Dispatcher" method="post" onsubmit="return validateForm()">

            <div class="field clearfix">
                <label for="username">Username</label>
                <input type="text" id="username" name="username"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="pw">Password</label>
                <input type="text" id="pw" name="pw"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="email">Email</label>
                <input type="text" id="email" name="email"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="email_c">Email di conferma</label>
                <input type="text" id="email_c" name="email_c"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>

            <%--<div class="field clearfix">
                <label for="tipo">Tipo</label>
                <input type="text" id="tipo" name="tipo"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>--%>
            <div class="field clearfix">
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="data_n">Data di nascita</label>
                <input type="date" id="data_n" name="data_n"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="luogo_n">Luogo di nascita</label>
                <input type="text" id="luogo_n" name="luogo_n"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="indirizzo">Indirizzo</label>
                <input type="text" id="indirizzo" name="indirizzo"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="tel">Telefono</label>
                <input type="text" id="tel" name="tel"
                       value=""
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label>&#160;</label>
                <input type="submit" class="button" value="Invia"/>
                <input type="button" name="backButton" class="button" value="Annulla"/>
            </div>
            <input type="hidden" name="controllerAction"/>
        </form>

    </section>

    <form name="backForm" method="post" action="Dispatcher">
        <input type="hidden" name="controllerAction" value="HomeManagement.view"/>
    </form>
</main>

</body>
</html>
