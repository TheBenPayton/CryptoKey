
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.awt.*;
import java.io.*;

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

    public static void main(){
        JFrame frame = new JFrame("CryptoKey V1.0");
        JPanel panel = new JPanel(new BorderLayout());
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        JButton button = new JButton("Submit Password!");
        JLabel label1 = new JLabel("Press submit to add key to database!");
        Checkbox one = new Checkbox("one", null, true);
        TextField tf1 = new TextField("", 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.getContentPane().add(button);
        frame.getContentPane().add(label1);
        frame.getContentPane().add(one);
        frame.getContentPane().add(tf1);

        //Method with stored action listener for the button:
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Submit button clicked!");
                String inputValue = tf1.getText();
                System.out.println("Value is: " + inputValue);
                Frame.FileManager(inputValue);
            }
        });

        //Code below here controls JFrame size/Style:
        frame.setSize(350, 350);
        frame.getContentPane().setBackground(Color.cyan);
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
            FileReader fr = new FileReader(FILE_NAME);
            FileWriter fw = new FileWriter(FILE_NAME, true);

            fw.write(key + "\n");
            fw.close();
            fr.close();

        } catch (Exception ex) {
            System.out.println("Exception has occurred: " + ex);
        }
    }
}
