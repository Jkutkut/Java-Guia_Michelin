package dam.jkutkut.michelin.view.registration;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.model.Restaurant;
import dam.jkutkut.michelin.view.MichelinMenu;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ViewRegistration extends JFrame implements WindowListener, MichelinMenu {
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
    private JLabel lblError;

    public ViewRegistration() {
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
        cmbType.setModel(new DefaultComboBoxModel(Restaurant.TYPES));
        cmbRegion.setModel(new DefaultComboBoxModel(Restaurant.REGIONS));

        spDistinction.setModel(new SpinnerNumberModel(
            Restaurant.MIN_DISTINCTION,
            Restaurant.MIN_DISTINCTION,
            Restaurant.MAX_DISTINCTION,
            1
        ));
    }

    // SETTERS
    public void setControlador(Controller controlador) {

    }

    public void setError(String msg) {
        if (msg == null || msg.isEmpty())
            lblError.setText("");
        else
            lblError.setText(msg);
    }

    public void clearError() {
        lblError.setText(null);
    }

    // GETTERS
    public JPanel getMenu() {
        return jpBody;
    }

    public String getName() {
        return txtfName.getText();
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
