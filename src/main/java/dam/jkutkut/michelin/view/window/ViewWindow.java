package dam.jkutkut.michelin.view.window;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.view.modification.ViewModification;
import dam.jkutkut.michelin.view.query.ViewQuery;
import dam.jkutkut.michelin.view.registration.ViewRegistration;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ViewWindow extends JFrame implements WindowListener {
    private static final String TITLE = "";

    public ViewWindow() {
        setTitle(TITLE);
        // setContentPane(jpBody);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        // setSize(500, 200);
        addWindowListener(this);

        initComponents();
    }

    private void initComponents() {

    }

    // SETTERS
    public void setControlador(Controller controlador) {

    }

    // GETTERS

    // Window listener methods
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
