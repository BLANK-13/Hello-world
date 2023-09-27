package helloworld.constants;

public enum Paths {


    //// is there a better way to reach these paths?
    CPU_FILE("../../../../../../../../../../proc/cpuinfo"),
    INTERFACE_1("../../../../../../../../../../sys/class/net/br-83d5a095cb16"),
    INTERFACE_2("../../../../../../../../../../sys/class/net/enp0s31f6");

    //    CPU_INFO_SHELL_SCRIPT("../cli/cpu-info.sh");

    public final String location;

    private Paths(String location) {
        this.location = location;

    }
}

