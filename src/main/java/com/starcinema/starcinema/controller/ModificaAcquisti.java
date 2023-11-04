package com.starcinema.starcinema.controller;

import com.starcinema.starcinema.model.dao.*;
import com.starcinema.starcinema.model.mo.*;
import com.starcinema.starcinema.services.config.Configuration;
import com.starcinema.starcinema.services.logservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModificaAcquisti {

    private ModificaAcquisti() {
    }

    public static void view(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory= null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL,null);
            daoFactory.beginTransaction();

            //Visualizzazione acquisti effettuati da un utente
            /*AcquistaDAO acquistaDAO = sessionDAOFactory.getAcquistaDAO();
            List<Acquista> acquisti = acquistaDAO.findLoggedAcquisti();*/
            AcquistaDAO acquistaDAO = daoFactory.getAcquistaDAO();
            List<Acquista> acquisti = acquistaDAO.findAcqByUsername(loggedUtente);
            System.out.println(acquisti.size());

            //acquistaDAO.delete(acquisti);

            List<Film> films = new ArrayList<>();
            List<Proiezione> proiezioni = new ArrayList<>();
            Film film = null;
            Proiezione proiezione = null;
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();
            //Ricavo le altre info di proiezione e film relative al cod_film e cod_proiezione
            //contenuti nella tabella acquista
            for(int i=0; i<acquisti.size(); i++){
                film = filmDAO.findByCodfilm(acquisti.get(i).getFilm().getCod_film());
                films.add(film);
                proiezione = proiezioneDAO.findPro(acquisti.get(i).getProiezione().getCod_pro());
                proiezioni.add(proiezione);
                System.out.println(films.get(i).getTitolo());
                System.out.println(proiezioni.get(i).getData_pro());
            }
            System.out.println("sss"+films.size());


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("acquisti", acquisti);
            request.setAttribute("films", films);
            request.setAttribute("proiezioni", proiezioni);
            request.setAttribute("viewUrl", "modificaAcquisti/view");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }

        }

    }

    //Visualizzazione info dei film in modificaAcquisti/modview
    public static void modview(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory= null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters=new HashMap<String,Object>();
            sessionFactoryParameters.put("request",request);
            sessionFactoryParameters.put("response",response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL,sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL,null);
            daoFactory.beginTransaction();

            String cod_pro_old = request.getParameter("cod_pro_old");
            String cod_film_old = request.getParameter("cod_film_old");
            String num_posto_old = request.getParameter("num_posto_old");

            System.out.println("csdfse"+cod_pro_old);
            System.out.println("csdfse"+cod_film_old);
            System.out.println("csdfse"+num_posto_old);

            List<Film> films = null;

            FilmDAO filmDAO = daoFactory.getFilmDAO();
            films = filmDAO.findFilm();


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn",loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("films", films);
            request.setAttribute("cod_pro_old", cod_pro_old);
            request.setAttribute("cod_film_old", cod_film_old);
            request.setAttribute("num_posto_old", num_posto_old);
            request.setAttribute("viewUrl", "modificaAcquisti/modview");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }

        }

    }

    //Metodo per la gestione del menù a tendina del titolo dei film
    public static void menuFilm(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;


        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters = new HashMap<String, Object>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();


            String cod_pro_old = request.getParameter("cod_pro_old");
            String cod_film_old = request.getParameter("cod_film_old");
            String num_posto_old = request.getParameter("num_posto_old");


            //Trovo il film in base al titolo selezionato
            String titolo = request.getParameter("titolo");
            System.out.println(titolo);
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByTitolo(titolo);

            //Visualizzazione proiezione filtrata per codice film
            List<Proiezione> proiezioni=null;
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

            proiezioni = proiezioneDAO.findSalaByCod_film(film.getCod_film());


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("proiezioni", proiezioni);
            request.setAttribute("titolo", titolo);

            request.setAttribute("cod_pro_old", cod_pro_old);
            request.setAttribute("cod_film_old", cod_film_old);
            request.setAttribute("num_posto_old", num_posto_old);

            request.setAttribute("viewUrl", "modificaAcquisti/modview");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

    }

    //Metodo per la gestione del menù a tendina del numero di sala
    public static void menuSala(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;


        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters = new HashMap<String, Object>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();

            String cod_pro_old = request.getParameter("cod_pro_old");
            String cod_film_old = request.getParameter("cod_film_old");
            String num_posto_old = request.getParameter("num_posto_old");


            //Visualizzazione proiezione filtrata per numero di sala e codice film
            List<Proiezione> proiezioni_data=null;
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

            String titolo = request.getParameter("titolo");

            System.out.println("titolo "+titolo);
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByTitolo(titolo);

            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            System.out.println("num sala "+num_sala);

            proiezioni_data = proiezioneDAO.findProBySalaFilm(film.getCod_film(),num_sala);


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("proiezioni_data", proiezioni_data);
            request.setAttribute("num_sala", num_sala);
            request.setAttribute("titolo", titolo);

            request.setAttribute("cod_pro_old", cod_pro_old);
            request.setAttribute("cod_film_old", cod_film_old);
            request.setAttribute("num_posto_old", num_posto_old);

            request.setAttribute("viewUrl", "modificaAcquisti/modview");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

    }

    //Metodo per la gestione del menù a tendina della data di proiezione
    public static void menuData(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;


        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters = new HashMap<String, Object>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();


            String cod_pro_old = request.getParameter("cod_pro_old");
            String cod_film_old = request.getParameter("cod_film_old");
            String num_posto_old = request.getParameter("num_posto_old");


            //Visualizzazione proiezione filtrata per numero di sala, codice film e data proiezione
            List<Proiezione> proiezioni_ora=null;
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

            //Ricezione del titolo ed estrazione del codice del film
            String titolo = request.getParameter("titolo");
            System.out.println("titolo "+titolo);
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByTitolo(titolo);

            //Ricezione del numero della sala
            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            System.out.println("num sala "+num_sala);

            //Ricezione della data di proiezione selezionata
            String data_pro = request.getParameter("data_pro");
            System.out.println(data_pro);
            if(data_pro != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date data_proiezione = sdf.parse(data_pro);
                System.out.println(data_proiezione);

                proiezioni_ora = proiezioneDAO.findProBySalaFilmData(film.getCod_film(),num_sala,data_proiezione);
            }


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("proiezioni_ora", proiezioni_ora);
            request.setAttribute("num_sala", num_sala);
            request.setAttribute("titolo", titolo);
            request.setAttribute("data_pro", data_pro);

            request.setAttribute("cod_pro_old", cod_pro_old);
            request.setAttribute("cod_film_old", cod_film_old);
            request.setAttribute("num_posto_old", num_posto_old);

            request.setAttribute("viewUrl", "modificaAcquisti/modview");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

    }

    //Metodo per la gestione del menù a tendina dell'ora di proiezione
    public static void menuOra(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;


        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters = new HashMap<String, Object>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();

            String cod_pro_old = request.getParameter("cod_pro_old");
            String cod_film_old = request.getParameter("cod_film_old");
            String num_posto_old = request.getParameter("num_posto_old");


            //Visualizzazione proiezione filtrata per numero di sala, codice film e data proiezione
            Proiezione proiezione=null;
            List<Composizione> composizioni=null;
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();
            ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();


            //Ricezione del titolo ed estrazione del codice del film
            String titolo = request.getParameter("titolo");
            System.out.println("titolo "+titolo);
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByTitolo(titolo);

            //Ricezione del numero della sala
            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            System.out.println("num sala "+num_sala);

            //Ricezione della data e ora di proiezione selezionate
            String data_pro = request.getParameter("data_pro");
            String ora_pro = request.getParameter("ora_pro");
            System.out.println(data_pro);
            System.out.println(ora_pro);
            if(data_pro != null && ora_pro != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date data_proiezione = sdf.parse(data_pro);
                System.out.println(data_proiezione);

                SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
                java.util.Date parsedDate = sdft.parse(ora_pro);
                Time ora_proiezione = new Time(parsedDate.getTime());
                System.out.println(ora_proiezione);

                proiezione = proiezioneDAO.findProBySalaFilmDataOra(film.getCod_film(), num_sala, data_proiezione, ora_proiezione);

                //Trovo i posti delle sale filtrati per codice proiezione
                composizioni = composizioneDAO.findCompByCod_pro(proiezione.getCod_pro());

            }


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("composizioni", composizioni);
            request.setAttribute("num_sala", num_sala);
            request.setAttribute("titolo", titolo);
            request.setAttribute("data_pro", data_pro);
            request.setAttribute("ora_pro", ora_pro);

            request.setAttribute("cod_pro_old", cod_pro_old);
            request.setAttribute("cod_film_old", cod_film_old);
            request.setAttribute("num_posto_old", num_posto_old);

            request.setAttribute("viewUrl", "modificaAcquisti/modview");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }

    }

    //Metodo per l'aggiornamento dell'acquisto
    public static void updatemod(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        List<Acquista> loggedAcquisti = null;

        Logger logger = LogService.getApplicationLogger();

        try {
            logger.info("Inizio della transazione");

            Map sessionFactoryParameters = new HashMap<String, Object>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();


            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();


            //Dati dell'acquisto da modificare
            Long cod_pro_old = Long.parseLong(request.getParameter("cod_pro_old"));
            Long cod_film_old = Long.parseLong(request.getParameter("cod_film_old"));
            String num_posto_old = request.getParameter("num_posto_old");

            System.out.println("www"+cod_pro_old);
            System.out.println("www"+cod_film_old);
            System.out.println("www"+num_posto_old);


            //Visualizzazione proiezione filtrata per numero di sala, codice film e data proiezione
            Proiezione proiezione=null;
            List<Composizione> composizioni=null;
            Composizione composizione = null;
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();
            ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
            AcquistaDAO acquistaDAO = daoFactory.getAcquistaDAO();
            AcquistaDAO loggedAcquistaDAO = sessionDAOFactory.getAcquistaDAO();

            //Ricezione del titolo ed estrazione del codice del film
            String titolo = request.getParameter("titolo");
            System.out.println("titolo "+titolo);
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByTitolo(titolo);

            //Ricezione del numero della sala
            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            System.out.println("num sala "+num_sala);

            //Ricezione del posto selezionato
            String selectedposto = request.getParameter("selectedposto");
            System.out.println("posto "+selectedposto);

            //Ricezione della data e ora di proiezione selezionate
            String data_pro = request.getParameter("data_pro");
            String ora_pro = request.getParameter("ora_pro");
            System.out.println(data_pro);
            System.out.println(ora_pro);
            if(data_pro != null && ora_pro != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date data_proiezione = sdf.parse(data_pro);
                System.out.println(data_proiezione);

                SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
                java.util.Date parsedDate = sdft.parse(ora_pro);
                Time ora_proiezione = new Time(parsedDate.getTime());
                System.out.println(ora_proiezione);

                //Ricavo codice proiezione
                proiezione = proiezioneDAO.findProBySalaFilmDataOra(film.getCod_film(), num_sala, data_proiezione, ora_proiezione);

                System.out.println("Riepilogo dati: ");
                System.out.println(loggedUtente.getUsername());
                System.out.println(film.getCod_film());
                System.out.println(selectedposto);
                System.out.println(proiezione.getCod_pro());
                System.out.println("www"+cod_pro_old);
                System.out.println("www"+cod_film_old);
                System.out.println("www"+num_posto_old);

                //Aggiornamento acquisto
                acquistaDAO.update(film.getCod_film(), selectedposto, proiezione.getCod_pro(), loggedUtente.getUsername(),
                        cod_film_old, num_posto_old, cod_pro_old);

                //Aggiornamento cookie
                //loggedAcquistaDAO.updateCookie(acquistaDAO.findAcqByUsername(loggedUtente));

                //Visualizza acquisti modificati
                //loggedAcquisti = loggedAcquistaDAO.findLoggedAcquisti();

                //Blocco il nuovo posto
                composizioneDAO.update(selectedposto, proiezione.getCod_pro());
                //Libero il vecchio posto
                composizioneDAO.freeposto(num_posto_old, cod_pro_old);


            }




            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();
            logger.info("Transazione completata con successo");


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            //request.setAttribute("acquisti", loggedAcquisti);
            request.setAttribute("viewUrl", "modificaAcquisti/modcompletata");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) daoFactory.rollbackTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
                logger.info("Transazione annullata a causa di un'eccezione");
            } catch (Throwable t) {
                logger.info("Errore durante il rollback della transazione");
            }
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) daoFactory.closeTransaction();
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
                logger.info("Errore durante chiusura  della transazione");
            }
        }

    }
}
