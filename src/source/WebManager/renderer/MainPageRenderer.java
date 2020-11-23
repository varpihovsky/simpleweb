package WebManager.renderer;

import javax.servlet.http.HttpServletRequest;

public class MainPageRenderer implements InterfaceRenderer {
    @Override
    public void render(HttpServletRequest request) {
        String rendered;
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        if (username == null || password == null || username.equals("") || password.equals("")) {
            rendered = "<a href=\"/controller?page=login&send=redirect\">Вход</a>\n" +
                    "        <a href=\"/controller?page=register&send=redirect\">Регистрация</a>";
        } else rendered = "<a href=\"/controller?page=profile&send=redirect\">Профиль</a>\n" +
                "        <a href=\"/controller?page=main&send=logout\">Выход</a>";
        request.setAttribute("render", rendered);
    }
}
