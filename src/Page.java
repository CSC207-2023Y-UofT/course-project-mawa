import javax.swing.*;

public interface Page {

    JButton back = new JButton("Back");
    JPanel backPanel = new JPanel();
    public void addTitle();
    public void addContent();
    //public void addFwdBackRefreshButton();
}
