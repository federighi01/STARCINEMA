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

import static java.lang.Long.parseLong;

public class HomeManagement {
    private HomeManagement() {
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


            List<Film> filmsdp=null;
            Film film=null;

            String titolo = request.getParameter("titolo");

            String data_pro = request.getParameter("data_pro");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data_proiezione = null;

            if (data_pro != null) {
                try {
                    data_proiezione = sdf.parse(data_pro);
                } catch (Exception e) {
                }
            }


            if(titolo!=null ){
                FilmDAO filmDAO = daoFactory.getFilmDAO();
                film = filmDAO.findByTitolo(titolo);

                //Sezione per passare data_pro e ora_pro alla nuovospettacolo.jsp per ricerca titolo film
                List<Proiezione> proiezioni;

                ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

                //Estrarre dati di proiezione per il film
                proiezioni=proiezioneDAO.findData_proByCod_film(film);
                //Conversione lista in array
                Proiezione [] proiezioniArray = new Proiezione[proiezioni.size()];
                for(int j=0;j<proiezioni.size();j++){
                    proiezioniArray[j] = proiezioni.get(j);
                }
                film.setProiezioni(proiezioniArray);
            }

            if(titolo==null && data_proiezione!=null){
                FilmDAO filmDAO = daoFactory.getFilmDAO();
                filmsdp = filmDAO.findFilmByData_pro(data_proiezione);
            }

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("film", film);
            request.setAttribute("titolo", titolo);
            request.setAttribute("filmsdp", filmsdp);

            request.setAttribute("viewUrl", "homeManagement/view");

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

    public static void schedafilm(HttpServletRequest request, HttpServletResponse response) {

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
            Film film = filmDAO.findByCodfilm(Long.parseLong(request.getParameter("selectedcodfilm")));

            //Sezione dedicata alle recensioni
            List<Recensione> recensioni;

            RecensioneDAO recensioneDAO = daoFactory.getRecensioneDAO();
            recensioni = recensioneDAO.findRecensioni(film.getCod_film());

            //Parte per passare data_pro e ora_pro a schedafilm.jsp
            List<Proiezione> proiezioni;

            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

                //Estrarre dati di proiezione per il film
                proiezioni=proiezioneDAO.findData_proByCod_film(film);
                //Conversione lista in array
                Proiezione [] proiezioniArray = new Proiezione[proiezioni.size()];
                for(int j=0;j<proiezioni.size();j++){
                    proiezioniArray[j] = proiezioni.get(j);
                }
                film.setProiezioni(proiezioniArray);


            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("film", film);
            request.setAttribute("recensioni", recensioni);
            request.setAttribute("viewUrl", "homeManagement/schedafilm");

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

    public static void deleterec(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory= null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;

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

            RecensioneDAO recensioneDAO = daoFactory.getRecensioneDAO();
            Recensione recensione = recensioneDAO.findByCod_rec(Long.parseLong(request.getParameter("cod_rec")));
            recensioneDAO.delete(recensione);

            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByCodfilm(Long.parseLong(request.getParameter("selectedcodfilm")));

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn",loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("film", film);
            request.setAttribute("viewUrl", "homeManagement/schedafilm");

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

    public static void logon(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        String applicationMessage = null;


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

            commonView(daoFactory, sessionDAOFactory, request);

            String username = request.getParameter("username");
            String pw = request.getParameter("pw");

            UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
            Utente utente = utenteDAO.findByUsername(username);

            if (utente == null || !utente.getPw().equals(pw)) {
                sessionUtenteDAO.delete(null);
                applicationMessage = "Username e password errati!";
                loggedUtente = null;
            } else {
                loggedUtente = sessionUtenteDAO.create(utente.getUsername(), null, null, utente.getTipo(), utente.getCognome(), utente.getNome(), null, null, null, null);
            }

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente != null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("applicationMessage", applicationMessage);

            request.setAttribute("viewUrl", "homeManagement/view");

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

    public static void logout(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;


        Logger logger = LogService.getApplicationLogger();

        try {

            Map sessionFactoryParameters = new HashMap<String, Object>();
            sessionFactoryParameters.put("request", request);
            sessionFactoryParameters.put("response", response);
            sessionDAOFactory = DAOFactory.getDAOFactory(Configuration.COOKIE_IMPL, sessionFactoryParameters);
            sessionDAOFactory.beginTransaction();

            UtenteDAO sessionUtenteDAO = sessionDAOFactory.getUtenteDAO();
            sessionUtenteDAO.delete(null);

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL, null);
            daoFactory.beginTransaction();

            commonView(daoFactory, sessionDAOFactory, request);

            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", false);
            request.setAttribute("loggedUtente", null);

            request.setAttribute("viewUrl", "homeManagement/view");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }
        }
    }

    public static void regView(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
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

            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", false);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("viewUrl", "homeManagement/Registrazione");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }

        }

    }

    public static void reg(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        String applicationMessage = null;


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

            String data_n = request.getParameter("data_n");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data_nascita = null;
            data_nascita = sdf.parse(data_n);


            utenteDAO.create(
                    request.getParameter("username"),
                    request.getParameter("pw"),
                    request.getParameter("email"),
                    request.getParameter("tipo"),
                    request.getParameter("cognome"),
                    request.getParameter("nome"),
                    data_nascita,
                    request.getParameter("luogo_n"),
                    request.getParameter("indirizzo"),
                    request.getParameter("tel"));

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();

            //System.out.println(sdf.parse(data_n));
            request.setAttribute("loggedOn", false);
            request.setAttribute("loggedUtente", null);
            request.setAttribute("applicationMessage", applicationMessage);

            request.setAttribute("viewUrl", "homeManagement/view");

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

    public static void newspectView(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
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

            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("viewUrl", "homeManagement/nuovospettacolo");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }

        }

    }

    public static void newproView(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
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

            Long selectedcodfilm = Long.parseLong(request.getParameter("selectedcodfilm"));

            sessionDAOFactory.commitTransaction();

            request.setAttribute("loggedOn", loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("selectedcodfilm", selectedcodfilm);
            request.setAttribute("viewUrl", "homeManagement/nuovospettacolo");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.rollbackTransaction();
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (sessionDAOFactory != null) sessionDAOFactory.closeTransaction();
            } catch (Throwable t) {
            }

        }

    }

    public static void newspect(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        String applicationMessage = null;


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

            filmDAO.create(
                    request.getParameter("titolo"),
                    request.getParameter("regista"),
                    request.getParameter("cast"),
                    request.getParameter("genere"),
                    Integer.parseInt(request.getParameter("durata")),
                    request.getParameter("nazione"),
                    Integer.parseInt(request.getParameter("anno")),
                    request.getParameter("descrizione"),
                    request.getParameter("trailer"));

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("applicationMessage", applicationMessage);

            request.setAttribute("viewUrl", "homeManagement/view");

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

    public static void newpro(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        String applicationMessage = null;


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

            ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

            Long selectedcodfilm = Long.parseLong(request.getParameter("selectedcodfilm"));
            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByCodfilm(selectedcodfilm);
            //System.out.println(selectedcodfilm);

            Integer num_sala = Integer.parseInt(request.getParameter("num_sala"));
            SalaDAO salaDAO = daoFactory.getSalaDAO();
            Sala sala = salaDAO.findSalaByNum_sala(num_sala);
            //System.out.println(num_sala);


            String data_pro = request.getParameter("data_pro");
            System.out.println(data_pro);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data_proiezione = null;
            data_proiezione = sdf.parse(data_pro);



            String ora_proString = request.getParameter("ora_pro");
            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
            java.util.Date parsedDate = sdft.parse(ora_proString);
            Time ora_pro = new Time(parsedDate.getTime());
            //String ora_proFormatted = sdft.format(ora_pro);

            proiezioneDAO.create(
                    film,
                    sala,
                    data_proiezione,
                    ora_pro);

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("applicationMessage", applicationMessage);

            request.setAttribute("viewUrl", "homeManagement/view");

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

    public static void insrec(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory sessionDAOFactory = null;
        DAOFactory daoFactory = null;
        Utente loggedUtente;
        String applicationMessage = null;


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

            RecensioneDAO recensioneDAO = daoFactory.getRecensioneDAO();

            UtenteDAO utenteDAO = daoFactory.getUtenteDAO();
            Utente utente = utenteDAO.findByUsername(loggedUtente.getUsername());

            FilmDAO filmDAO = daoFactory.getFilmDAO();
            Film film = filmDAO.findByCodfilm(Long.parseLong(request.getParameter("selectedcodfilm")));


            recensioneDAO.create(
                    utente,
                    film,
                    Integer.parseInt(request.getParameter("voto")),
                    request.getParameter("commento"));

            List<Recensione> recensioni;

            recensioni = recensioneDAO.findRecensioni(film.getCod_film());

            commonView(daoFactory, sessionDAOFactory, request);

            daoFactory.commitTransaction();
            sessionDAOFactory.commitTransaction();


            request.setAttribute("loggedOn", loggedUtente!=null);
            request.setAttribute("loggedUtente", loggedUtente);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("film", film);
            request.setAttribute("recensioni", recensioni);
            request.setAttribute("viewUrl", "homeManagement/schedafilm");

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

    private static void commonView(DAOFactory daoFactory, DAOFactory sessionDAOFactory, HttpServletRequest request) {

        List<Film> films;
        List<Proiezione> proiezioni;

        FilmDAO filmDAO = daoFactory.getFilmDAO();
        films = filmDAO.findFilm();

        ProiezioneDAO proiezioneDAO = daoFactory.getProiezioneDAO();

        for(int i=0;i< films.size();i++){
            //Estrarre dati di proiezione per ogni film
            proiezioni=proiezioneDAO.findData_proByCod_film(films.get(i));
            //Conversione lista in array
            Proiezione [] proiezioniArray = new Proiezione[proiezioni.size()];
            for(int j=0;j<proiezioni.size();j++){
                proiezioniArray[j] = proiezioni.get(j);
            }
            films.get(i).setProiezioni(proiezioniArray);
        }
        request.setAttribute("films", films);

    }

}
