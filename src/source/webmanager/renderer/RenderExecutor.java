package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.operations.required.DatabaseCommunicative;
import webmanager.file.FileManager;
import webmanager.file.operations.required.FileOperating;

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

        if (renderer instanceof DatabaseCommunicative) {
            ((DatabaseCommunicative) renderer).setController(databaseController);
        }
        if (renderer instanceof FileOperating) {
            ((FileOperating) renderer).setFileManager(fileManager);
        }

        renderer.render(request);
    }
}
