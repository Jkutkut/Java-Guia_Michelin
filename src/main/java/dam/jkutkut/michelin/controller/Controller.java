package dam.jkutkut.michelin.controller;

import dam.jkutkut.db.MichelinDB;
import dam.jkutkut.exception.InvalidDataException;
import dam.jkutkut.exception.SQLiteQueryException;
import dam.jkutkut.michelin.model.Restaurant;
import dam.jkutkut.michelin.view.registration.ViewRegistration;
import dam.jkutkut.michelin.view.query.ViewQuery;
import dam.jkutkut.michelin.view.modification.ViewModification;
import dam.jkutkut.michelin.view.window.ViewWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class Controller implements ActionListener {
    private MichelinDB db;
    private ViewWindow vWindow;
    private ViewRegistration vRegistration;
    private ViewQuery vQuery;
    private ViewModification vModification;

    private ArrayList<Restaurant> restaurants;
    private Restaurant modifiedRestaurant;

    public Controller(MichelinDB db, ViewWindow vWindow, ViewRegistration vRegistration, ViewQuery vQuery, ViewModification vModification) {
        this.db = db;
        this.vWindow = vWindow;
        this.vModification = vModification;
        this.vRegistration = vRegistration;
        this.vQuery = vQuery;

        restaurants = null;
        modifiedRestaurant = null;
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

            // Query
            else if (button == vQuery.getBtnSearch()) {
                updateTable();
            }
            else if (button == vQuery.getBtnDelete()) {
                deleteRestaurant();
            }

            // Modification
            else if (button == vModification.getBtnSearch()) {
                searchRestaurants();
            }
            else if (button == vModification.getBtnSubmit()) {
                updateRestaurant();
            }
            else if (button == vModification.getBtnClear()) {
                vModification.clearForm();
                modifiedRestaurant = null;
                vModification.setMode(vModification.SEARCH_MODE);
            }
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

    // *********** Registration ***********
    public void registerRestaurant() {
        Restaurant restaurant = new Restaurant();
        try {
            restaurant.setName(vRegistration.getName());
            restaurant.setRegion(vRegistration.getRegion());
            restaurant.setCity(vRegistration.getCity());
            restaurant.setDistinction(vRegistration.getDistinction());
            restaurant.setAddress(vRegistration.getAddress());
            restaurant.setMinPrice(vRegistration.getMinPrice());
            restaurant.setMaxPrice(vRegistration.getMaxPrice());
            restaurant.setType(vRegistration.getRestType());
            restaurant.setPhone(vRegistration.getPhone());
            restaurant.setWeb(vRegistration.getWeb());

            restaurant.validate();

            db.addRestaurant(restaurant);
        }
        catch (InvalidDataException e) {
            vRegistration.setError(e.getMessage());
        }
    }

    // *********** Query ***********
    private void updateTable() {
        try {
            restaurants = db.getRestaurants(vQuery.getFilterConditions());
            vQuery.updateTable(restaurants);
        }
        catch (Exception error) {
            vQuery.setError(error.getMessage());
            System.out.println(error.getMessage());
            System.out.println(error.getStackTrace());
        }
    }

    private void deleteRestaurant() {
        if (vQuery.restaurantSelected() == -1) {
            vQuery.setError("No restaurant selected");
            return;
        }
        try {
            db.removeRestaurant(restaurants.get(vQuery.restaurantSelected()));
            updateTable();
        }
        catch (Exception error) {
            vQuery.setError(error.getMessage());
            System.out.println(error.getMessage());
            System.out.println(error.getStackTrace());
        }
    }

    // *********** Modification ***********

    public void searchRestaurants() {
        if (vModification.getName().isEmpty()) {
            vModification.setError("Name field is empty");
            return;
        }
        try {
            modifiedRestaurant = db.getRestaurant(vModification.getName());
            if (modifiedRestaurant == null) {
                throw new InvalidDataException("Restaurant not found");
            }
            vModification.setRestaurant(modifiedRestaurant);
            vModification.setMode(vModification.MODIFY_MODE);
        }
        catch (SQLiteQueryException e1) {
            vModification.setError(e1.getMessage());
        }
        catch (InvalidDataException e2) {
            vModification.setInfo(e2.getMessage());
        }
    }

    public void updateRestaurant() {
        Restaurant restaurant = new Restaurant();
        try {
            restaurant.setName(vModification.getName());
            restaurant.setRegion(vModification.getRegion());
            restaurant.setCity(vModification.getCity());
            restaurant.setDistinction(vModification.getDistinction());
            restaurant.setAddress(vModification.getAddress());
            restaurant.setMinPrice(vModification.getMinPrice());
            restaurant.setMaxPrice(vModification.getMaxPrice());
            restaurant.setType(vModification.getRestType());
            restaurant.setPhone(vModification.getPhone());
            restaurant.setWeb(vModification.getWeb());

            restaurant.validate();

            db.updateRestaurant(modifiedRestaurant, restaurant); // TODO
            vModification.setInfo("Restaurant updated");
        }
        catch (InvalidDataException e) {
            vModification.setError(e.getMessage());
        }
    }
}
