package webmanager.file.abstractions;

public class FileOperator {
    String name;
    String toName;

    public FileOperator(String name, String toName) {
        this.name = name;
        this.toName = toName;
    }

    public FileOperator(String name) {
        this.name = name;
    }

    public FileOperator() {

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
