package dam.jkutkut.michelin.controller;

import dam.jkutkut.exception.InvalidDataException;
import dam.jkutkut.michelin.view.registration.ViewRegistration;
import dam.jkutkut.michelin.view.query.ViewQuery;
import dam.jkutkut.michelin.view.modification.ViewModification;
import dam.jkutkut.michelin.view.window.ViewWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

    private ViewWindow vWindow;
    private ViewRegistration vRegistration;
    private ViewQuery vQuery;
    private ViewModification vModification;

    public Controller(ViewWindow vWindow, ViewRegistration vRegistration, ViewQuery vQuery, ViewModification vModification) {
        this.vWindow = vWindow;
        this.vModification = vModification;
        this.vRegistration = vRegistration;
        this.vQuery = vQuery;

        vWindow.openWindow(vRegistration);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() instanceof JButton) {
//            JButton button = (JButton) e.getSource();
//            if (button == )
//        }
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            if (menuItem == vWindow.getJmiQuery())
                vWindow.openWindow(vQuery);
            else if (menuItem == vWindow.getJmiModification())
                vWindow.openWindow(vModification);
            else if (menuItem == vWindow.getJmiRegistration())
                vWindow.openWindow(vRegistration);
        }
    }

    public void registerRestaurant() {
        try {
            String name = vRegistration.getName();


            vRegistration.clearError();
        }
        catch (InvalidDataException e) {
            vRegistration.setError(e.getMessage());
        }
    }

    public void searchRestaurants() {

    }

    public void consultRestaurants() {

    }
}
