package dam.jkutkut.michelin.view.modification;

import dam.jkutkut.michelin.controller.Controller;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ViewModification extends JFrame implements WindowListener {
    private static final String TITLE = "";
    private JPanel jpBody;
    private JLabel lblTitle;
    private JPanel jpForm;
    private JPanel jpBtns;
    private JButton btnSubmit;
    private JButton btnClear;
    private JTextField txtfName;
    private JComboBox cmbRegion;
    private JTextField txtfCity;
    private JTextField txtfAddress;
    private JSpinner spDistinction;
    private JTextField txtfMinPrice;
    private JTextField txtfMaxPrice;
    private JTextField txtfPhone;
    private JTextField txtfWeb;
    private JLabel lblName;
    private JLabel lblType;
    private JLabel lblRegion;
    private JLabel lblCity;
    private JLabel lblAddress;
    private JLabel lblDistinction;
    private JLabel lblMinPrice;
    private JLabel lblMaxPrice;
    private JLabel lblPhone;
    private JLabel lblWeb;
    private JComboBox cmbType;

    public ViewModification() {
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
    public JPanel getMenu() {
        return jpBody;
    }

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
