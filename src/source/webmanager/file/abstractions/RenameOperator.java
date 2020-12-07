package webmanager.file.abstractions;

public class RenameOperator {
    String name;
    String toName;


    public RenameOperator(String name, String toName) {
        this.name = name;
        this.toName = toName;
    }

    public RenameOperator(String name) {
        this.name = name;
    }

    public RenameOperator() {

    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
