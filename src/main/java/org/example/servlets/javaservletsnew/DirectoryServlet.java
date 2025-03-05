package org.example.servlets.javaservletsnew;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/files")
public class DirectoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (path == null || path.isEmpty()) {
            path = getServletContext().getRealPath("/");
        }

        File directory = new File(path);
        File[] files = directory.listFiles();

        request.setAttribute("currentPath", path);
        request.setAttribute("files", files);
        request.setAttribute("parentPath", directory.getParent());
        request.setAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));

        request.getRequestDispatcher("/directory.jsp").forward(request, response);
    }
}