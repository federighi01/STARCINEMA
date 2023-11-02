package com.starcinema.starcinema.controller;

import com.starcinema.starcinema.model.dao.*;
import com.starcinema.starcinema.model.mo.*;
import com.starcinema.starcinema.services.config.Configuration;
import com.starcinema.starcinema.services.logservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SituazioneSale {

    private SituazioneSale() {
    }

    public static void view(HttpServletRequest request, HttpServletResponse response) {

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


            //Trova tutte le sale disponibili
            List<Sala> sale = null;
            SalaDAO salaDAO = daoFactory.getSalaDAO();
            sale = salaDAO.findSale();


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("sale", sale);
            request.setAttribute("viewUrl", "situazioneSale/view");

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


            //Visualizzazione proiezione filtrata per numero di sala
            List<Proiezione> proiezioni=null;
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();


            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            System.out.println(num_sala);

            proiezioni = proiezioneDAO.findProByNum_sala(num_sala);



            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("proiezioni", proiezioni);
            request.setAttribute("num_sala", num_sala);
            request.setAttribute("viewUrl", "situazioneSale/view");

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



            List<Proiezione> proiezioni_ora=null;
            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();


            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            System.out.println(num_sala);


            String data_pro = request.getParameter("data_pro");
            System.out.println(data_pro);
            Date data_proiezione = null;
            if(data_pro != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                data_proiezione = sdf.parse(data_pro);
                System.out.println(data_proiezione);

                //Trova l'ora di proiezione tramite numero di sala e data di proiezione
                proiezioni_ora = proiezioneDAO.findOraBySalaData(num_sala,data_proiezione);
                System.out.println(proiezioni_ora.size());
            }



            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("proiezioni_ora", proiezioni_ora);
            request.setAttribute("num_sala", num_sala);
            request.setAttribute("data_pro", data_pro);
            request.setAttribute("viewUrl", "situazioneSale/view");

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


    //Metodo per la visualizzazione della sala e relativi posti
    public static void cercaSala(HttpServletRequest request, HttpServletResponse response) {

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

            //Passaggio informazioni di sessione utente loggato (dati salvati in CookieImpl/UtenteDAOCookieImpl)
            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            loggedUtente = sessionUtenteDAO.findLoggedUtente();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();


            List<Composizione> composizioni=null;


            //Data passata dalla funzione innescata dal bottone per la ricerca della sala per data
            //di proiezione (con relativa conversione in Date)
            String data_pro = request.getParameter("data_pro");
            System.out.println(data_pro);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data_proiezione = null;
            if (data_pro != null) {
                    data_proiezione = sdf.parse(data_pro);
                    System.out.println(data_proiezione);
            }

            String ora_pro = request.getParameter("ora_pro");
            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
            Time ora_proiezione = null;
            if(ora_pro != null){
                java.util.Date parsedDate = sdft.parse(ora_pro);
                ora_proiezione = new Time(parsedDate.getTime());
                System.out.println(ora_proiezione);
            }

            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            System.out.println(num_sala);


            if(ora_proiezione != null && data_proiezione != null){

                //Trova proiezione filtrata per data e ora di proiezione
                ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();
                Proiezione proiezione = proiezioneDAO.findByDataOra(data_proiezione,ora_proiezione);

                //Trova i posti per numero sala, data e ora proiezione selezionati
                ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
                composizioni = composizioneDAO.findComposizioniByNum_sala(num_sala, proiezione.getCod_pro());

            }

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("composizioni", composizioni);
            request.setAttribute("num_sala", num_sala);
            request.setAttribute("viewUrl", "situazioneSale/view");

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
}
