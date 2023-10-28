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
                            "INTERO");
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


            Acquista_abbDAO acquista_abbDAO = daoFactory.getAcquista_abbDAO();
            acquista_abbDAO.create(utente,abbonamento,data_acquisto_abb);




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
