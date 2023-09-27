package helloworld.core;

import com.google.common.base.Charsets;
import helloworld.api.NetworkInfo;
import helloworld.constants.Paths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NetworkInfoService {


    public NetworkInfo getInterfacesInfo() {

        try {

            HashMap<String, String> interfaceOneMap = new HashMap<>(40);
            HashMap<String, String> interfaceTwoMap = new HashMap<>(40);

            File interfaceOne = new File(Paths.INTERFACE_1.location);
            File interfaceTwo = new File(Paths.INTERFACE_2.location);

            File[] interfaceOneFiles = interfaceOne.listFiles();
            File[] interfaceTwoFiles = interfaceTwo.listFiles();


            assert interfaceOneFiles != null;
            assert interfaceTwoFiles != null;

            int largerArrSize = Math.max(interfaceOneFiles.length, interfaceTwoFiles.length);

            //// this way once we finish the smaller sized directory we'll continue, with the files left in the bigger directory instead of looping twice.
            for (int i = 0; i < largerArrSize; i++) {

                if (i < interfaceOneFiles.length) {

                    File file = interfaceOneFiles[i];
                    interfaceOneMap.put(interfaceOneFiles[i].getName(), file.isFile() ? readFileContent(interfaceOneFiles[i]) : "DIRECTORY");
                }

                if (i < interfaceTwoFiles.length) {

                    File file = interfaceOneFiles[i];
                    interfaceTwoMap.put(interfaceOneFiles[i].getName(), file.isFile() ? readFileContent(interfaceOneFiles[i]) : "DIRECTORY");

                }
            }
            return new NetworkInfo(interfaceOneMap, interfaceTwoMap);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


    public String readFileContent(File file) {
        // Declaring object of StringBuilder class
        StringBuilder builder = new StringBuilder();

        // try block to check for exceptions where
        // object of BufferedReader class us created
        // to read filepath
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(file.toPath().toString()))) {

            String str;

            // Condition check via buffer.readLine() method
            // holding true upto that the while loop runs
            while ((str = buffer.readLine()) != null) {

                builder.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}



/*
* addr_assign_type    dev_port	       name_assign_type      speed
address		    dormant	       napi_defer_hard_irqs  statistics
addr_len	    duplex	       netdev_group	     subsystem
broadcast	    flags	       operstate	     testing
carrier		    gro_flush_timeout  phys_port_id	     threaded
carrier_changes     ifalias	       phys_port_name	     tx_queue_len
carrier_down_count  ifindex	       phys_switch_id	     type
carrier_up_count    iflink	       power		     uevent
device		    link_mode	       proto_down
dev_id		    mtu		       queues

*
*
*
*
* addr_assign_type    dev_port	       name_assign_type      speed
address		    dormant	       napi_defer_hard_irqs  statistics
addr_len	    duplex	       netdev_group	     subsystem
broadcast	    flags	       operstate	     testing
carrier		    gro_flush_timeout  phys_port_id	     threaded
carrier_changes     ifalias	       phys_port_name	     tx_queue_len
carrier_down_count  ifindex	       phys_switch_id	     type
carrier_up_count    iflink	       power		     uevent
device		    link_mode	       proto_down
dev_id		    mtu		       queues

*
*
* */