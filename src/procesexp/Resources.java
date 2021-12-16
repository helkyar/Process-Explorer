package procesexp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
* Method to check against disposable ram 
* Method to check against disks (withc one)
* Method to print disk/ram info
*/
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.IOException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JTextArea;

/**
 *
 * @author Javier Palacios Botejara
 */
public class Resources{
    private final int mb = 1024 * 1024;
    private final int gb = mb * 1024;

    public int getMemory() {
        final Runtime instance = Runtime.getRuntime();
        final long total = instance.totalMemory()/mb;
        final long free = instance.freeMemory()/mb;
        final long used = total-free;    
        final long max = instance.maxMemory()/mb;
        return getPercentage(total, free);
    }

    public int getStorage(int i) {
        File[] file = File.listRoots();
        if(i>file.length-1 || i < 0) {i=0;}
        long totalSpace = file[i].getTotalSpace()/mb; //total disk space in mb.
        long freeSpace =  file[i].getFreeSpace()/mb; //unallocated / free disk space in mb.
        long usedSpace = totalSpace-freeSpace; //unallocated / free disk space in mb.
        return getPercentage(totalSpace, freeSpace);
    }    
    
    private int getPercentage(long total, long free){
        int p = 1;
        try {
            p=(int) ((total - free)*100/total);
        } catch (Exception e) {
            LogGen.error("SEVERE: method Resources.getPercentage"+e.getMessage());
        }
        return p;
    }
    
    public boolean isConnected(){
        try{ 
            InetAddress in = InetAddress.getByName("www.google.com");
            return in.isReachable(2000);
        } catch(UnknownHostException e){LogGen.error("Traying to access "+e.getMessage()+" but failed");}
          catch(IOException e){LogGen.error(e.getMessage());}
        
       return false;
    }
}
