package view;


import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 08.12.2016.
 */
public class MainPageGUI implements GUIInterface {
    public MainPageGUI() {
    }

    public void drawJPanel() {
        WestJPanelGUI instWestJPanelGUI = new WestJPanelGUI();
        EastJPanelGUI instEastJPanelGUI = new EastJPanelGUI();
        jfrm.setSize(jfrmDimension);
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{210, 660};
        gridBagLayout.rowHeights = new int[]{460};
        mainJPanel.removeAll();
        mainJPanel.setVisible(true);
        mainJPanel.setLayout(gridBagLayout);
        mainJPanel.setBackground(backGround);
        mainJPanel.setSize(jfrmDimension);
        mainJPanel.add(westJPanel);
        mainJPanel.add(eastJPanel);
        JScrollPane srl = new JScrollPane(mainJPanel);
        srl.setAutoscrolls(true);
        jfrm.add(srl);
        instWestJPanelGUI.drawJPanel();
        instEastJPanelGUI.drawJPanel();
        jfrm.setVisible(true);
    }
}
