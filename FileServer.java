import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {        /* SERVER CONNECTION WITH PORT NUMBER:4457 */

 private static ServerSocket serverSocket;
 private static Socket clientSocket = null;
 private static final int maxClientsCount = 10;   /* limitation that number of clients=10 (can be modified)*/
 private static final CLIENTConnection[] threads = new CLIENTConnection[maxClientsCount];
 //public static ServerDemo sd = new ServerDemo();
 
      public static void main(String[] args) throws IOException {  /* THIS IS THE MAIN FUNCTION TO START THE SERVER OR REPORTS IF THERE IS ANY ISSUE*/

try {
    serverSocket = new ServerSocket(4457);
    System.out.println("Server started.");
    ServerDemo.display("server started");
} catch (Exception e) {
    System.err.println("Port already in use.");
    System.exit(1);
}

while (true) {    /* accepting the socket connection*/
    try {
        clientSocket = serverSocket.accept();
        System.out.println("Accepted connection : " + clientSocket);
       ServerDemo.display("Accepted connection : " + clientSocket);

        int i = 0;
        for (i = 0; i < maxClientsCount; i++) {
          if (threads[i] == null) {
            (threads[i] = new CLIENTConnection(clientSocket, threads)).start();
            break;
          }
        }
        if (i == maxClientsCount) {
            PrintStream os = new PrintStream(clientSocket.getOutputStream());
            os.println("Server too busy. Try later.");
            os.close();
            clientSocket.close();
          }

    } catch (Exception e) {
        System.err.println("Error in connection attempt.");
     }
        }
         }
         }