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

    public void setMainPanel() /**установка самой главной панели*/
    {
        if(mainPanel == null) /**если панели не было */
        {
            mainPanel = new MainPanel(this);/**устанавливаем панель */ 
        }
        container.add(mainPanel.getName(), mainPanel);/**заносим главную панель в контейнер */
        cardLayout.show(container, mainPanel.getName());/** отображаем панель */
        mainAppletClass.setSize(container.getPreferredSize());
    }

    public void setLecturePanel() /**установка панели преподавателя*/
    {
        if(lecturerPanel == null) {
            lecturerPanel = new LecturerPanel(this, dataCollector);
        }
        container.add(lecturerPanel.getName(), lecturerPanel);/**заносим панель преподавателя в контейнер */
        cardLayout.show(container, lecturerPanel.getName());/** отображаем панель */
        mainAppletClass.setSize(container.getPreferredSize());
    }

    public void setStudentPanel()/**установка панели студента*/
    {
        if(studentPanel == null) {
            studentPanel = new StudentPanel(this, dataCollector);
        }
        container.add(studentPanel.getName(), studentPanel);/**заносим панель студента в контейнер */
        cardLayout.show(container, studentPanel.getName());/** отображаем панель */
        mainAppletClass.setSize(container.getPreferredSize());
    }

    public void setInfoListPanel() /**установка панели со списком преподавателей и студентов*/
    {
        if(infoListPanel == null) {
            infoListPanel = new InfoListPanel(this, dataCollector);
        }
        infoListPanel.updateInfoList();
        container.add(infoListPanel.getName(), infoListPanel);/**заносим данную панель в контейнер */
        cardLayout.show(container, infoListPanel.getName());/** отображаем панель */
        mainAppletClass.setSize(container.getPreferredSize());
    }

}
