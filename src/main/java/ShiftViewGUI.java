import javax.swing.*;

public class ShiftViewGUI {
    private JFrame frame = new JFrame();

    public ShiftViewGUI(){
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ShiftViewGUI();
    }
}
