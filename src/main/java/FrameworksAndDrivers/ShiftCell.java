package FrameworksAndDrivers;

import InterfaceAdapters.*;
import UseCases.ShiftFileReader;

import javax.swing.*;
import java.awt.*;

public class ShiftCell extends JButton implements GUIElement{
    private int shift;
    private Color colour;
    private ShiftFileReader reader;
    private Page gui;
    private int user;
    private ShiftCellPresenter presenter;

    public ShiftCell(int shift, Page gui, int user){
        super();
        this.shift = shift;
        this.colour = Color.cyan;
        this.gui = gui;
        this.user = user;
        presenter = new ShiftCellPresenter(this, shift, user);
        this.addActionListener(presenter);
    }
    public void paintComponent(Graphics g1) {
        setBackground(colour);
        setOpaque(true);
        Graphics2D g = (Graphics2D) g1.create();
        super.paintComponent(g);
        g.drawString(presenter.getString(), getWidth()/5, getWidth()/5);
        g.dispose();
    }

    public int getShift(){
        return shift;
    }

    @Override
    public void nextPage() {
        new ShiftView(shift, user);
        gui.dispose();
    }

    @Override
    public String getContent() {
        return this.getText();
    }
}
