package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import InterfaceAdapters.*;

public class ShiftCell extends JButton {
    private Shift shift;
    private Color colour;

    public ShiftCell(Shift shift){
        this.shift = shift;
        this.colour = Color.cyan;
    }
    public void paintComponent(Graphics g1) {
        setBackground(colour);
        setOpaque(true);
        Graphics2D g = (Graphics2D) g1.create();
        super.paintComponent(g);
        g.drawString(shift.getTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                getWidth()/5, getWidth()/5);
        g.dispose();
    }

    public Shift getShift(){
        return shift;
    }
}
