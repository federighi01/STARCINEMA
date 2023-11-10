<%@page session="false"%>
<%@page import="com.starcinema.starcinema.model.mo.Utente"%>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%
    boolean loggedOn = false;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String menuActiveLink = "Registrazione";
    String applicationMessage = (String) request.getAttribute("applicationMessage");

    Utente utente = (Utente) request.getAttribute("utente");
    List<String> usernames = (List<String>) request.getAttribute("usernames");
%>
<html>
<head>
    <title>Registrazione</title>
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

        /* Parte per nascondere form di login */
        #login label[for="username"], #login label[for="pw"] {
            display: none;
        }

        #login form input[type="text"], #login form input[type="password"] {
            display: none;
        }

        #login form input[type="text"]:focus, #login form input[type="password"]:focus {
            outline: none;
        }

        #login form input[type="submit"] {
            display: none;
        }

        #login form input[type="submit"]:hover {
            display: none;
        }

    </style>

    <script language="javascript">

        //Controllo se username inserito esiste già

        function checkUsername(username) {
            var submitButton = document.getElementById("reg");
            var errUsername = document.getElementById('errUsername');
            var usernamesList = [
                <% for (String name : usernames) { %>'<%= name %>',<% } %>
            ];

            console.log(username);
            console.log(usernamesList);

            // Check se l'username è presente nella lista
            if (usernamesList.indexOf(username) !== -1) {
                errUsername.style.display='block';
                submitButton.disabled = true;
            } else {
                errUsername.style.display='none';
                submitButton.disabled = false;
            }
        }

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
                alert("Indirizzo email di conferma non corrisponde a quella inserita.");
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
        <form name="regForm" action="Dispatcher" method="post" onsubmit="return validateForm()" class="input-container">

            <table class="mex">
                <tr>
                    <td>
                        Completa la tua registrazione fornendo i tuoi dati personali
                    </td>
                </tr>
            </table>

            <table style="margin-left: 420px; margin-top: 50px;">
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username"
                               oninput="checkUsername(this.value)"
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div>
                    <div id="errUsername" style="color: red; display: none;">
                        Username gia' assegnato o non valido
                    </div>
                    <br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="pw">Password</label>
                        <input type="text" id="pw" name="pw"
                               value=""
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="email">Email</label>
                        <input type="text" id="email" name="email"
                               value=""
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="email_c">Email di conferma</label>
                        <input type="text" id="email_c" name="email_c"
                               value=""
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="cognome">Cognome</label>
                        <input type="text" id="cognome" name="cognome"
                               value=""
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="nome"
                               value=""
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="data_n">Data di nascita</label>
                        <input type="date" id="data_n" name="data_n"
                               value=""
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="luogo_n">Luogo di nascita</label>
                        <input type="text" id="luogo_n" name="luogo_n"
                               value=""
                               required size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="indirizzo">Indirizzo</label>
                        <input type="text" id="indirizzo" name="indirizzo"
                               value=""
                               size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="field clearfix">
                        <label for="tel">Telefono</label>
                        <input type="text" id="tel" name="tel"
                               value=""
                               size="20" maxlength="50" autocomplete="off"/>
                    </div><br>
                </td>
            </tr>
        </table>
                    <div class="field clearfix" style="text-align: center; margin-right: 130px">
                        <label>&#160;</label>
                        <input type="submit" class="button" id="reg" name="reg" value="Iscriviti"/>
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
