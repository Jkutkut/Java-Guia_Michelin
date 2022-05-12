package dam.jkutkut.michelin.view.query;

import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.model.Restaurant;
import dam.jkutkut.michelin.view.MichelinMenu;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ViewQuery extends JFrame implements WindowListener, MichelinMenu {
    private static final String TITLE = "";
    private JPanel jpBody;
    private JLabel lblTitle;
    private JPanel jpFilterForm;
    private JLabel lblFilterTitle;
    private JComboBox cmbRegion;
    private JComboBox cmbDistinction;
    private JButton btnSearch;
    private JLabel lblRegion;
    private JLabel lblDistinction;
    private JPanel jpQuery;
    private JLabel lblQueryTitle;
    private JScrollPane jspTable;
    private JTable tableRestaurants;
    private JButton btnDelete;

    public ViewQuery() {
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
        cmbRegion.setModel(new DefaultComboBoxModel(Restaurant.REGIONS));
        cmbDistinction.setModel(new DefaultComboBoxModel(Restaurant.DISTINCTIONS));
        cmbDistinction.setSelectedIndex(Restaurant.DISTINCTIONS.length - 1);
    }

    // SETTERS
    public void setControlador(Controller controlador) {
        btnSearch.addActionListener(controlador);
        btnDelete.addActionListener(controlador);
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
