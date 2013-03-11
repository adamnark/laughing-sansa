/*
 * Created by JFormDesigner on Sun Mar 20 14:13:59 IST 2011
 */
package examples.LostExample.others.stations;

import examples.LostExample.actions.StationDestroyAction;
import examples.LostExample.actions.StationHideAction;
import examples.LostExample.actions.StationRebuildAction;
import examples.utils.ExamplesUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author User #6
 */
public class StationsManager extends JPanel {

    private static final String STATIONS_LOCATION = "dharmastations/";
    private static final String JPG_EXTENSION = ".jpg";
    private List<DharmaStation> stations;
    private DefaultListModel<DharmaStation> model;

    public StationsManager() {
        initComponents();
        initStationsList();
        initDharmaStations();

        for (DharmaStation station : stations) {
            model.addElement(station);
            mainStationPanel.add(createStationPanel(station), station.getTitle());
        }
    }

    private void initStationsList() {
        model = new DefaultListModel<DharmaStation>();
        stationsList.setModel(model);

        stationsList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                DharmaStation station = model.get(stationsList.getSelectedIndex());
                CardLayout cardLayout = (CardLayout) mainStationPanel.getLayout();
                cardLayout.show(mainStationPanel, station.getTitle());
            }
        });
        //if we want control over how's the list DATA is being painted - but without actually painting it ourselves
        //we can set a Renderer that is basically a Swing component that is being created for every data item
        stationsList.setCellRenderer(new DharmaStationRenderer());

        hideButton.setAction(new StationHideAction(this));
        destroyButton.setAction(new StationDestroyAction(this));
        rebuildAction.setAction(new StationRebuildAction(this));
    }

    private void initDharmaStations() {
        stations = new ArrayList<DharmaStation>();
        URL resource = getClass().getResource(ExamplesUtils.IMAGES_FOLDER + STATIONS_LOCATION);
        try {
            File baseDir = new File(resource.toURI());
            if (baseDir.isDirectory() && baseDir.exists()) {
                for (File file : baseDir.listFiles(new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(JPG_EXTENSION);
                    }
                })) {
                    String fileName = file.getName();
                    String stationName = getStationName(fileName);
                    stations.add(new DharmaStation(stationName, STATIONS_LOCATION + fileName));
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String getStationName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return fileName;
        }

        int extensionIndex = fileName.lastIndexOf(JPG_EXTENSION);
        //found extension and going to remove it
        if (extensionIndex != -1) {
            return fileName.substring(0, extensionIndex);
        } else {
            return fileName;
        }
    }

    private JComponent createStationPanel(DharmaStation station) {
        return new DharmaStationPanel(station);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane2 = new JScrollPane();
        mainStationPanel = new JPanel();
        listPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        stationsList = new JList();
        buttonsPanel = new JPanel();
        hideButton = new JButton();
        destroyButton = new JButton();
        rebuildAction = new JButton();

        //======== this ========
        setBorder(new TitledBorder("The Dharma Stations"));
        setLayout(new BorderLayout());

        //======== scrollPane2 ========
        {

            //======== mainStationPanel ========
            {
                mainStationPanel.setLayout(new CardLayout());
            }
            scrollPane2.setViewportView(mainStationPanel);
        }
        add(scrollPane2, BorderLayout.CENTER);

        //======== listPanel ========
        {
            listPanel.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- stationsList ----
                stationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                stationsList.setVisibleRowCount(6);
                scrollPane1.setViewportView(stationsList);
            }
            listPanel.add(scrollPane1, BorderLayout.CENTER);

            //======== buttonsPanel ========
            {
                buttonsPanel.setLayout(new FlowLayout());

                //---- hideButton ----
                hideButton.setText("Hide");
                buttonsPanel.add(hideButton);

                //---- destroyButton ----
                destroyButton.setText("Destroy");
                buttonsPanel.add(destroyButton);

                //---- rebuildAction ----
                rebuildAction.setText("Rebuild");
                buttonsPanel.add(rebuildAction);
            }
            listPanel.add(buttonsPanel, BorderLayout.SOUTH);
        }
        add(listPanel, BorderLayout.WEST);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane2;
    private JPanel mainStationPanel;
    private JPanel listPanel;
    private JScrollPane scrollPane1;
    private JList stationsList;
    private JPanel buttonsPanel;
    private JButton hideButton;
    private JButton destroyButton;
    private JButton rebuildAction;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void setSelectedStationState(StationState newState) {
        int selectedIndex = stationsList.getSelectedIndex();
        if (selectedIndex > -1) {
            DharmaStation station = stations.get(selectedIndex);
            station.setState(newState);
            stationsList.updateUI();
        }
    }
}
