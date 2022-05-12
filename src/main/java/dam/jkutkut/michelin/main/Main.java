package dam.jkutkut.michelin.main;

import dam.jkutkut.michelin.controller.Controller;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                Login vLogin = new Login();

                Controller controller = new Controller();
//                vLogin.setController(controller);
//                vLogin.setVisible(true);
            }
        });
    }
}
