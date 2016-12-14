package controller;

import model.Flights;
import view.table_models.EditFlightsTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by ПК on 14.12.2016.
 */
public class EditFlightsController extends EditDataJPanelController {
    public EditFlightsController(){}

    @Override
    public void chooseTableButtons(final JButton airlinesButton, JButton airplanesButton, JButton flightsButton, JButton passengersButton, JButton usersButton, final JButton[] arrButtons) {
        for (JButton button:arrButtons){button.setBackground(backGround);}
        flightsButton.setBackground(red);
        EditFlightsTableModel instEditFlightsTableModel = new EditFlightsTableModel(instFlightService.getAllService());
        JTable allJTable = drawTable(instEditFlightsTableModel);
        int columnsQuantity = allJTable.getColumnCount();
        if (isInsertPresed){
            JTable insertJTable = drawInsertNewDataTable(columnsQuantity);
            insertNewDataController(confirmInsertNewDataButton,insertJTable);
        }
        else{
            editTableController(allJTable);
        }
    }

    private void insertNewDataController(final JButton button, final JTable table){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flights flight = addFlightObject(table);
                instFlightService.insertNewService(flight);
                EditFlightsTableModel instEditFlightsTableModel = new EditFlightsTableModel(instFlightService.getAllService());
                editTableController(drawTable(instEditFlightsTableModel));
            }
        });
    }

    private void editTableController(final JTable table) {
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Flights flight = addFlightObject(table);
                    if (isEditPresed) {
                        instFlightService.editDataService(flight);
                    }
                    else if (isDeletePresed) {
                        if (messageForDelete("Flight")) {instFlightService.deleteDataService(flight);}
                    }
                    EditFlightsTableModel instEditFlightsTableModel = new EditFlightsTableModel(instFlightService.getAllService());
                    editTableController(drawTable(instEditFlightsTableModel));
                }
            }});
    }

    private Flights addFlightObject(JTable table){
        int row = table.getSelectedRow();
        Long id;
        if(isInsertPresed){id = 0l;}
        else {id = (Long) table.getValueAt(row, 0);}
        Flights flight = new Flights();
        flight.setId(id);
        flight.setNumber(table.getValueAt(row,1).toString());
        flight.setPortOfDepart(table.getValueAt(row,2).toString());
        flight.setPortOfDestin(table.getValueAt(row,3).toString());
        flight.setDateOfDepart(table.getValueAt(row,4).toString());
        flight.setTimeOdDepart(table.getValueAt(row,5).toString());
        flight.setDateOfArrive(table.getValueAt(row,6).toString());
        flight.setTimeOfArrive(table.getValueAt(row,7).toString());
        flight.setPriceEconom(Integer.parseInt(table.getValueAt(row,8).toString()));
        flight.setPriceBusiness(Integer.parseInt(table.getValueAt(row,9).toString()));
        flight.setAirplane_id(Long.parseLong(table.getValueAt(row,10).toString()));
        return flight;
    }
}

