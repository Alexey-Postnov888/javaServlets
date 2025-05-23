package org.example.servlets.javaservletsnew;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlets.javaservletsnew.models.User;
import org.example.servlets.javaservletsnew.models.UserStore;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (UserStore.getUser(username) != null) {
            request.setAttribute("error", "Пользователь с таким именем уже существует");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            User newUser = new User(username, password, email);
            UserStore.addUser(newUser);

            request.getSession().setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/files");
        }
    }
}