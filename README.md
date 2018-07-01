Thesaurus-Client-Server-Using-Sockets
---


Description
---

A client/server system, the client process will have a simple GUI interface and will allow the user to select a word in a block of text and have the system send a query to a server to look up the word in a thesaurus file and return a list of alternative words from the server. 

The client process will connect to the server and send the word that the user has selected in an input text box. The server will return a string that will contain alternative words that the user might use instead of the input word. These alternative words will be separated by commas. The string returned for the word “abandon” might look like this: “drops, dumps, ditches, discards; deserts, leaves; ends, gives up; looses, empties, vacates, resigns, quits”. 

Once the client has returned a result, it will allow the user to input another word to reference against the thesaurus. Your server will have a file or database that it will read to find the string of words related to the input word. It will return the associated string to the client process. It will return the string to the client and keep the connection open. The client and server will each have an Exit option that will close the program. The server process should also have a GUI interface. It includes a text box which should display incoming client requests so that the we can see that the processes are communicating. The program can run two clients and a single server without any problems, but the server is not a concurrent server.                                            


Contents
---
This package consists of 6 programs. 
* Fileserver.java 
* TextDemo.java 
* MultithreadedchatClient.java
* ClientConnection.java
* ServerDemo.java
* Thesarus.sql(it is the database should be put in the localhost by using xampp) 

Instructions to compile and run the code
---
•	First, we need to run the Fileserver.java and then Textdemo.java. The server starts when we run the fileserver.java and the client process with the GUI runs with the TextDemo.java.

•	The GUI shows a block of text in the textarea and will result the synonym of selected word in the textfield. If a word selected does not exist in the database then it returns “not there”.

•	To see all the client requests run serverdemo.java. 

•	The server is multithreaded and can be run on multiple clients by creating another instance of client.

Test Data::
---
Make- create, originate, invent, beget, form

Book- dairy, account

Use-employ, utilize, exhaust, spend

Do-execute, accomplish, achieve, attain

Show- isplay, exhibit, present

Mark-label, tag, price, ticket

Begin- start, open, launch


Limitations:
---
Maximum number of clients is 10.

References:
---
* https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
* https://stackoverflow.com/questions/10462725/actionlistener-for-a-specific-text-inside-a-jtextarea 


