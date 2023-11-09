<header class="clearfix"><!-- Defining the header section of the page -->

    <h1 class="logo"><!-- Defining the logo element -->
        St<span style="color: yellow;">&starf;</span>rcinem<span style="color: yellow;">&starf;</span>
    </h1>

    <nav><!-- Defining the navigation menu -->
        <ul>
          <li <%=menuActiveLink.equals("Home")?"class=\"active\"":""%>>
            <a href="Dispatcher?controllerAction=HomeManagement.view">Home</a>
          </li>
          <li style="pointer-events: none;" <%=menuActiveLink.equals("Metodoingresso")?"class=\"active\"":""%>>
                      <a href="Dispatcher?controllerAction=GestioneAcquisti.sceltapostiView">Metodo ingresso</a>
          </li>
          <li style="pointer-events: none;" <%=menuActiveLink.equals("Sceltaposti")?"class=\"active\"":""%>>
            <a href="Dispatcher?controllerAction=GestioneAcquisti.sceltaposti">Scelta posti</a>
          </li>
          <li style="pointer-events: none;" <%=menuActiveLink.equals("Carrello")?"class=\"active\"":""%>>
                      <a href="Dispatcher?controllerAction=GestioneAcquisti.carrelloView">Carrello</a>
          </li>
        </ul>
    </nav>

</header>