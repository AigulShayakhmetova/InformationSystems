package panelsPackage;

import java.awt.CardLayout;
import java.awt.Container;

import dataPackage.DataCollector;
import mainPackage.MainAppletClass;

public class PanelsManager/** публичный класс */
{
    public DataCollector dataCollector;
    public MainAppletClass mainAppletClass;
    public Container container;
    public CardLayout cardLayout;
    public MainPanel mainPanel;
    public LecturerPanel lecturerPanel;
    public StudentPanel studentPanel;
    public InfoListPanel infoListPanel;

    public PanelsManager(MainAppletClass mainAppletClass, Container container)/**конструктор класса */
    {
        dataCollector = new DataCollector();/** создание объекта */

        this.mainAppletClass = mainAppletClass;
        this.container = container;
        this.cardLayout = new CardLayout();
        this.container.setLayout(cardLayout);
        setMainPanel();/**установка панели*/
    }

    public void setMainPanel() /**установка панели*/
    {
        if(mainPanel == null) /**если панели не было */
        {
            mainPanel = new MainPanel(this);/**устанавливаем панель */ 
        }
        container.add(mainPanel.getName(), mainPanel);/**заносим панель в контейнер */
        cardLayout.show(container, mainPanel.getName());/** отображаем панель */
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

}
