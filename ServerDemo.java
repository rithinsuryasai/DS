import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

/* THIS IS THE CLIENT CLASS COMMENCEMENT WITH GUI*/
public class ServerDemo extends JFrame
{
    private static JTextArea tarea =  new JTextArea(10, 10);  /* TEXT AREA WHERE THE BLOCK OF TEXT IS PRESENT*/
    private static JTextField tfield = new JTextField(10);  /* TEXT FIELD WHERE THE RESULT IS DISPLAYED*/


    private void createAndDisplayGUI()     /* THE GUI CLASS*/
    {
        new Thread(new MultiThreadChatClient()).start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tarea.setText("Hello there\n");

        JScrollPane scroll = new JScrollPane(tarea);

        tfield.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tarea.append(tfield.getText() + "\n");
            }
        });
        
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(tfield, BorderLayout.PAGE_END);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String... args)   /* main PROGRAM*/
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ServerDemo().createAndDisplayGUI();
            }
        });
    }

       public static void display(String result) {     /* TO DISPLAY THE RESULT WHICH IS FROM THE SERVER*/
    		System.out.println("Here");
			tfield.setText(result);
			tarea.append(result);
			tarea.setText(result);
			}

}