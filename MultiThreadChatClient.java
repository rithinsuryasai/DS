import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiThreadChatClient implements Runnable {

  // The client socket
  private static Socket clientSocket = null;
  // The output stream
  private static PrintStream os = null;
  // The input stream
  private static DataInputStream is = null;

  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  int portNumber = 4457;
  // The default host.
  String host = "localhost";
  
 // public static ServerDemo sdm = new ServerDemo();

  public MultiThreadChatClient() {     /* CHECKS THE HOST AND THE PORTNUMBER ASSIGNED*/
	  
	    try {
	        clientSocket = new Socket(host, portNumber);
	        inputLine = new BufferedReader(new InputStreamReader(System.in));
	        os = new PrintStream(clientSocket.getOutputStream());
	        is = new DataInputStream(clientSocket.getInputStream());
	      } catch (UnknownHostException e) {
	        System.err.println("Don't know about host " + host);
	      } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to the host "
	            + host);
	      }
  } 
 public void findWord(String word) {      /* the word clicked will go to database from here*/
	    if (clientSocket != null && os != null && is != null) {
	        /* Create a thread to read from the server. */
	          //new Thread(new MultiThreadChatClient()).start();
	          if (!closed) {
	            os.println("Search "+word);    /* sending the word to lookup in the database*/
	            ServerDemo.display("Search "+word);
	          }
	          
	      }
 }
  public void run() {   /* the run method gives the synonym of the word and sends it to display in the textfield*/
    String responseLine;
    try {
      while ((responseLine = is.readLine()) != null) {
        System.out.println(responseLine);
		TextDemo.display(responseLine);    /* sending the synonym result to the textfield in TextDemo class*/
        if (responseLine.indexOf("*** Bye") != -1)   /* to teriminate the program*/
          break;
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
  }
}