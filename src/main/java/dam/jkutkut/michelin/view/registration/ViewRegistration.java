package dam.jkutkut.michelin.view.registration;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.model.Restaurant;
import dam.jkutkut.michelin.view.MichelinMenu;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

public class ViewRegistration extends JFrame implements MichelinMenu {
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
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

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
            return Restaurant.NULL_PRICE;
        try {
            return Double.parseDouble(txtfMinPrice.getText());
        }
        catch (NumberFormatException e) {
            return Restaurant.NULL_PRICE;
        }
    }

    public double getMaxPrice() {
        if (txtfMaxPrice.getText().isEmpty())
            return Restaurant.NULL_PRICE;
        try {
            return Double.parseDouble(txtfMaxPrice.getText());
        }
        catch (NumberFormatException e) {
            return Restaurant.NULL_PRICE;
        }
    }

    public String getRestType() {
        return cmbType.getSelectedItem().toString();
    }

    public String getPhone() {
        if (txtfPhone.getText().isEmpty())
            return null;
        return txtfPhone.getText();
    }

    public String getWeb() {
        if (txtfWeb.getText().isEmpty())
            return null;
        return txtfWeb.getText();
    }
}
