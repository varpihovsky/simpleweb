package WebManager;

import WebManager.renderer.InterfaceRenderer;
import WebManager.renderer.RendererController;
import WebManager.send.InterfaceSend;
import WebManager.send.SendController;
import WebManager.send.SendExecutor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        SendController controller = new SendController();
        RendererController rendererController = new RendererController();
        SendExecutor executor = new SendExecutor();

        InterfaceSend currentSend = controller.defineSend(request);

        String page = executor.execute(currentSend, request, response, this.getServletContext());
        InterfaceRenderer currentRenderer = rendererController.defineRenderer(page);
        currentRenderer.render(request);

        page = "/" + page + ".jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
