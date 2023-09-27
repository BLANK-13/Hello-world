package helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Processor {

    private String processorNo;
    private String modelName;
    private String vendorId;
    private String cpuMHz;
    private String cacheSize;


    public Processor() {}

    public Processor(String processorNo, String modelName, String vendorId, String cpuMHz, String cacheSize) {
        this.processorNo = processorNo;
        this.modelName = modelName;
        this.vendorId = vendorId;
        this.cpuMHz = cpuMHz;
        this.cacheSize = cacheSize;
    }


    @JsonProperty
    public String getProcessorNo() {
        return processorNo;
    }

    @JsonProperty
    public String getModelName() {
        return modelName;
    }

    @JsonProperty
    public String getVendorId() {
        return vendorId;
    }

    @JsonProperty
    public String getCpuMHz() {
        return cpuMHz;
    }

    @JsonProperty
    public String getCacheSize() {
        return cacheSize;
    }
}
