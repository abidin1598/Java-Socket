/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author SMK
 */
public class JavaApplication1 {
private static ServerSocket servSock;
    private static final int PORT = 14808;

    public static void main(String[] args)
    {
        
        
        System.out.println("Opening port...\n");
        try
        {
            servSock = new ServerSocket(PORT);      //Step 1.
        }
        catch(IOException ioEx)
        {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
        do
        {
            handleClient();
        }
        while (true);
    }

    private static void handleClient()
    {
        Socket link = null;                     			//Step 2.
        try
        {
            link = servSock.accept();        				//Step 2.
            System.out.println("Client accept!");
            Scanner input = new Scanner(link.getInputStream()); 	//Step 3.
            PrintWriter output =  new PrintWriter(link.getOutputStream(),true); 	//Step 3.
            BufferedReader in=new BufferedReader(new InputStreamReader(link.getInputStream()));
            int numMessages = 0;
            //String message = input.nextLine();      			//Step 4.
          
            int inpt;
            String oxunan="";	String soz=""; String oxu="dur";
            
            while((inpt=in.read())!=-1) { 
              if ( inpt >= 0 ) {
			            char c = (char) inpt;
			           oxunan= Character.toString(c);   //oxunan=oxunan+c; //'c' charini string edirik
			            }
		if (oxunan.equals("&") ) { oxu="basla";  }  //System.out.println("yeni soz: \n");
	         if (oxu.equals("basla") ) { soz=soz+oxunan; }
	            if (oxunan.equals("!") ) {
	               oxu="dur";  
	               System.out.println("\n==="+soz+"===\n"); // soz = Receive Data
	                   
	                    }
                     if (oxu.equals("dur")){
                        
                         soz="";
                     }
                    
           
        }
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println( "\n* Closing connection... *");
                link.close();                    //Step 5.
            }
            catch(IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }


 public static String now(String dateFormat) {
                           Calendar cal = Calendar.getInstance();
                           SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                           return sdf.format(cal.getTime());

		  }

}

