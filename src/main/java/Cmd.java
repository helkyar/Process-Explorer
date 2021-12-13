/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Javier Palacios Botejara
 */
class Cmd {
    /** 
    * Overloaded method for executing command process
    * @param comand command to be executed in string form
    * @param i          min line start
    * @param j          max line end
    * @param sep        break point for the response
    * @param endline    how to end each returned line
    * @return           string response of the executed command
    */
    public static String run(String comand, int i, int j, String sep, String endline){
        String s, msg = "";
        try{
            Process p = Runtime.getRuntime().exec(comand);  //IOEx
            msg = runLogic(p, i, j, sep, endline); //IOEx, InterruptedEx

        } catch(IOException e){LogGen.error(e.getMessage());}
          catch(InterruptedException e){LogGen.error(e.getMessage());} 

        return msg;
    }
    
    public static String run(String comand){
        return run(comand, -1,1000, "", "");
    }
    public static String run(String comand, String endline){
        return run(comand, -1, 1000, "", endline);
    }
    public static String run(String comand, int i, int j){
        return run(comand, i, j, "", "");
    }
    public static String run(String comand, int i, int j, String sep){
        return run(comand, i, j, sep, "");
    }
    
    /** 
    * Overloaded method for executing command process
    * @param comand command to be executed in array form
    * @param i          min line start
    * @param j          max line end
    * @param sep        break point for the response
    * @param endline    how to end each returned line
    * @return           string response of the executed command
    */
    public static String run(String[] comand, int i, int j, String sep, String endline){
        String s, msg = "";
        try{
            Process p = Runtime.getRuntime().exec(comand);  //IOEx
            msg = runLogic(p, i, j, sep, endline); //IOEx, InterruptedEx

        } catch(IOException e){LogGen.error(e.getMessage());}
          catch(InterruptedException e){LogGen.error(e.getMessage());} 

        return msg;
    }

    public static String run(String[] comand){
        return run(comand, -1,1000, "", "");
    }
    public static String run(String[] comand, String endline){
        return run(comand, -1, 1000, "", endline);
    }
    public static String run(String[] comand, int i, int j){
        return run(comand, i, j, "", "");
    }
    public static String run(String[] comand, int i, int j, String sep){
        return run(comand, i, j, sep, "");
    }

    private static String runLogic (Process p, int i, int j, String sep, String endline)
            throws InterruptedException, IOException {
        String s, msg = "";
        BufferedReader br = new BufferedReader(
        new InputStreamReader(p.getInputStream()));
        
        for(int z = 0; (s = br.readLine()) != null; z++){ //IOEx
            if(z > i && z < j) {msg += s + endline;}
        }

        if(!sep.equals("") && msg.contains(sep)){
            msg = msg.substring(msg.indexOf(sep)+sep.length());
        }

        p.waitFor(); // InterruptedEx
        p.destroy();  
        
        return msg;
    }
    // Execute on thread
    public static String runThr(String comand, String sep){
        return runThr(comand, -1, 1000, sep);
    }
    public static String runThr(String comand, int i, int j, String sep){
        
        //Redeclare to implement getResult method
        class ParalelReader extends Thread{public String getResult(){return "";}};
        String msg = "";
        
        try {
            ParalelReader reader = new ParalelReader(){
                private String s, msg = "";
                Process p = Runtime.getRuntime().exec(comand);  //IOEx

                @Override
                public void run(){
                    try {
                        BufferedReader br = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));
                        //IOEx
                        for(int z = 0; (s = br.readLine()) != null; z++){ //IOEx
                            if(z > i && z < j) {msg += s + "\n";}
                        }
                        if(!sep.equals("") && msg.contains(sep)){
                            msg = msg.substring(msg.indexOf(sep)+sep.length());
                        }
                     } catch(IOException e){LogGen.error(e.getMessage());}
                }

                @Override
                public String getResult() {
                    try{
                        p.waitFor(); // InterruptedEx
                        LogGen.error ("exit: " + p.exitValue());
                        p.destroy();  
                    } catch(InterruptedException e){LogGen.error(e.getMessage());} 
                    return this.msg;
                }
            };
            
            reader.start();
            msg = reader.getResult();
        } catch(IOException e){LogGen.error(e.getMessage());}
        
        return msg;
    }
}
