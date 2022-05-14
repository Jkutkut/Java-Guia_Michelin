package dam.jkutkut.michelin.main;

import com.formdev.flatlaf.FlatDarculaLaf;
import dam.jkutkut.db.MichelinDB;
import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.view.registration.ViewRegistration;
import dam.jkutkut.michelin.view.query.ViewQuery;
import dam.jkutkut.michelin.view.modification.ViewModification;
import dam.jkutkut.michelin.view.window.ViewWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ViewWindow viewWindow = new ViewWindow();
                ViewRegistration viewRegistration = new ViewRegistration();
                ViewModification viewModification = new ViewModification();
                ViewQuery viewQuery = new ViewQuery();

                MichelinDB db = new MichelinDB();


                Controller controller = new Controller(
                    db,
                    viewWindow,
                    viewRegistration,
                    viewQuery,
                    viewModification
                );
                viewWindow.setController(controller);
                viewModification.setController(controller);
                viewRegistration.setController(controller);
//                viewQuery.setController(controller);
                viewWindow.setVisible(true);
            }
        });
    }
}
