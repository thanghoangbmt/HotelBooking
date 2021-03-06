/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tintt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tintt.daos.AccountDAO;
import tintt.dtos.AccountDTO;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private final String ADMIN = "AdminArticleIndexController";
    private final String USER = "IndexController";
    private final String ERROR = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean result = false;

        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            AccountDAO dao = new AccountDAO();
            AccountDTO dto = dao.checkLogin(email, password);
            if (dto != null) {
                HttpSession session = request.getSession();
                if (dto.getRole().equals("Admin")) {
                    url = ADMIN;
                } else if (dto.getRole().equals("User")) {
                    url = USER;
                }
                session.setAttribute("USER", dto);
                result = true;
            } else {
                request.setAttribute("LOGINERROR", "Incorrect Email or Password!");
                result = false;
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            if (result) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
