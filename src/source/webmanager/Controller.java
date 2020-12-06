package webmanager;

import webmanager.database.DatabaseConnector;
import webmanager.database.DatabaseController;
import webmanager.renderer.InterfaceRenderer;
import webmanager.renderer.RendererController;
import webmanager.send.InterfaceSend;
import webmanager.send.SendController;
import webmanager.send.SendExecutor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static DatabaseConnector connector = new DatabaseConnector();

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
        SendController controller = new SendController();
        RendererController rendererController = new RendererController();
        SendExecutor executor = new SendExecutor();

        DatabaseController databaseController = new DatabaseController(connector);

        InterfaceSend currentSend = controller.defineSend(request);

        String page = executor.execute(currentSend, request, response, getServletContext(), databaseController);
        InterfaceRenderer currentRenderer = rendererController.defineRenderer(page);
        currentRenderer.render(request, databaseController);

        page = "/" + page + ".jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
