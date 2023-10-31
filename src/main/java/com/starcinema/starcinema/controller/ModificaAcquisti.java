package com.starcinema.starcinema.controller;

import com.starcinema.starcinema.model.dao.AcquistaDAO;
import com.starcinema.starcinema.model.dao.DAOFactory;
import com.starcinema.starcinema.model.dao.UtenteDAO;
import com.starcinema.starcinema.model.mo.Acquista;
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
            AcquistaDAO acquistaDAO = daoFactory.getAcquistaDAO();
            List<Acquista> acquisti = acquistaDAO.findAcqByUsername(loggedUtente);
            System.out.println(acquisti.size());

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("acquisti", acquisti);
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
}
