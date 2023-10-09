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

        function mainOnLoadHandler() {
            document.regForm.addEventListener("submit", submitReg);
            document.regForm.backButton.addEventListener("click", goback);
        }

    </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>
    <section id="regFormSection">
        <form name="regForm" action="Dispatcher" method="post">

            <div class="field clearfix">
                <label for="username">Username</label>
                <input type="text" id="username" name="username"
                       <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="pw">Password</label>
                <input type="text" id="pw" name="pw"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="email">Email</label>
                <input type="text" id="email" name="email"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="tipo">Tipo</label>
                <input type="text" id="tipo" name="tipo"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="data_n">Data di nascita</label>
                <input type="date" id="data_n" name="data_n"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="luogo_n">Luogo di nascita</label>
                <input type="text" id="luogo_n" name="luogo_n"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="indirizzo">Indirizzo</label>
                <input type="text" id="indirizzo" name="indirizzo"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label for="tel">Telefono</label>
                <input type="text" id="tel" name="tel"
                <%--manca il parametro value--%>
                       required size="20" maxlength="50"/>
            </div><br>
            <div class="field clearfix">
                <label>&#160;</label>
                <input type="submit" class="button" value="Invia"/>
                <input type="button" name="backButton" class="button" value="Annulla"/>
            </div>

        </form>
    </section>

    <form name="backForm" method="post" action="Dispatcher">
        <%--input type="hidden" name="selectedInitial" value="<%=selectedInitial%>"/--%>
        <input type="hidden" name="controllerAction" value="HomeManagement.view"/>
    </form>
</main>

</body>
</html>
