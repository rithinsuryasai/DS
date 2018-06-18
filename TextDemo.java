import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

/* THIS IS THE CLIENT CLASS COMMENCEMENT WITH GUI*/

public class TextDemo extends JFrame
{
    private JTextArea tarea =  new JTextArea(10, 10);  /* TEXT AREA WHERE THE BLOCK OF TEXT IS PRESENT*/
    private static JTextField tfield = new JTextField(10);  /* TEXT FIELD WHERE THE RESULT IS DISPLAYED*/
    public MultiThreadChatClient chatClient = new MultiThreadChatClient();


    private void createAndDisplayGUI()     /* THE GUI CLASS*/
    {
        new Thread(new MultiThreadChatClient()).start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tarea.setText("Hello there\n");
        tarea.append("Hello student://");  /* THE BLOCK OF TEXT THAT IS DISPLAYED ON THE CLIENT GUI*/
        tarea.append("Do Use Mark book");
        JScrollPane scroll = new JScrollPane(tarea);

        tfield.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tarea.append(tfield.getText() + "\n");
            }
        });

        tarea.addMouseListener(new MouseAdapter()    /* FUNCTION WHILE MOUSE IS CLICKED FOR PROVIDING THE INPUT*/
        {
            public void mouseClicked(MouseEvent me)
            {
                int x = me.getX();
                int y = me.getY();
                System.out.println("X : " + x);
                System.out.println("Y : " + y);
                
                int offset = tarea.viewToModel( me.getPoint() );
                int start;
				try {
					start = Utilities.getWordStart(tarea, offset);
					int end = Utilities.getWordEnd(tarea, offset);
	                String text = tarea.getText(start, end - start);
	                System.out.println(text);
	                chatClient.findWord(text);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
                new TextDemo().createAndDisplayGUI();
            }
        });
    }

       public static void display(String result) {     /* TO DISPLAY THE RESULT WHICH IS FROM THE SERVER*/
    		
			tfield.setText(result);
    	}

}