package helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SystemInfo {

    @JsonProperty
    private List<Processor> systemCpus;

    @JsonProperty
    private int totalCpus;

    public SystemInfo() {}

    public SystemInfo(List<Processor> systemCpus, int totalCpus) {
        this.systemCpus = systemCpus;
        this.totalCpus = totalCpus;
    }
}
