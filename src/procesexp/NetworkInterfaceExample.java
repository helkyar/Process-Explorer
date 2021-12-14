package procesexp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Academia
 */
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
 
public class NetworkInterfaceExample {
 
    public static String[] getInterfaces() throws SocketException {
         
        // NetworkInterface implements a static method that returns all the interfaces on the PC,
        // which we add on a list in order to iterate over them.
         String[] miarray = new String[2];
         int i = 0;
        System.out.println("Printing information about the available interfaces...\n");
        for (NetworkInterface iface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
             
            miarray[i] = iface.getDisplayName();
            i++;
            // Due to the amount of the interfaces, we will only print info
            // about the interfaces that are actually online.
            if (iface.isUp()) {
                 
                // Display name
                System.out.println("Interface name: " + iface.getDisplayName());
                 
                // Interface addresses of the network interface
                System.out.println("\tInterface addresses: ");
                for (InterfaceAddress addr : iface.getInterfaceAddresses()) {
                    System.out.println("\t\t" + addr.getAddress().toString());
                }
                 
                // MTU (Maximum Transmission Unit)
                System.out.println("\tMTU: " + iface.getMTU());
                 
                // Subinterfaces
                System.out.println("\tSubinterfaces: " + Collections.list(iface.getSubInterfaces()));
                 
                // Check other information regarding the interface
                System.out.println("\tis loopback: " + iface.isLoopback());
                System.out.println("\tis virtual: " + iface.isVirtual());
                System.out.println("\tis point to point: " + iface.isPointToPoint());
            }
        }
        return miarray;
    }
}

