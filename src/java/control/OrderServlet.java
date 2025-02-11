/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import model.*;
/**
 *
 * @author Audrey
 */
public class OrderServlet extends HttpServlet {
     
  /*
   * Handle GET requests
   */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException {
      
      forwardRequest(request, response, "/index.jsp");
  }
  /*
   * Handle "POST" requests
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException, ServletException {
    if (request.getParameter("order") != null) {
      handlePlaceOrder(request, response);
      forwardRequest(request, response, "/checkout.jsp");
    }    
    else if (request.getParameter("changeOrderStatus") != null) {
      handleChangeOrderStatus(request, response);
      forwardRequest(request, response, "/changeOrderStatus.jsp");
    }  
     
  }
  
  /**
   * handles placing an order
   * @param request
   * @param response
   * @throws IOException
   * @throws ServletException 
   */
  private void handlePlaceOrder(HttpServletRequest request,
          HttpServletResponse response) throws IOException, ServletException {
    
    HttpSession session = request.getSession(true);
    
    //what else do we want to do?
    String email = request.getParameter("email");
    
    //get users id
    String id = (String)session.getAttribute("userID");
    String status = "processing";
    
    //get the current date and time:
    String time = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //get current date time with Date()
    Date date = new Date();
    time+=(dateFormat.format(date));
 

    OrderUpdate ou = new OrderUpdate();
    boolean orderAdded = ou.addOrder(0, status, id, time);
    
    ShoppingCartItemUpdate su = new ShoppingCartItemUpdate();
    boolean cartCleared = su.clearCart(id);
    
    boolean success = false;
    if(cartCleared && orderAdded)
        success=true;
    
    if(success)
           session.setAttribute("orderMessage", "Order has been placed successfully");
    else
           session.setAttribute("orderMessage", "Order has not been placed");
   
  }
  
  /**
   * handles the administrator function of changing the status of an order
   * @param request
   * @param response
   * @throws IOException
   * @throws ServletException 
   */
  private void handleChangeOrderStatus(HttpServletRequest request,
          HttpServletResponse response) throws IOException, ServletException {
      
      HttpSession session = request.getSession(true);
      
      String orderID= request.getParameter("orderID");
      String status = request.getParameter("status");
      
      OrderUpdate ou = new OrderUpdate();
      boolean success = ou.changeOrderStatus(orderID, status);
      
      if(success)
          session.setAttribute("changeStatusMessage", "order status has been changed");
      else
          session.setAttribute("changeStatusMessage", "order status has not been changed");
      
  }
  
      /*
   * Forward this request to another component.
   */
  private void forwardRequest(HttpServletRequest request,
          HttpServletResponse response, String forwardUrl)
          throws IOException, ServletException {
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
            forwardUrl);
    dispatcher.forward(request, response);
  }
}
