package dam.jkutkut.michelin.view.modification;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.model.Restaurant;
import dam.jkutkut.michelin.view.MichelinMenu;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

public class ViewModification extends JFrame implements MichelinMenu {
    private static final String TITLE = "";
    public static final int SEARCH_MODE = 0;
    public static final int MODIFY_MODE = 1;

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
    private JButton btnSearch;
    private JComboBox cmbType;

    public ViewModification() {
        setTitle(TITLE);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        cmbRegion.setModel(new DefaultComboBoxModel(Arrays.stream(Restaurant.REGIONS).limit(Restaurant.REGIONS.length - 1).toArray()));
        cmbType.setModel(new DefaultComboBoxModel(Restaurant.TYPES));
        cmbRegion.setMaximumRowCount(8);

        spDistinction.setModel(new SpinnerNumberModel(
            Restaurant.MIN_DISTINCTION,
            Restaurant.MIN_DISTINCTION,
            Restaurant.MAX_DISTINCTION,
            1
        ));
        setMode(SEARCH_MODE);
    }

    // SETTERS
    public void setController(Controller controller) {
        btnSubmit.addActionListener(controller);
        btnClear.addActionListener(controller);
        btnSearch.addActionListener(controller);
    }

    public void setMode(int mode) {
        boolean enableForm = mode == MODIFY_MODE;

        txtfName.setEnabled(!enableForm);
        btnSearch.setEnabled(!enableForm);

        cmbRegion.setEnabled(enableForm);
        txtfAddress.setEnabled(enableForm);
        txtfCity.setEnabled(enableForm);
        txtfAddress.setEnabled(enableForm);
        cmbType.setEnabled(enableForm);
        spDistinction.setEnabled(enableForm);
        txtfMinPrice.setEnabled(enableForm);
        txtfMaxPrice.setEnabled(enableForm);
        txtfPhone.setEnabled(enableForm);
        txtfWeb.setEnabled(enableForm);
        btnSubmit.setEnabled(enableForm);
        btnClear.setEnabled(enableForm);
    }

    public void clearForm() {
        txtfName.setText("");
        cmbRegion.setSelectedIndex(0);
        txtfCity.setText("");
        txtfAddress.setText("");
        cmbType.setSelectedIndex(0);
        spDistinction.setValue(Restaurant.MIN_DISTINCTION);
        txtfMinPrice.setText("");
        txtfMaxPrice.setText("");
        txtfPhone.setText("");
        txtfWeb.setText("");
    }

    public void setRestaurant(Restaurant r) {
        txtfName.setText(r.getName());
        cmbRegion.setSelectedItem(r.getRegion());
        txtfCity.setText(r.getCity());
        txtfAddress.setText(r.getAddress());
        cmbType.setSelectedItem(r.getType());
        spDistinction.setValue(r.getDistinction());
        txtfMinPrice.setText(String.valueOf(r.getMinPrice()));
        txtfMaxPrice.setText(String.valueOf(r.getMaxPrice()));
        txtfPhone.setText(r.getPhone());
        txtfWeb.setText(r.getWeb());
    }

    public void setError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void setInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }


    // GETTERS
    public JPanel getMenu() {
        return jpBody;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public String getName() {
        return txtfName.getText();
    }

    public String getRegion() {
        return (String) cmbRegion.getSelectedItem();
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
        return (String) cmbType.getSelectedItem();
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
