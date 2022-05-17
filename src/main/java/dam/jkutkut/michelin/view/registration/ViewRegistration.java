package dam.jkutkut.michelin.view.registration;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.model.Restaurant;
import dam.jkutkut.michelin.view.MichelinMenu;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

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
        cmbRegion.setModel(new DefaultComboBoxModel(Arrays.stream(Restaurant.REGIONS).limit(Restaurant.REGIONS.length - 1).toArray()));
        cmbRegion.setMaximumRowCount(8);

        spDistinction.setModel(new SpinnerNumberModel(
            Restaurant.MIN_DISTINCTION,
            Restaurant.MIN_DISTINCTION,
            Restaurant.MAX_DISTINCTION,
            1
        ));
    }

    // SETTERS
    public void setController(Controller controller) {
        btnSubmit.addActionListener(controller);
        btnClear.addActionListener(controller);
    }

    public void setError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void clearForm() {
        txtfName.setText("");
        cmbType.setSelectedIndex(0);
        cmbRegion.setSelectedIndex(0);
        txtfCity.setText("");
        txtfAddress.setText("");
        spDistinction.setValue(Restaurant.MIN_DISTINCTION);
        txtfMinPrice.setText("");
        txtfMaxPrice.setText("");
        txtfPhone.setText("");
        txtfWeb.setText("");
    }

    // GETTERS
    public JPanel getMenu() {
        return jpBody;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    // Form data
    public String getName() {
        return txtfName.getText();
    }

    public String getRegion() {
        return cmbRegion.getSelectedItem().toString();
    }

    public String getCity() {
        return txtfCity.getText();
    }

    public int getDistinction() {
        return (int) spDistinction.getValue();
    }

    public String getAddress() {
        return txtfAddress.getText();
    }

    public double getMinPrice() {
        if (txtfMinPrice.getText().isEmpty())
            return -1;
        try {
            return Double.parseDouble(txtfMinPrice.getText());
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    public double getMaxPrice() {
        if (txtfMaxPrice.getText().isEmpty())
            return -1;
        try {
            return Double.parseDouble(txtfMaxPrice.getText());
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getRestType() {
        return cmbType.getSelectedItem().toString();
    }

    public String getPhone() {
        return txtfPhone.getText();
    }

    public String getWeb() {
        return txtfWeb.getText();
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
