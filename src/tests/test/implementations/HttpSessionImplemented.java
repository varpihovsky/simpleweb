package test.implementations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.ArrayList;
import java.util.Enumeration;

public class HttpSessionImplemented implements HttpSession {
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<Object> objects = new ArrayList<>();

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i) == s)
                return objects.get(i);
        }
        return null;
    }

    @Override
    public Object getValue(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(String s, Object o) {
        names.add(s);
        objects.add(o);
    }

    @Override
    public void putValue(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public void removeValue(String s) {

    }

    @Override
    public void invalidate() {

    }

    @Override
    public boolean isNew() {
        return false;
    }
}
