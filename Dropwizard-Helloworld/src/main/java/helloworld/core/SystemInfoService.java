package helloworld.core;

import helloworld.api.Processor;
import helloworld.constants.Paths;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SystemInfoService {

//    private void makeCpuInfoFile() throws IOException, InterruptedException {
//
//        Process process = Runtime.getRuntime().exec("sh " + Paths.CPU_INFO_SHELL_SCRIPT.location);
//        process.waitFor();
//
//        int exitValue = process.exitValue();
//
//        if (exitValue != 0) {
//            throw new IOException("Something went wrong with the file.");
//        }
//    }

    public List<Map<String, String>> getProcessorsInfo() throws IOException, InterruptedException {

        // makeCpuInfoFile();

        /// we'll get a list of Map where a map is a single processor's info like so, { processor=16, cpu MHz=2100.000, address sizes=46 bits physical, 48 bits virtual...}

        List<Map<String, String>> cpuInfosList = new ArrayList<>(20); //// a fair estimation of CPUs count.
        Map<String, String> cpuInfoMap = new HashMap<>(30);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.CPU_FILE.location));
        String line = bufferedReader.readLine();

        int lineCount = 1;

        while (line != null) {

            /// for extracting the CPU attributes to a reachable entry in a map.

            String[] parts = line.split(":");
            if (parts.length > 1) {

                String key = parts[0].trim();
                String value = parts[1].trim();

                cpuInfoMap.put(key, value);

            }

            line = bufferedReader.readLine();
            lineCount++;

            //// CPUs are seperated by a double \n in the multiple of 28 lines, so I'll use that to determine when we finished from one cpu and then add its info and clear its map for the next CPU.
            if (lineCount % 28 == 0) {
                cpuInfosList.add(new HashMap<>(cpuInfoMap));
                cpuInfoMap.clear();
            }
        }

        bufferedReader.close();

        return cpuInfosList;
    }


    public List<Processor> getProcessors() {

        try {

            List<Map<String, String>> cpuInfsoList = getProcessorsInfo();
            List<Processor> processorList = new ArrayList<>(cpuInfsoList.size());  //// giving an initial capacity if we know or have a good estimation is always good.

            //// we already have all the CPU's info, but I'll use only the following attributes.
            String processorNo;
            String modelName;
            String vendorId;
            String cpuMHz;
            String cacheSize;

            for (Map<String, String> cpu : cpuInfsoList) {

                processorNo = cpu.get("processor");
                modelName = cpu.get("model name");
                vendorId = cpu.get("vendor_id");
                cpuMHz = cpu.get("cpu MHz");
                cacheSize = cpu.get("cache size");


                processorList.add(new Processor(processorNo, modelName, vendorId, cpuMHz, cacheSize));
            }

            return processorList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
