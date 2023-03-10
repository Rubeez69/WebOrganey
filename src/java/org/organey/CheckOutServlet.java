/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.organey;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.organey.data.dao.DatabaseDao;
import org.organey.data.dao.OrderDao;
import org.organey.data.dao.OrderDetailDao;
import org.organey.data.model.Order;
import org.organey.data.model.OrderDetail;
import org.organey.data.model.OrderDetailSession;
import org.organey.data.model.User;
import org.organey.data.util.StringHelper;

/**
 *
 * @author HIEU
 */
public class CheckOutServlet extends BaseServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("LoginServlet");
        } else {
            OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
            String code = StringHelper.randomString(9);
            Order order = new Order(code, "order", "pending", user.getId());
            orderDao.insert(order);

            order = orderDao.find(code);

            OrderDetailDao orderDetailDao = DatabaseDao.getInstance().getOrderDetailDao();

            List<OrderDetailSession> cart = (List<OrderDetailSession>) session.getAttribute("cart");
            if (cart != null) {
                for (OrderDetailSession ods : cart) {
                    OrderDetail orderDetail = new OrderDetail(ods.getQuantity(), order.getId(), ods.getProductId(), ods.getProductPrice());
                    orderDetailDao.insert(orderDetail);
                }
            }

            session.removeAttribute("cart");
            response.sendRedirect("CartServlet");
        }

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
