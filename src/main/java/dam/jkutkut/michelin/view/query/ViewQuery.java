package dam.jkutkut.michelin.view.query;

import dam.jkutkut.db.MichelinDB;
import dam.jkutkut.michelin.controller.Controller;
import dam.jkutkut.michelin.model.Restaurant;
import dam.jkutkut.michelin.view.MichelinMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class ViewQuery extends JFrame implements WindowListener, MichelinMenu {
    private static final String TITLE = "";
    private DefaultTableModel dtm;

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
        setContentPane(jpBody);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 200);
        addWindowListener(this);

        initComponents();
    }

    private void initComponents() {
        cmbRegion.setModel(new DefaultComboBoxModel(Restaurant.REGIONS));
        cmbRegion.setSelectedIndex(Restaurant.REGIONS.length - 1);
        cmbDistinction.setModel(new DefaultComboBoxModel(Restaurant.DISTINCTIONS));
        cmbDistinction.setSelectedIndex(Restaurant.DISTINCTIONS.length - 1);

        setupTable();
    }

    private void setupTable() {
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRestaurants.setModel(dtm);

        for (int i = 0; i < MichelinDB.TABLE_ATRIBUTES.length; i++)
            dtm.addColumn(MichelinDB.TABLE_ATRIBUTES[i]);
        tableRestaurants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // SETTERS
    public void setController(Controller controller) {
        btnSearch.addActionListener(controller);
        btnDelete.addActionListener(controller);
    }

    public void updateTable(ArrayList<Restaurant> restaurants) {
        dtm.setRowCount(0); // clear table
        for (Restaurant restaurant : restaurants) {
            if (wantedRegion(restaurant.getRegion()) && wantedDistinction(restaurant.getDistinction()))
                dtm.addRow(restaurant.toArray());
        }
        if (dtm.getRowCount() == 0)
            JOptionPane.showMessageDialog(this, "No results found", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private boolean wantedDistinction(int distinction) {
        if (cmbDistinction.getSelectedIndex() == Restaurant.DISTINCTIONS.length - 1)
            return true;
        return distinction == cmbDistinction.getSelectedIndex() + 1;
    }

    private boolean wantedRegion(String region) {
        if (cmbRegion.getSelectedIndex() == Restaurant.REGIONS.length - 1)
            return true;
        return region.equals(cmbRegion.getSelectedItem());
    }

    public void setError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // GETTERS
    public JPanel getMenu() {
        return jpBody;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public int restaurantSelected() {
        return tableRestaurants.getSelectedRow();
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
