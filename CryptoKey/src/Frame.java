
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Frame {
    //This object class contains code for the GUI.
    /**
     * This class contains the GUI generation as well as for now
     * the full functionality of the program.
     *
     * <b><CODE>main()</CODE></b> Contains everything from frame and component
     * initialization, also includes the event listener for the button to write
     * to the text file "database"/
     */

    //Globals:
    private static final String FILE_NAME = "database.txt";
    private static File userFile = new File(FILE_NAME);

    //MainLoop:
    public static void main(){
        JFrame frame = new JFrame("CryptoKey V1.0");
        frame.setResizable(false);
        JPanel panel = new JPanel(new BorderLayout());
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        JProgressBar bar = new JProgressBar(0);
        JButton button = new JButton("Submit Password!");
        JButton button2 = new JButton("Retrieve Password!");
        JLabel label1 = new JLabel("Press submit to add key to database!");
        Checkbox one = new Checkbox("one", null, true);
        TextField tf1 = new TextField("", 25);
        JTextArea textArea = new JTextArea("**Stored Keys**\n",10, 20);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setVisible(true);
        bar.setValue(0);
        bar.setStringPainted(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.getContentPane().add(button);
        frame.getContentPane().add(label1);
        frame.getContentPane().add(one);
        frame.getContentPane().add(tf1);
        frame.getContentPane().add(bar);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(scroll);

        //Method with stored action listener for the button:
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Submit button clicked!");
                String inputValue = tf1.getText();
                if (inputValue.isEmpty()){
                    System.out.println("No input..");
                    inputValue = null;
                    bar.setValue(0);
                } else {
                    System.out.println("Added Value is: " + inputValue);
                    Frame.FileManager(inputValue);
                    bar.setValue(100);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Retrieving stored keys..");
                try {
                    File file = new File(FILE_NAME);
                    Scanner fscan =  new Scanner(file);
                    String storedKey;
                    while(fscan.hasNextLine()) {
                        storedKey = fscan.nextLine();
                        textArea.append("Key Retrieved: " + storedKey + "\n");
                    }
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex);
                }
            }
        });

        //Code below here controls JFrame size/Style:
        frame.setSize(350, 350);
        frame.getContentPane().setBackground(Color.lightGray);
        frame.setVisible(true);
    }

    //Other methods (Mostly file read and write control):
    public static void FileManager(String key) {

        try {
            if (userFile.createNewFile()) {
                System.out.println("File created for user!");
            } else {
                System.out.println("File is already in path!");
            }
            FileWriter fw = new FileWriter(FILE_NAME, true);

            fw.write(key + "\n");
            fw.close();
        } catch (Exception ex) {
            System.out.println("Exception has occurred: " + ex);
        }
    }
}
