package com.starcinema.starcinema.controller;

import com.starcinema.starcinema.model.dao.*;
import com.starcinema.starcinema.model.mo.Composizione;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Sala;
import com.starcinema.starcinema.model.mo.Utente;
import com.starcinema.starcinema.services.config.Configuration;
import com.starcinema.starcinema.services.logservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestioneAcquisti {

    private GestioneAcquisti() {
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

            Film film=null;
            List<Composizione> composizioni=null;
            Sala sala=null;

            String cod_film = request.getParameter("selectedcodfilm");
            if(cod_film != null){
                FilmDAO filmDAO = daoFactory.getFilmDAO();
                Long selectedcodfilm = Long.parseLong(cod_film);
                film = filmDAO.findByCodfilm(selectedcodfilm);
                System.out.println(selectedcodfilm);
            }
            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            if(num_sala != null){
                ComposizioneDAO composizioneDAO = daoFactory.getComposizioneDAO();
                composizioni = composizioneDAO.findComposizioniByNum_sala(num_sala);

                SalaDAO salaDAO = daoFactory.getSalaDAO();
                sala = salaDAO.findSalaByNum_sala(num_sala);
            }

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("film", film);
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

    /*public static void sceltaposti(HttpServletRequest request, HttpServletResponse response) {

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

            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Long selectedcodfilm = Long.parseLong(request.getParameter("selectedcodfilm"));
            Film film = filmDAO.findByCodfilm(selectedcodfilm);


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("film", film);
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

    }*/
}
