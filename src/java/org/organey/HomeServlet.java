/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.organey;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.organey.data.dao.CategoryDao;
import org.organey.data.dao.DatabaseDao;
import org.organey.data.dao.ProductDao;
import org.organey.data.model.Category;
import org.organey.data.model.OrderDetailSession;
import org.organey.data.model.Product;

/**
 *
 * @author HIEU
 */
public class HomeServlet extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        List<Product> productList = productDao.findAll();
        List<Product> productList_5 = productDao.find_five();
        List<Product> productList_10 = productDao.find_ten();
        

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("productList", productList);
        request.setAttribute("productList_5", productList_5);
        request.setAttribute("productList_10", productList_10);
       

        request.getRequestDispatcher("home.jsp").include(request, response);
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
    }

    

}
