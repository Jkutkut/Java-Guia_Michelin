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

public class ViewQuery extends JFrame implements MichelinMenu {
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

        hideTable();
    }

    // SETTERS
    public void setController(Controller controller) {
        btnSearch.addActionListener(controller);
        btnDelete.addActionListener(controller);
    }

    public void updateTable(ArrayList<Restaurant> restaurants) {
        showTable();
        dtm.setRowCount(0); // clear table
        for (Restaurant restaurant : restaurants)
            dtm.addRow(restaurant.toArray());
        if (dtm.getRowCount() == 0) {
            hideTable();1
            JOptionPane.showMessageDialog(this, "No results found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hideTable() {
        jpQuery.setVisible(false);
    }

    public void showTable() {
        jpQuery.setVisible(true);
    }

    public Object[] getFilterConditions() {
        ArrayList<Object> conditions = new ArrayList<>();
        if (cmbRegion.getSelectedIndex() != Restaurant.REGIONS.length - 1) {
            conditions.add(MichelinDB.REGION_ATRIBUTE);
            conditions.add(cmbRegion.getSelectedItem());
        }
        if (cmbDistinction.getSelectedIndex() != Restaurant.DISTINCTIONS.length - 1) {
            conditions.add(MichelinDB.DISTINCTION_ATRIBUTE);
            conditions.add(cmbDistinction.getSelectedIndex() + 1);
        }
        return conditions.toArray();
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
}
