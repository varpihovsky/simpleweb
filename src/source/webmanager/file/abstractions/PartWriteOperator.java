package webmanager.file.abstractions;

import javax.servlet.http.Part;

public class PartWriteOperator {
    private Part part;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartWriteOperator(Part part) {
        this.part = part;
    }

    public PartWriteOperator(Part part, String name) {
        this.part = part;
        this.name = name;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
