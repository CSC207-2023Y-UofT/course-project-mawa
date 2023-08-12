package FrameworksAndDrivers;

import InterfaceAdapters.*;

import javax.swing.*;
import java.awt.*;
/**
 * The ShiftCell class represents a GUIElement for displaying a shift.
 * It extends JButton and implements the GUIElement interface.
 */
public class ShiftCell extends JButton implements GUIElement{
    private int shift;
    private Color colour;
    private int user;
    private ShiftCellPresenter presenter;
    private boolean painted;
    /**
     * Constructs a ShiftCell object.
     *
     * @param shift The ID of the shift.
     * @param user The ID of the user associated with the shift.
     */
    public ShiftCell(int shift, int user){
        super();
        this.painted = false;
        this.shift = shift;
        this.colour = new Color((int)(Math.random() * 0x1000000));
        this.user = user;
        presenter = new ShiftCellPresenter(this, shift, user);
        this.addActionListener(presenter);
        System.out.println(shift);
        repaint();
        setText(presenter.getString());
    }
    /**
     * Paints the shift cell with its associated color.
     *
     * @param g1 The Graphics object used for painting.
     */
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        if (!painted) {
            setBackground(colour);
            setOpaque(true);
            g1.dispose();
            painted = true;
        }
    }

    public int getShift(){
        return shift;
    }
    /**
     * Opens a new ShiftView or ShiftViewHRGUI based on the user's role.
     * If the user is HR, opens a ShiftViewHRGUI for editing shift workers.
     * Otherwise, opens a ShiftView for viewing the shift details.
     */
    @Override
    public void nextPage() {
        if (presenter.isHR()){
            new ShiftViewHRGUI(shift);
        }else {
            new ShiftView(shift, user);
        }
    }

    @Override
    public String getContent() {
        return this.getText();
    }
}
