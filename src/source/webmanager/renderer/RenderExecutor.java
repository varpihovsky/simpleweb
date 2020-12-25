package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.file.FileManager;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;

public class RenderExecutor {
    private static InterfaceRenderer defineRenderer(String page) {
        InterfaceRenderer currentRenderer = new NullRenderer();

        try {
            RendererEnum rendererEnum = RendererEnum.getInstance(page);
            currentRenderer = rendererEnum.getCurrentRenderer();
            return currentRenderer;
        } catch (IllegalArgumentException e) {
            return currentRenderer;
        }
    }

    public static void execute(String page, HttpServletRequest request, DatabaseController databaseController,
                               FileManager fileManager) {
        InterfaceRenderer renderer = defineRenderer(page);

        Checker.initializeObjects((Operative) renderer, fileManager, databaseController);

        renderer.render(request);
    }
}
