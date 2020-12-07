package webmanager;

import webmanager.database.DatabaseConnector;
import webmanager.database.DatabaseController;
import webmanager.file.FileManager;
import webmanager.renderer.RenderExecutor;
import webmanager.sender.SendExecutor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig
public class Controller extends HttpServlet {
    private static final DatabaseConnector connector = new DatabaseConnector();
    private static final DatabaseController databaseController = new DatabaseController(connector);
    private FileManager fileManager;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        fileManager = new FileManager(getServletContext());

        String page = SendExecutor.execute(request, response, fileManager, databaseController);
        RenderExecutor.execute(page, request, databaseController, fileManager);

        page = "/" + page + ".jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
