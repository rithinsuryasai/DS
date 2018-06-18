import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

//CHECKS THE CLIENT COUNT AND ALSO THE CONNECTION*
public class CLIENTConnection extends Thread{     
	private Socket clientSocket;
	private DataInputStream in = null;
	private PrintStream os;
	private final CLIENTConnection[] threads;
	
	private int maxClientsCount;
	public CLIENTConnection(Socket client, CLIENTConnection[] threads) {
		this.clientSocket = client;
		this.threads = threads;
		maxClientsCount = threads.length;
	}

	@Override
	public void run() {
		try {
			int maxClientsCount = this.maxClientsCount;
			String clientSelection;
			String searchWord;
			String result = null;
			String name;
			
 
			in = new DataInputStream(clientSocket.getInputStream());;
			os = new PrintStream(clientSocket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(       /*MySQL connection */
			"jdbc:mysql://localhost:3306/thesaurus","root","");
			name = "";
			while(true){
				String line = br.readLine();
				
				System.out.println(line);                     
				String command[] = line.split(" ");     /* SPLITTING THE INPUT "SEARCH INPUT" TO "INPUT" FOR DATABASE LOOKUP*/
				if (line.startsWith("/quit")) {
					break;
				}

				if(command[0].equals("Search")) {
					searchWord = command[1];             /* SERCHING FOR SYNONYM AFTER SPLITTING*/
					
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select synonym from synonyms where word = '"+searchWord+"'");  /* SQL statement for server lookup*/
					while(rs.next())  
					result = rs.getString(1);
					System.out.println(result);      /*PRINTING IT IN CONSOLE*/
					if(result.equals("null")) {
						result=result.replace("null", "not there");	
					}
				os.println(result);              /* SENDING IT TO PRINT IN CLIENT GUI TEXTFIELD*/
				
				ServerDemo.display(result);
				result="null";
				}
			}
			con.close();
			in.close();
			os.close();
			clientSocket.close();

		} catch (IOException | ClassNotFoundException | SQLException ex) {
			Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}