package dam.jkutkut.michelin.controller;

import dam.jkutkut.exception.InvalidDataException;
import dam.jkutkut.michelin.model.Restaurant;
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
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();

            // Registration
            if (button == vRegistration.getBtnClear())
                vRegistration.clearForm();
            else if (button == vRegistration.getBtnSubmit())
                this.registerRestaurant();
        }
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
        Restaurant restaurant = new Restaurant();
        try {
            restaurant.setName(vRegistration.getName());
            restaurant.setRegion(vRegistration.getRegion());
            restaurant.setType(vRegistration.getRestType());
            restaurant.setDistinction(vRegistration.getDistinction());
            restaurant.setMinPrice(vRegistration.getMinPrice());
            restaurant.setMaxPrice(vRegistration.getMaxPrice());
            restaurant.setPhone(vRegistration.getPhone());
            restaurant.setWeb(vRegistration.getWeb());

            restaurant.validate();
            // TODO save to database
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
