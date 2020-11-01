package servlet;

import business_layer.MessageBoardManager;
import database.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {
    static final String HOMEPAGE = "index.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageBoardManager m = new MessageBoardManager();

        String message = request.getParameter("message");
        String title = request.getParameter("title");
        String username = "testusername"; //this will be the currently logged in user - implement later

        m.postMessage(username, title, message);

        response.sendRedirect(HOMEPAGE);
    }
}
