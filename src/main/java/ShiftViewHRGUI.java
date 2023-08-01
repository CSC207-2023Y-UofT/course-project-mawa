import javax.swing.*;

public class ShiftViewHRGUI {
    private JFrame frame = new JFrame();

    public void ShiftViewGUI(){
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ShiftViewHRGUI();
    }

}
