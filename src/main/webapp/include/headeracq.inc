<header class="clearfix"><!-- Defining the header section of the page -->

    <h1 class="logo"><!-- Defining the logo element -->
        Starcinema
    </h1>

    <nav><!-- Defining the navigation menu -->
        <ul>
          <li <%=menuActiveLink.equals("Home")?"class=\"active\"":""%>>
            <a href="Dispatcher?controllerAction=HomeManagement.view">Home</a>
          </li>
          <li <%=menuActiveLink.equals("Sceltaposti")?"class=\"active\"":""%>>
            <a href="Dispatcher?controllerAction=GestioneAcquisti.view">Scelta posti</a>
          </li>
        </ul>
    </nav>

</header>