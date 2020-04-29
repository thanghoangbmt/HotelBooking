/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tintt.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tintt.daos.AccountDAO;
import tintt.dtos.AccountDTO;
import tintt.dtos.AccountError;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    private final String SUCCESS = "login.jsp";
    private final String ERROR = "register.jsp";

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
        try {
            String email = request.getParameter("txtEmail").trim();
            String password = request.getParameter("txtPassword");
            String fullname = request.getParameter("txtFullname");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");
            String role = "User";
            String status = "Active";

            AccountDAO dao = new AccountDAO();
            AccountError error = null;
            boolean valid = true;
            if (dao.checkExistEmail(email)) {
                if (error == null) {
                    error = new AccountError();
                }
                error.setEmailError("Email has been registered!");
                valid = false;
            }

            if (valid) {
                AccountDTO dto = new AccountDTO(email, password, fullname, phone, address, role, status);
                boolean result = dao.registerAccount(dto);
                if (result) {
                    url = SUCCESS;
                }
            } else {
                url = ERROR;
                request.setAttribute("REGISTERERROR", error);
            }
        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
        } finally {
            if (url.equals(SUCCESS)) {
                response.sendRedirect(url);
            } else if (url.equals(ERROR)) {
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
