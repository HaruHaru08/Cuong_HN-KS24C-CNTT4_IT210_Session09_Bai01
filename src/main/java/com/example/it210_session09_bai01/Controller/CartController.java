package com.example.it210_session09_bai01.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @WebServlet("/add-to-cart")
    public class AddToCartServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            List<String> cart = (List<String>) session.getAttribute("cart");

            if (cart == null) {
                cart = new ArrayList<>();
            }

            String product = request.getParameter("productName");
            cart.add(product);
            session.setAttribute("cart", cart);
            response.sendRedirect("/checkout");
        }
    }
    @WebServlet("/checkout")
    public class CheckoutServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();

            List<String> cart = (List<String>) session.getAttribute("cart");
            int size = (cart != null) ? cart.size() : 0;
            response.setContentType("text/html");
            response.getWriter().println("<h1>Giỏ hàng của bạn</h1>");
            response.getWriter().println("<p>Số lượng sản phẩm: " + size + "</p>");

            if (size > 0) {
                for (String item : cart) {
                    response.getWriter().println("- " + item + "<br>");
                }
            }
        }
    }
}
