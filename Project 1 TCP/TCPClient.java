

import java.io.*;
import java.net.*;
import java.util.*;
public class TCPClient
{
public static void main(String[] args) throws IOException 
{     
	  
	   Scanner s=new Scanner(System.in);
	   
	   System.out.println("Enter 1 for text files and enter 2 for image(binary files):");
		int choice_client=s.nextInt();
        
		if(choice_client==1)
		{
		System.out.println("Enter the port number to connect with the Server.");
		System.out.println("Note that the port number should be the same as made available by the server.");
		int port_number=s.nextInt();
        



		System.out.println("Enter the IP address of the server: ");
		String ip_address=s.next();



		System.out.println("Operating with the text files");
		System.out.println("Enter the complete path of the input file: ");
		System.out.println("The input file is the one which has data.");
		String source=s.next();




		System.out.println("Enter the complete path of the output file: ");
		System.out.println("The output file is empty in the beginning.");
		String destination=s.next();
	    
		 Socket socketClient= new Socket(ip_address,port_number);
		   System.out.println("Client: "+"Connection Established");
		   System.out.println("Hello World");
      int i;
      char[] c=new char[10];
     
      try
      {
    	  PrintWriter outt =new PrintWriter(socketClient.getOutputStream(), true);  
    	  FileInputStream fis = new FileInputStream(source);  
    	  
    	  outt.println(choice_client);
    	  outt.println(source);
    	  outt.println(destination);
         byte[] bs = new byte[fis.available()];
         i=fis.read(bs);       
         System.out.println("Number of bytes read: "+i);
         outt.println(i);
         int t=i/10;
         if((i%10)!=0)
         t++;
         outt.println(t);
         outt.println(i%10);        
         System.out.println("Number of times 10 bytes of data would be sent is :"+t);
         System.out.println("Bytes read: ");
         int temp;
         for(int p=0;p<t;p++)
         {		
		        if(p==(t-1))
		        temp=i%10;
		        else
		        temp=10;
		        int h=p*10;
		        for(int k=0;k<temp;k++)
				         {
				            c[k]=(char)(bs[h+k]);				
				            System.out.print(c[k]);
		                }  
		         String d=new String(c);
		         outt.println(d);
         }
         fis.close();
      }
      catch(Exception ex)
      {}
        socketClient.close();
		}
		
		else
		{
			
			System.out.println("Enter the port number to connect with the Server.");
			System.out.println("Note that the port number should be the same as made available by the server.");
			int port_number=s.nextInt();
	        



			System.out.println("Enter the IP address of the server: ");
			String ip_address=s.next();



			System.out.println("Operating with the image files");
			System.out.println("Enter the complete path of the input file: ");
			System.out.println("The input file is the one which has image.");
			String source=s.next();




			System.out.println("Enter the complete path of the output file: ");
			System.out.println("The output image file is empty in the beginning.");
			String destination=s.next();
			
			   Socket socketClient= new Socket(ip_address,port_number);
			   System.out.println("Client: "+"Connection Established");
			   System.out.println("Hello World");
			
			   
			   File file=new File(source);
				FileInputStream fis=new FileInputStream(source);




				//Creating objects to send and receive data
				BufferedReader inn=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				PrintStream outt=new PrintStream(socketClient.getOutputStream());
				//ObjectOutputStream oos=new ObjectOutputStream(sock.getOutputStream());



				//Sending the choice to the server
				outt.println(choice_client);


				//Sending the input file name to the server
				outt.println(source);



				//Sending the output file name to the server
				outt.println(destination);




				//Total File Size
				int file_size=fis.available();
				System.out.println("The total file size is "+file_size+" bytes");




				//Sending the file size to the server
				outt.println(file_size);

				byte im_array[]=new byte[file_size];
				char im_char_array[]=new char[file_size];
				String abc_client;


				int nobr=fis.read(im_array);
				System.out.println("The number of bytes being read are:");
				System.out.println(nobr);

				for(int we=0;we<im_array.length;we++)
				{
				im_char_array[we]=(char)im_array[we];
				}
				 

				abc_client=new String(im_char_array);
				System.out.println("The size of the string is:");
				System.out.println(abc_client.length());



				//Passing the string to the server
				outt.println(abc_client);



				System.out.println("End of file reached");
				System.out.println("File sending successful");
				System.out.println("Closing the client");


				inn.close();
				outt.close();


				if(fis!=null)
				{
				fis.close();
				}

		
			socketClient.close();
			
		}
		
   }
}