package view;

import controller.ScheduleJPanelController;
import service.ScheduleTableSearchService;
import service.ScheduleTableService;
import view.table_models.ArrivalTableModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by ПК on 11.12.2016.
 */
public class ScheduleJPanelGUI extends EastJPanelGUI {
    public ScheduleJPanelGUI(){

        //this.instScheduleTableSearchService = new ScheduleTableService();
        this.instScheduleTableSearchService = new ScheduleTableSearchService();
    }

    protected ScheduleTableSearchService instScheduleTableSearchService;
    //protected ScheduleTableService instScheduleTableSearchService;

    protected final String arrivalsStr = "ARRIVALS";
    protected final String departuresStr = "DEPARTURES";
    private String searchStr = "SEARCH";
    private JSlider jSliderTime;
    public static boolean isArrivalsChoosedComboBox = true;

    @Override
    public void drawJPanel() {

        eastJPanel.setVisible(false);
        eastJPanel.removeAll();
        eastJPanel.setBackground(darkBackGround);
        eastJPanel.setPreferredSize(eastJPanelDimension);
        eastJPanel.setLayout(new BorderLayout());
        eastJPanel.add(topEastJPanel,BorderLayout.NORTH);
        eastJPanel.add(middleEastJPanel,BorderLayout.CENTER);
        eastJPanel.add(bottomEastJPanel,BorderLayout.SOUTH);
        drawTopEastJPanel();
        ArrivalTableModel instArrivalTableModel =
                new ArrivalTableModel(instScheduleTableSearchService.makeArrivalScheduleTable(jSliderTime.getValue(),
                        instScheduleTableSearchService.getListAllArrivalFlights()));
        drawTable(instArrivalTableModel);
        drawBottomEastJPanel();

        eastJPanel.setVisible(true);
    }

    public void drawTopEastJPanel(){
        ScheduleJPanelController instScheduleJPanelController = new ScheduleJPanelController();
        topEastJPanel.setVisible(false);
        topEastJPanel.removeAll();
        topEastJPanel.setPreferredSize(topEastJPanelDimension);
        topEastJPanel.setBackground(darkBackGround);
        topEastJPanel.setLayout(new BorderLayout());
        northBoardJPanel.setVisible(false);
        northBoardJPanel.removeAll();
        northBoardJPanel.setLayout(new GridLayout(1,3,10,0));
        northBoardJPanel.setBackground(backGround);
        northBoardJPanel.setPreferredSize(northBoardJPanelDimension);
        JButton arrivalsButton = new JButton(arrivalsStr);
        JButton departuresButton = new JButton(departuresStr);
        JButton searchButton = new JButton(searchStr);
        Dimension buttonDimension = new Dimension(200,50);
        JButton[] arr = {arrivalsButton,departuresButton,searchButton};
        addPropertiesToTopButtons(arr,buttonDimension);
        northBoardJPanel.setVisible(true);
        topEastJPanel.add(northBoardJPanel,BorderLayout.NORTH);
        centerBoardJPanel.setVisible(false);
        centerBoardJPanel.removeAll();
        centerBoardJPanel.setPreferredSize(centerBoardJPanelDimension);
        centerBoardJPanel.setBackground(red);
        centerBoardJPanel.setVisible(true);
        topEastJPanel.add(centerBoardJPanel,BorderLayout.CENTER);
        drawSouthBoardJPanel();
        topEastJPanel.add(southBoardJPanel,BorderLayout.SOUTH);
        instScheduleJPanelController.topButtonsController(arrivalsButton,departuresButton,searchButton,jSliderTime);
        instEastJPanelController.topButtonsMouseController(arrivalsButton);
        instEastJPanelController.topButtonsMouseController(departuresButton);
        instEastJPanelController.topButtonsMouseController(searchButton);
        arrivalsButton.setBackground(red);
        topEastJPanel.setVisible(true);

    }

    protected void drawSouthBoardJPanel(){
        ScheduleJPanelController instScheduleJPanelController = new ScheduleJPanelController();
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        southBoardJPanel.setLayout(new BorderLayout());
        jSliderTime = new JSlider(0,24,3);
        jSliderTime.setPreferredSize(new Dimension(850,25));
        jSliderTime.setMajorTickSpacing(12);
        jSliderTime.setMinorTickSpacing(3);
        jSliderTime.setPaintTicks(true);
        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        Font font = new Font("Verdana", Font.PLAIN, 10);
        JLabel label24 = new JLabel("24h");
        JLabel label12 = new JLabel("12h");
        JLabel label0 = new JLabel("0h");
        label24.setFont(font);
        label12.setFont(font);
        label0.setFont(font);
        labelTable.put(0, label24);
        labelTable.put(12, label12);
        labelTable.put(24, label0);
        jSliderTime.setLabelTable(labelTable);
        jSliderTime.setPaintLabels(true);
        jSliderTime.setBackground(darkBackGround);
        jSliderTime.setInverted(true);
        jSliderTime.setValue(24);
        southBoardJPanel.add(jSliderTime,BorderLayout.WEST);
        instScheduleJPanelController.jSliderTimeController(jSliderTime);
        southBoardJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanelSearchSchedule(){
        ScheduleJPanelController instScheduleJPanelController = new ScheduleJPanelController();
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        southBoardJPanel.setLayout(new BorderLayout());
        JPanel topJPanel = new JPanel();
        topJPanel.setBackground(darkBackGround);
        topJPanel.setPreferredSize(new Dimension(850,50));
        topJPanel.setLayout(new GridLayout(2,2,50,10));
        String[] scheduleStr;
        if(isArrivalsChoosedComboBox) {scheduleStr = new String[]{arrivalsStr, departuresStr};}
        else {scheduleStr = new String[]{departuresStr,arrivalsStr};}
        JComboBox scheduleComboBox = new JComboBox(scheduleStr);
        JComboBox dateComboBox = new JComboBox(instScheduleTableSearchService.makeArrOfDatesForSearchComboBox());
        JComboBox destinationComboBox = new JComboBox(instScheduleTableSearchService.makeArrOfDestinPortsForSearchComboBox());
        JComboBox flightsComboBox = new JComboBox(instScheduleTableSearchService.makeArrOfFlightsForSearchComboBox());
        JComboBox[] arrComboBox = {dateComboBox,destinationComboBox,scheduleComboBox,flightsComboBox};
        Font font = new Font("Verdana", Font.BOLD, 10);
        for (JComboBox combo:arrComboBox) {
            combo.setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    return new JButton() {
                        @Override
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
            ((JLabel)combo.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
            combo.setBackground(darkBackGround);
            combo.setFont(font);
            combo.setMaximumRowCount(5);
            combo.setAutoscrolls(true);
            combo.setAlignmentX(SwingConstants.CENTER);
            topJPanel.add(combo);
        }
        southBoardJPanel.add(topJPanel,BorderLayout.NORTH);
        JPanel bottomJPanel = new JPanel();
        bottomJPanel.setBackground(darkBackGround);
        bottomJPanel.setPreferredSize(new Dimension(850,20));
        bottomJPanel.setLayout(new CardLayout());
        JButton searchJButton = new JButton(searchStr);
        searchJButton.setFont(font);
        searchJButton.setBackground(red);
        searchJButton.setPreferredSize(new Dimension(200,15));
        bottomJPanel.add(searchJButton,BorderLayout.CENTER);
        southBoardJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        southBoardJPanel.setVisible(true);
        instScheduleJPanelController.scheduleSearcComboBoxController(scheduleComboBox);
        instScheduleJPanelController.directionComboBoxSearchController(destinationComboBox,flightsComboBox,dateComboBox);
        instScheduleJPanelController.dateComboBoxSearchController(destinationComboBox,flightsComboBox,dateComboBox);
        instScheduleJPanelController.searchButtonSearchMenu(searchJButton,dateComboBox,destinationComboBox,scheduleComboBox,flightsComboBox);
    }

}