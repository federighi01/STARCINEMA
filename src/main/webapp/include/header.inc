<style>

</style>

<script>
  function headerOnLoadHandler() {
    var usernameTextField = document.querySelector("#username");
    var usernameTextFieldMsg = "Lo username \xE8 obbligatorio.";
    var pwTextField = document.querySelector("#pw");
    var pwTextFieldMsg = "La password \xE8 obbligatoria.";

    if (usernameTextField != undefined && pwTextField != undefined ) {
      usernameTextField.setCustomValidity(usernameTextFieldMsg);
      usernameTextField.addEventListener("change", function () {
        this.setCustomValidity(this.validity.valueMissing ? usernameTextFieldMsg : "");
      });
      pwTextField.setCustomValidity(pwTextFieldMsg);
      pwTextField.addEventListener("change", function () {
       this.setCustomValidity(this.validity.valueMissing ? pwTextFieldMsg : "");
      });
    }
  }
</script>

<header class="clearfix"><!-- Defining the header section of the page -->

  <h1 class="logo"><!-- Defining the logo element -->
  St<span style="color: yellow;">&starf;</span>rcinem<span style="color: yellow;">&starf;</span>
  </h1>

  <form name="logoutForm" action="Dispatcher" method="post">
    <input type="hidden" name="controllerAction" value="HomeManagement.logout"/>
  </form>

  <nav><!-- Defining the navigation menu -->
    <ul>
      <li <%=menuActiveLink.equals("Home")?"class=\"active\"":""%>>
        <a href="Dispatcher?controllerAction=HomeManagement.view">Home</a>
      </li>
      <%if (loggedOn && loggedUtente.getTipo().equals("utente")) {%>
        <li <%=menuActiveLink.equals("Modificaacquisti")?"class=\"active\"":""%>>
          <a href="Dispatcher?controllerAction=ModificaAcquisti.view">Modifica acquisti</a>
        </li>
        <li><a href="javascript:logoutForm.submit()">Logout</a></li>
      <%}%>
      <%if (loggedOn && loggedUtente.getTipo().equals("amministratore")) {%>
              <li <%=menuActiveLink.equals("Situazionesale")?"class=\"active\"":""%>>
                <a href="Dispatcher?controllerAction=SituazioneSale.view">Situazione sale</a>
              </li>
              <li <%=menuActiveLink.equals("Nuovospettacolo")?"class=\"active\"":""%>>
                              <a href="Dispatcher?controllerAction=HomeManagement.newspectView">Nuovo spettacolo</a>
              </li>
              <li><a href="javascript:logoutForm.submit()">Logout</a></li>
            <%}%>
    </ul>
  </nav>

  <%if (!loggedOn) {%>
  <nav><ul><li <%=menuActiveLink.equals("Registrazione")?"class=\"active\"":""%>>
                      <a href="Dispatcher?controllerAction=HomeManagement.regView">Registrati</a>
              </li></ul></nav>
    <section id="login" class="clearfix">
      <form name="logonForm" action="Dispatcher" method="post">
        <label for="username">Utente</label>
        <input type="text" id="username"  name="username" maxlength="40" required autocomplete="off">
        <label for="pw">Password</label>
        <input type="password" id="pw" name="pw" maxlength="40" required>
        <input type="hidden" name="controllerAction" value="HomeManagement.logon"/>
        <input type="submit" value="Accedi">
      </form>
    </section>
  <%}%>

</header>