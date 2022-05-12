package dam.jkutkut.michelin.main;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.view.modification.ViewModification;
import dam.jkutkut.michelin.view.query.ViewQuery;
import dam.jkutkut.michelin.view.registration.ViewRegistration;
import dam.jkutkut.michelin.view.window.ViewWindow;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ViewWindow viewWindow = new ViewWindow();
                ViewModification viewModification = new ViewModification();
                ViewRegistration viewRegistration = new ViewRegistration();
                ViewQuery viewQuery = new ViewQuery();


                Controller controller = new Controller(
                    viewWindow,
                    viewModification,
                    viewQuery,
                    viewRegistration
                );
//                viewWindow.setController(controller);
//                viewModification.setController(controller);
//                viewRegistration.setController(controller);
//                viewQuery.setController(controller);
                viewWindow.setVisible(true);
            }
        });
    }
}
