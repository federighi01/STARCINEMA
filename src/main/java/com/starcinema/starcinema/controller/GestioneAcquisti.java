package com.starcinema.starcinema.controller;

import com.starcinema.starcinema.model.dao.*;
import com.starcinema.starcinema.model.mo.*;
import com.starcinema.starcinema.services.config.Configuration;
import com.starcinema.starcinema.services.logservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestioneAcquisti {

    private GestioneAcquisti() {
    }

    public static void sceltapostiView(HttpServletRequest request, HttpServletResponse response) {

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


            List<Composizione> composizioni=null;
            Sala sala=null;


            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            Long cod_pro = Long.parseLong(request.getParameter("cod_pro"));
            if(num_sala != null){
                ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
                composizioni = composizioneDAO.findComposizioniByNum_sala(num_sala,cod_pro);

                SalaDAO salaDAO = daoFactory.getSalaDAO();
                sala = salaDAO.findSalaByNum_sala(num_sala);
            }

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("composizioni", composizioni);
            request.setAttribute("sala", sala);
            request.setAttribute("viewUrl", "gestioneAcquisti/sceltaposti");

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

    public static void sceltapostiViewabb(HttpServletRequest request, HttpServletResponse response) {

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


            List<Composizione> composizioni=null;
            Sala sala=null;


            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            Long cod_pro = Long.parseLong(request.getParameter("cod_pro"));
            if(num_sala != null){
                ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
                composizioni = composizioneDAO.findComposizioniByNum_sala(num_sala,cod_pro);

                SalaDAO salaDAO = daoFactory.getSalaDAO();
                sala = salaDAO.findSalaByNum_sala(num_sala);
            }

            //Passaggio info abbonamento
            AbbonamentoDAO abbonamentoDAO = daoFactory.getAbbonamentoDAO();
            Abbonamento abbonamento = abbonamentoDAO.findAbb();

            Acquista_abbDAO acquista_abbDAO = daoFactory.getAcquista_abbDAO();
            Acquista_abb acquista_abb = acquista_abbDAO.findAcqByUsername(loggedUtente);


            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("composizioni", composizioni);
            request.setAttribute("sala", sala);
            request.setAttribute("abbonamento", abbonamento);
            request.setAttribute("acquista_abb", acquista_abb);
            request.setAttribute("viewUrl", "gestioneAcquisti/sceltapostiabb");

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

    public static void sceltaposti(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        List<Composizione> composizioni=null;

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


            //Ricezione posti selezionati nel checkbox
            String[] selectedposti = request.getParameterValues("selectedposti");
            //Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            String numSalaParam = request.getParameter("num_sala");
            Integer num_sala = null;

            if (numSalaParam != null && !numSalaParam.isEmpty()) {
                num_sala = Integer.parseInt(numSalaParam);

                Long cod_pro = Long.parseLong(request.getParameter("cod_pro"));

                //ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
                if (selectedposti != null) {
                    composizioni = new ArrayList<Composizione>(); // Inizializza la lista prima di iniziare ad aggiungere elementi
                    ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();

                    for (int i = 0; i < selectedposti.length; i++) {
                        if (selectedposti[i] != null && !selectedposti[i].isEmpty()) {
                            List<Composizione> composizioniPosto = composizioneDAO.findCompByPosto(num_sala, selectedposti[i], cod_pro);
                            if (composizioniPosto != null) {
                                composizioni.addAll(composizioniPosto); // Aggiungi tutti gli elementi della lista a composizioni
                            }
                            System.out.println(selectedposti[i]);
                        }
                    }
                }
            }

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("composizioni", composizioni);
            request.setAttribute("viewUrl", "gestioneAcquisti/carrello");

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

    public static void sceltapostiabb(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        List<Composizione> composizioni=null;

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


            //Ricezione posti selezionati nel checkbox
            String[] selectedposti = request.getParameterValues("selectedposti");
            //Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            String numSalaParam = request.getParameter("num_sala");
            Integer num_sala = null;

            if (numSalaParam != null && !numSalaParam.isEmpty()) {
                num_sala = Integer.parseInt(numSalaParam);

                Long cod_pro = Long.parseLong(request.getParameter("cod_pro"));

                //ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
                if (selectedposti != null) {
                    composizioni = new ArrayList<Composizione>(); // Inizializza la lista prima di iniziare ad aggiungere elementi
                    ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();

                    for (int i = 0; i < selectedposti.length; i++) {
                        if (selectedposti[i] != null && !selectedposti[i].isEmpty()) {
                            List<Composizione> composizioniPosto = composizioneDAO.findCompByPosto(num_sala, selectedposti[i], cod_pro);
                            if (composizioniPosto != null) {
                                composizioni.addAll(composizioniPosto); // Aggiungi tutti gli elementi della lista a composizioni
                            }
                            System.out.println(selectedposti[i]);
                        }
                    }
                }
            }


            //Passaggio info abbonamento
            AbbonamentoDAO abbonamentoDAO = daoFactory.getAbbonamentoDAO();
            Abbonamento abbonamento = abbonamentoDAO.findAbb();

            Acquista_abbDAO acquista_abbDAO = daoFactory.getAcquista_abbDAO();
            Acquista_abb acquista_abb = acquista_abbDAO.findAcqByUsername(loggedUtente);

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("composizioni", composizioni);
            request.setAttribute("abbonamento", abbonamento);
            request.setAttribute("acquista_abb", acquista_abb);
            request.setAttribute("viewUrl", "gestioneAcquisti/carrello");

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


    //Metodo per l'acquisto di un abbonamento
    public static void acqabb(HttpServletRequest request, HttpServletResponse response) {

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

            //Sezione per visualizzazione info abbonamento
            AbbonamentoDAO abbonamentoDAO = daoFactory.getAbbonamentoDAO();
            Abbonamento abbonamento = abbonamentoDAO.findAbb();

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            //Passaggio abbonamento al carrello
            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("abbonamento", abbonamento);
            request.setAttribute("viewUrl", "gestioneAcquisti/carrello");

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


    //Carrello per biglietto singolo
    public static void carrello(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        Posto posto=null;

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


            UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
            Utente utente = utenteDAO.findByUsername(loggedUtente.getUsername());
            System.out.println(loggedUtente.getUsername());


            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Long selectedcodfilm = Long.parseLong(request.getParameter("selectedcodfilm"));
            Film film = filmDAO.findByCodfilm(selectedcodfilm);
            System.out.println(selectedcodfilm);


            //Ricezione composizioni dal carrello
            String[] payments = request.getParameterValues("payments");

            PostoDAO postoDAO = daoFactory.getPostoDAO();
            AcquistaDAO acquistaDAO = daoFactory.getAcquistaDAO();

            Date data_acq = new Date();
            System.out.println(data_acq);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data_acquisto = sdf.format(data_acq);
            System.out.println(data_acquisto);


            Long cod_pro = Long.parseLong(request.getParameter("cod_pro"));
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();
            Proiezione proiezione = proiezioneDAO.findPro(cod_pro);

            String metodo_p = request.getParameter("metodo_p");
            System.out.println(metodo_p);
            String num_carta = request.getParameter("num_carta");

            ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
            if(payments != null){
                for (int i = 0; i < payments.length; i++) {
                    posto = postoDAO.findPosto(payments[i]);
                    acquistaDAO.create(
                            utente,
                            film,
                            posto,
                            proiezione,
                            data_acquisto,
                            metodo_p,
                            num_carta);
                    composizioneDAO.update(payments[i],cod_pro);
                    System.out.println(payments[i]);
                }
            }


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("viewUrl", "gestioneAcquisti/acqcompletato");

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

    //Carrello per abbonamento
    public static void carrelloabb(HttpServletRequest request, HttpServletResponse response) {

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


            UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
            Utente utente = utenteDAO.findByUsername(loggedUtente.getUsername());
            System.out.println(loggedUtente.getUsername());




            //Sezione per acquisto dell'abbonamento
            Date data_acq_abb = new Date();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data_acquisto_abb = sdf2.format(data_acq_abb);
            System.out.println(data_acquisto_abb);

            Long cod_abb = Long.parseLong(request.getParameter("cod_abb"));
            System.out.println(cod_abb);
            AbbonamentoDAO abbonamentoDAO = daoFactory.getAbbonamentoDAO();
            Abbonamento abbonamento = abbonamentoDAO.findAbbByCod(cod_abb);

            String metodo_p = request.getParameter("metodo_p");
            System.out.println(metodo_p);
            String numero_carta = request.getParameter("numero_carta");

            Acquista_abbDAO acquista_abbDAO = daoFactory.getAcquista_abbDAO();
            acquista_abbDAO.create(utente,abbonamento,data_acquisto_abb,metodo_p,numero_carta);



            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("viewUrl", "gestioneAcquisti/acqcompletato");

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

    //Carrello per utilizzo ingresso abbonamento
    public static void carrello_ut_abb(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        Posto posto=null;

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


            UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
            Utente utente = utenteDAO.findByUsername(loggedUtente.getUsername());
            System.out.println(loggedUtente.getUsername());


            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Long selectedcodfilm = Long.parseLong(request.getParameter("selectedcodfilm"));
            Film film = filmDAO.findByCodfilm(selectedcodfilm);
            System.out.println(selectedcodfilm);


            //Ricezione composizioni dal carrello
            String[] payments = request.getParameterValues("payments");

            PostoDAO postoDAO = daoFactory.getPostoDAO();
            AcquistaDAO acquistaDAO = daoFactory.getAcquistaDAO();

            Date data_acq = new Date();
            System.out.println(data_acq);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data_acquisto = sdf.format(data_acq);
            System.out.println(data_acquisto);


            Long cod_pro = Long.parseLong(request.getParameter("cod_pro"));
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();
            Proiezione proiezione = proiezioneDAO.findPro(cod_pro);


            ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
            if(payments != null){
                for (int i = 0; i < payments.length; i++) {
                    posto = postoDAO.findPosto(payments[i]);
                    acquistaDAO.create(
                            utente,
                            film,
                            posto,
                            proiezione,
                            data_acquisto,
                            "",
                            "");
                    composizioneDAO.update(payments[i],cod_pro);
                    System.out.println(payments[i]);
                }
            }


            //Utilizzo ingresso abbonamento
            Long cod_b = Long.parseLong(request.getParameter("cod_b"));
            System.out.println(cod_b);
            BigliettoDAO bigliettoDAO = daoFactory.getBigliettoDAO();
            Biglietto biglietto = bigliettoDAO.findBigliettoByCod_b(cod_b);


            Long cod_abb = Long.parseLong(request.getParameter("cod_abb"));
            System.out.println(cod_abb);
            AbbonamentoDAO abbonamentoDAO = daoFactory.getAbbonamentoDAO();
            Abbonamento abbonamento = abbonamentoDAO.findAbbByCod(cod_abb);


            Long cod_acq_abb = Long.parseLong(request.getParameter("cod_acq_abb"));
            System.out.println(cod_acq_abb);
            Acquista_abbDAO acquista_abbDAO = daoFactory.getAcquista_abbDAO();
            Acquista_abb acquista_abb = acquista_abbDAO.findAcqByCod_acq_abb(cod_acq_abb);


            Date data_ut_abb = new Date();
            System.out.println(data_ut_abb);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data_utilizzo_abb = sdf2.format(data_ut_abb);
            System.out.println(data_utilizzo_abb);

            Utilizzo_abbDAO utilizzo_abbDAO = daoFactory.getUtilizzo_abbDAO();
            utilizzo_abbDAO.create(abbonamento,biglietto,acquista_abb,data_utilizzo_abb);


            //Decremento numero ingressi abbonamento
            acquista_abbDAO.update(acquista_abb);

            Acquista_abb acq_abb = acquista_abbDAO.findAcqByCod_acq_abb(cod_acq_abb);
            System.out.println(acq_abb.getNum_ingressi());

            if(acq_abb.getNum_ingressi() == 0){
                acquista_abbDAO.delete(acq_abb);   //cancellazione abbonamento privo di ingressi
            }


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("viewUrl", "gestioneAcquisti/acqcompletato");

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


    //Metodo comune agli altri metodi del controller GestioneAcquisti
    //Utile per passare informazioni al carrello
    private static void commonView(DAOFactory daoFactory, DAOFactory sessionDAOFactory, HttpServletRequest request) {

        Film film=null;
        Proiezione proiezione=null;
        Biglietto biglietto=null;

        //Film da passare al carrello
        String cod_film = request.getParameter("selectedcodfilm");
        if(cod_film != null){
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Long selectedcodfilm = Long.parseLong(cod_film);
            film = filmDAO.findByCodfilm(selectedcodfilm);

            //Biglietto da passare al carrello
            BigliettoDAO bigliettoDAO = daoFactory.getBigliettoDAO();
            biglietto = bigliettoDAO.findBigliettoByCod_film(selectedcodfilm);

        }


        //Proiezione da passare al carrello
        ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

        String formattedDate = request.getParameter("formattedDate");
        String formattedTime = request.getParameter("formattedTime");
        if(formattedDate != null && formattedTime != null){
            try {
                System.out.println(formattedDate);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date data_proiezione = sdf.parse(formattedDate);

                System.out.println(formattedTime);
                SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
                java.util.Date parsedDate = sdft.parse(formattedTime);
                Time ora_pro = new Time(parsedDate.getTime());

                proiezione = proiezioneDAO.findByDataOra(data_proiezione, ora_pro);
            }catch (ParseException e) {
                e.printStackTrace();
            }

        }

        request.setAttribute("film", film);
        request.setAttribute("proiezione", proiezione);
        request.setAttribute("biglietto", biglietto);
    }

}
