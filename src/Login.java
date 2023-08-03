import javax.swing.*;

public class Login{
    JLabel labelEmpNum = new JLabel("Enter tutorial.Employee Number: ");
    JLabel labelPwd = new JLabel("Enter Password: ");
    JTextField empNumIn = new JTextField(7);
    JPasswordField pwdIn = new JPasswordField(7);
    final Object[] newOptions = {"Enter Credentials", labelEmpNum, empNumIn,
            labelPwd, pwdIn};
    String empNum;
    char[] pwd;

    public Login(){
        JOptionPane pane = new JOptionPane(newOptions, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(null, "Login");

        dialog.setResizable(true);
        dialog.setVisible(true);
        empNum = empNumIn.getText();
        pwd = pwdIn.getPassword();

    }
    public static void main(String[] args){
        new Login();



    }


}
