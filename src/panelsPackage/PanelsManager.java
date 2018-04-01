package panelsPackage;

import java.awt.CardLayout;
import java.awt.Container;

import dataPackage.DataCollector;
import mainPackage.MainAppletClass;

public class PanelsManager {
    public DataCollector dataCollector;
    public MainAppletClass mainAppletClass;
    public Container container;
    public CardLayout cardLayout;
    public MainPanel mainPanel;
    public LecturerPanel lecturerPanel;
    public StudentPanel studentPanel;
    public InfoListPanel infoListPanel;

    public PanelsManager(MainAppletClass mainAppletClass, Container container) {
        dataCollector = new DataCollector();

        this.mainAppletClass = mainAppletClass;
        this.container = container;
        this.cardLayout = new CardLayout();
        this.container.setLayout(cardLayout);
        setMainPanel();
    }

    public void setMainPanel() {
        if(mainPanel == null) {
            mainPanel = new MainPanel(this);
        }
        container.add(mainPanel.getName(), mainPanel);
        cardLayout.show(container, mainPanel.getName());
        mainAppletClass.setSize(container.getPreferredSize());
    }

    public void setLecturePanel() {
        if(lecturerPanel == null) {
            lecturerPanel = new LecturerPanel(this, dataCollector);
        }
        container.add(lecturerPanel.getName(), lecturerPanel);
        cardLayout.show(container, lecturerPanel.getName());
        mainAppletClass.setSize(container.getPreferredSize());
    }

    public void setStudentPanel() {
        if(studentPanel == null) {
            studentPanel = new StudentPanel(this, dataCollector);
        }
        container.add(studentPanel.getName(), studentPanel);
        cardLayout.show(container, studentPanel.getName());
        mainAppletClass.setSize(container.getPreferredSize());
    }

    public void setInfoListPanel() {
        if(infoListPanel == null) {
            infoListPanel = new InfoListPanel(this, dataCollector);
        }
        infoListPanel.updateInfoList();
        container.add(infoListPanel.getName(), infoListPanel);
        cardLayout.show(container, infoListPanel.getName());
        mainAppletClass.setSize(container.getPreferredSize());
    }

//    public void addPanel(JPanel panel, String name) {
//        container.add(name, panel);
//    }
//
//    public void showPanel(String name) {
//        cardLayout.show(container, name);
////        container.set
//    }
}
