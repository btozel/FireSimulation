import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class FireSimulationMain {

    public final int MINIMUM_SIZE = 25;

    public static void main(String[] args) {

        FireSimulationMain myMain = new FireSimulationMain();

        myMain.welcomeWindow();

    }



    public void welcomeWindow(){

        // Creates welcome window to get height and width from the user for the simulation
        JFrame frame = new JFrame();
        // set the close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the layout of the frame: FlowLayout
        frame.setLayout(new GridLayout(1,1));
        // set the size of the frame: 300 x 150
        frame.setSize(300,200);
        // make the frame not resizable
        frame.setResizable(false);


        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(4,1));


        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JLabel widthLabel = new JLabel("Width: ", null, SwingConstants.RIGHT);
        JTextField widthTextField = new JTextField(10);
        topPanel.add(widthLabel);
        topPanel.add(widthTextField);
        innerPanel.add(topPanel);

        JPanel mid1Panel = new JPanel(new GridLayout(1,2));
        JLabel heightLabel = new JLabel("Height: ",null, SwingConstants.RIGHT);
        JTextField heightTextField = new JTextField(10);
        mid1Panel.add(heightLabel);
        mid1Panel.add(heightTextField);
        innerPanel.add(mid1Panel);

        JPanel mid2Panel = new JPanel(new GridLayout(1,2));
        JLabel time = new JLabel("Update Time: ",null, SwingConstants.RIGHT);
        JTextField timeTextField = new JTextField(10);
        mid2Panel.add(time);
        mid2Panel.add(timeTextField);
        innerPanel.add(mid2Panel);




        JPanel bottomPanel = new JPanel(new GridLayout(1,2));
        JButton startButton = new JButton("Start");
        startButton.setSize(100, 50);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get user inputs
                String width = widthTextField.getText();
                String height = heightTextField.getText();
                String updateTime = timeTextField.getText();

                try{
                    int x = Integer.parseInt(width);
                    int y = Integer.parseInt(height);
                    int time = Integer.parseInt(updateTime);

                    frame.dispose();
                    GUI simulation = new GUI(x,y);
                    simulation.simulationTimer(time);

                } catch (NumberFormatException invalidInput){
                    JOptionPane.showMessageDialog(frame, "Please Enter Valid Dimensions and Time.",
                            "Input Error!", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setSize(100, 50);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        bottomPanel.add(startButton);
        bottomPanel.add(cancelButton);
        innerPanel.add(bottomPanel);


        frame.add(innerPanel);

        // make the frame visible
        frame.setVisible(true);
        // center the frame
        frame.setLocationRelativeTo(null);



    }
}
