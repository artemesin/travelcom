package user.domain;

import java.util.HashMap;
import java.util.Map;

public enum ClientType {
    NEW("First time"),
    REGULAR("Regular client"),
    OLD("Was a far time ago"),
    VIP("Very important person"),
    BAD("Bad client"),
    IN_THE_BLACK_LIST("Ignore");

    private Map<String, ClientType> map = new HashMap<>();

    public boolean containsInEnum(String str){
        if(!map.isEmpty()){
            return map.containsKey(str);
        }
        return false;
    }

    private String description;

    ClientType(String description){
        this.description = description;
        map.put(this.name(), this);
    }

    public String getDescription() {
        return description;
    }
}
