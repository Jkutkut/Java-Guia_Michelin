package dam.jkutkut.michelin.view.window;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.view.MichelinMenu;
import dam.jkutkut.michelin.view.query.ViewQuery;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ViewWindow extends JFrame implements WindowListener {
    private static final String TITLE = "";

    private JPanel jpBody;
    private JMenu jmMode;
    private JMenuItem jmiQuery;
    private JMenuItem jmiRegistration;
    private JMenuItem jmiModification;
    private JScrollPane jspMenuContainer;

    public ViewWindow() {
        setTitle(TITLE);
        setContentPane(jpBody);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
         setSize(1500, 800);
        addWindowListener(this);

        initComponents();
    }

    private void initComponents() {

    }

    // SETTERS
    public void setController(Controller controller) {
        jmiModification.addActionListener(controller);
        jmiQuery.addActionListener(controller);
        jmiRegistration.addActionListener(controller);
    }

    public void openWindow(MichelinMenu window) {
        jspMenuContainer.setViewportView(window.getMenu());
    }

    // GETTERS

    public JMenuItem getJmiQuery() {
        return jmiQuery;
    }

    public JMenuItem getJmiModification() {
        return jmiModification;
    }

    public JMenuItem getJmiRegistration() {
        return jmiRegistration;
    }

    // Window listener methods
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
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
