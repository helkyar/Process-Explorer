/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
 
/**
 *
 * @author Javier Palacios
 */
public class GetIP {
    
    public static String getPublicIP() {
        String publicIP;
    	try {
                URL tempURL = new URL("http://checkip.amazonaws.com/");
                HttpURLConnection tempConn = (HttpURLConnection)tempURL.openConnection();
                InputStream tempInStream = tempConn.getInputStream();
                InputStreamReader tempIsr = new InputStreamReader(tempInStream);
                BufferedReader tempBr = new BufferedReader(tempIsr);        
 
                publicIP = tempBr.readLine();
 
                tempBr.close();
                tempInStream.close();
 
        } catch (Exception ex) {
                publicIP = "Null";   
          }
 
         return publicIP;
    }
}
 
