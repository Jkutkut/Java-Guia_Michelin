package dam.jkutkut.michelin.controller;

import dam.jkutkut.michelin.view.modification.ViewModification;
import dam.jkutkut.michelin.view.query.ViewQuery;
import dam.jkutkut.michelin.view.registration.ViewRegistration;
import dam.jkutkut.michelin.view.window.ViewWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

    private ViewWindow vWindow;
    private ViewModification vModification;
    private ViewQuery vQuery;
    private ViewRegistration vRegistration;

    public Controller(ViewWindow vWindow, ViewModification vModification, ViewQuery vQuery, ViewRegistration vRegistration) {
        this.vWindow = vWindow;
        this.vModification = vModification;
        this.vRegistration = vRegistration;
        this.vQuery = vQuery;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() instanceof JButton) {
//            JButton button = (JButton) e.getSource();
//            if (button == )
//        }
    }

}
