package helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class NetworkInfo {

    @JsonProperty
    private Map<String, String> interface1;

    @JsonProperty
    private Map<String, String> interface2;


    public NetworkInfo() {}

    public NetworkInfo(Map<String, String> interface1, Map<String, String> interface2) {
        this.interface1 = interface1;
        this.interface2 = interface2;
    }

    public Map<String, String> getInterface1() {
        return interface1;
    }

    public Map<String, String> getInterface2() {
        return interface2;
    }
}
