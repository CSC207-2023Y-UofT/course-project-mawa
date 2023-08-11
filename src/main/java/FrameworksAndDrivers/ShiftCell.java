package FrameworksAndDrivers;

import InterfaceAdapters.*;
import UseCases.ShiftFileReader;

import javax.swing.*;
import java.awt.*;

public class ShiftCell extends JButton implements GUIElement{
    private int shift;
    private Color colour;
    private Page gui;
    private int user;
    private ShiftCellPresenter presenter;
    private float width;
    private boolean painted;

    public ShiftCell(int shift, Page gui, int user, float width){
        super();
        this.painted = false;
        this.shift = shift;
        this.width = width;
        this.colour = new Color((int)(Math.random() * 0x1000000));
        this.gui = gui;
        this.user = user;
        presenter = new ShiftCellPresenter(this, shift, user);
        this.addActionListener(presenter);
        System.out.println(shift);
        repaint();
    }
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        if (!painted) {
            setBackground(colour);
            setOpaque(true);
            g1.drawString(presenter.getString(), (int) (width / 5), (int) (width / 5));
            g1.dispose();
            painted = true;
        }
    }

    public int getShift(){
        return shift;
    }

    @Override
    public void nextPage() {
        if (presenter.isHR()){
            new ShiftViewHRGUI(shift);
        }else {
            new ShiftView(shift, user);
        }
    }

    /*@Override
    public void nextPage() {
        new ShiftView(shift, user);
    }*/

    @Override
    public String getContent() {
        return this.getText();
    }
}
