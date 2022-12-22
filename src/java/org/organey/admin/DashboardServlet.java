/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.organey.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.organey.data.dao.Database;
import org.organey.data.dao.OrderDao;
import org.organey.data.dao.ProductDao;
import org.organey.data.dao.UserDao;
import org.organey.data.model.Order;
import org.organey.data.model.OrderDetail;
import org.organey.data.model.Product;
import org.organey.data.util.Constants;
import org.organey.data.util.GetDateTime;

/**
 *
 * @author HIEU
 */
public class DashboardServlet extends BaseAdminServlet {

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
        super.doGet(request, response);
        UserDao userDao = Database.getInstance().getUserDao();
        int numberUser = userDao.findAll().size();

        ProductDao productDao = Database.getInstance().getProductDao();
        int numberProduct = productDao.findAll().size();

        List<Product> productList = productDao.findAll();

        OrderDao orderDao = Database.getInstance().getOrderDao();
        int numberOrder = orderDao.findAll().size();
        double total = getTotal();

        request.setAttribute("productList", productList);
        request.setAttribute("numberUser", numberUser);
        request.setAttribute("numberProduct", numberProduct);
        request.setAttribute("numberOrder", numberOrder);
        request.setAttribute("total", total);

        List<Order> orderPendingList = Database.getInstance().getOrderDao().findByStatus("pending");
        request.setAttribute("orderPendingList", orderPendingList);

        List<String> dateList = GetDateTime.getDates(Constants.NUMBER_DAY);
        request.setAttribute("dateList", dateList);

        List<Integer> countEachDay = new ArrayList<>();
        for (int i = 0; i < Constants.NUMBER_DAY; i++) {
            countEachDay.add(orderDao.countOrderByDay(dateList.get(i)));
        }
        request.setAttribute("countEachDay", countEachDay);

        request.getRequestDispatcher("admin/dashboard.jsp").include(request, response);
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
        super.doPost(request, response);
    }

    private double getTotal() {
        double total = 0;
        List<Order> orderList = Database.getInstance().getOrderDao().findByStatus("finish");
        for (Order order : orderList) {
            List<OrderDetail> orderDetaiList = Database.getInstance().getOrderDetailDao().findByOrder(order.id);
            total += sum(orderDetaiList);
        }
        return total;
    }

    private double sum(List<OrderDetail> orderDetaiList) {
        double s = 0;
        for (OrderDetail orderDetail : orderDetaiList) {
            s += orderDetail.amount * orderDetail.price;
        }

        return s;
    }

}
