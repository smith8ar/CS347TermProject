<%-- 
    Document   : index
    Created on : Nov 16, 2011, 4:50:16 PM
    Author     : lynerc
--%>

<%@page import="java.util.*, model.*" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="css/index.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
   
        <div id="minHeight"></div>
        <div id="outer">
          <div id="clearheader"></div>
          <!-- to clear header - you could use padding-top instead on the three main elements-->
          <div id="left">

            <h1>Navigation</h1>
            
                <%
                if(session.getAttribute("isAdmin")==null){                 
                %>
                
                <p>Please Login or create a new account to use the 
                full features of this web application</p>
                
                <%
                }
                else if(session.getAttribute("isAdmin").equals("no")){        
                %>
                <ul>
                <li><a href="#">My Account</a></li>
                <li><a href="shoppingcart.jsp">Shopping Cart</a></li>
                <li><a href="wishlist.jsp">Wish List</a></li> 
                </ul>
                
                <%
                    }
                    else if(session.getAttribute("isAdmin").equals("yes")){
                %>
                
                <ul>
                <li><a href="#">Orders</a></li>
                <li><a href="#">Users</a></li>
                <li><a href="#">Items</a></li>
                </ul>
                
                <%
                    }
                %>
            </ul>

          </div>
          <div id="right">
            <p>
                <%
                
                if (session.getAttribute("loggedin")!=null) {
                    UserRequest ur = new UserRequest();
                    out.print("Hello" + ur.getName((String)session.getAttribute("userEmail")));
                
                %>
            <form method="post" action="Login" >
                <p><input type="submit" name="logout" value="Log Out"/></p>
            </form>
                <%
                }
                else{
                %>
                   <jsp:include page="/login.jsp"/>
               <%
               }
               %>
                
            </p
            
            <p>
            <ul>
                <li><a href="registration.jsp">Register!</a></li>
                <li><a href="forgotPassword.jsp">Forgot Password?</a></li>
            </ul>
            </p>
          </div>
          <div id="centrecontent">
              <h1>Hello World!</h1>
            </div>
          <div id="clearfooter"></div>
          <!-- to clear footer -->
        </div>
        <!-- end outer div -->
        <div id="footer"></div>

        <div id="header">E-Commerce Application</div>
       
    </body>
</html>
