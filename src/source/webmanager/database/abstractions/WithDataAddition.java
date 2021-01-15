package webmanager.database.abstractions;

import java.util.HashMap;

class WithDataAddition {
    protected HashMap<String, Object> additionalData = new HashMap<>();

    public void setAdditionalData(String attributeName, Object data) {
        additionalData.put(attributeName, data);
    }

    public Object getAdditionalData(String attributeName) {
        return additionalData.get(attributeName);
    }
}
