

import java.net.*;
import java.io.*;

public class TCPServer
 {
   public static void main(String[] args) throws IOException 
   {	
	   
	ServerSocket mysocket = new ServerSocket(6670);
        System.out.println("Server is running");
        Socket connectionSocket = mysocket.accept();
         
      
      try
      {  
    	  
    	  
    	
         BufferedReader inn = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));     	
         int choice_client=Integer.parseInt(inn.readLine());
         String source=inn.readLine();
         String destination=inn.readLine();
         if(choice_client==1)
         {
         byte[] b = new byte[10];
         FileOutputStream fos=new FileOutputStream(destination);
         
         System.out.println("now reading");
         int file_size1=Integer.parseInt(inn.readLine());
         System.out.println("The number of bytes that would be copied is"+file_size1);
         String h =inn.readLine();
         int f = Integer.parseInt(h);
         String g=inn.readLine();
         int count=Integer.parseInt(g);         
         for(int i=0;i<f;i++)
         {        
        	 if(i==(f-1))
        	 {        		 
        		 String message = inn.readLine();
        		 b=message.getBytes();
        		 System.out.println("Sending the remaining bytes of data ");
        		 if(count<5)
        		 {
        			for(int q=0;q<count;q++)
                	fos.write(b[q]);
        		 }
        		 else
                 	for(int q=5;q<count;q++)
                   {
           			for(int t=0;t<5;t++)
                    fos.write(b[t]);	
                   	fos.write(b[q]);
                   	}
        	 } 	 
         String message = inn.readLine();
         b=message.getBytes();
         System.out.println("Breaking 10 bytes and sending into 5 bytes");
         for(int q=0;q<5;q++)
         {
        	fos.write(b[q]);
         }
         System.out.println("Sending other half 5 bytes of 10 bytes");
           for(int q=5;q<10;q++)
           { 
           	fos.write(b[q]);
           }         
         }     
         fos.close();
     //    fis.close();
      }    
      
      
      else
      {
    	   File file1=new File(destination);
   	   FileOutputStream fos=new FileOutputStream(file1);

   	   File file2=new File(source);
   	   FileInputStream fis=new FileInputStream(file2);



   	   //Accepting the size of the file from the client
   	   int file_size1=Integer.parseInt(inn.readLine());
   	   System.out.println("The file size sent by the client is:");
   	   System.out.println(file_size1);

   	   byte im_array[]=new byte[file_size1];
   	   char im_char_array[]=new char[file_size1];
   	   byte im_array1[]=new byte[file_size1];
   	   char im_char_array1[]=new char[file_size1];
   	   String abc_server_1;

   	   //Accepting the string from the client
   	   String abc_server=inn.readLine();
   	   fis.read(im_array);


   	   for(int yt=0;yt<im_array.length;yt++)
   	   {
   	   im_char_array[yt]=(char)im_array[yt];
   	   }

   	   abc_server_1=new String(im_char_array);



   	   for(int wr=0;wr<im_array1.length;wr++)
   	   {
   	   im_char_array1[wr]=abc_server_1.charAt(wr);
   	   }


   	   for(int wt=0;wt<im_array.length;wt++)
   	   {
   	   im_array1[wt]=(byte)im_char_array1[wt];
   	   }


   	   fos.write(im_array1);



   	   System.out.println("File Writing done successfully in chunks of 5 bytes");
   	   inn.close();
   	   fos.close();




   	   if(fos!=null)
   	   {
   	   fos.close();
   	   }


   	   if(fis!=null)
   	   {
   	   fis.close();
   	   }
         
      }
      }
         
      
      
      catch(Exception e)
      {} 
      
      mysocket.close();
 }
      
}