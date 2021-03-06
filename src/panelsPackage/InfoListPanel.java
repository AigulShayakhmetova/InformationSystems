package panelsPackage;

import javax.swing.JPanel;

import dataPackage.DataCollector;
import dataPackage.Lecturer;
import dataPackage.Student;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class InfoListPanel extends JPanel {
    private PanelsManager panelsManager;
    private DataCollector dataCollector;
    private DefaultListModel defaultListModelLecturers;
    private DefaultListModel defaultListModelStudents;
    private JList studentsList;

    public InfoListPanel(PanelsManager panelsManager, DataCollector dataCollector) {
        this.panelsManager = panelsManager;
        this.dataCollector = dataCollector;

        System.out.println("InfoListPanel::InfoListPanel(); -- this.getClass().getName():" + this.getClass().getName());
        setName(this.getClass().getName());

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
        setLayout(flowLayout);/**Установка режима FlowLayout выполняется при помощи метода setLayout*/

        Box verticalBox_2 = Box.createVerticalBox();
        add(verticalBox_2);

        Box horizontalBox = Box.createHorizontalBox();
        verticalBox_2.add(horizontalBox);

        Box verticalBox = Box.createVerticalBox();
        horizontalBox.add(verticalBox);

        JLabel lblNewLabel = new JLabel("Преподаватель");/**Создаём надпись "Преподаватель"*/
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox.add(lblNewLabel);/** надпись добавляется в компонент verticalBox*/

        defaultListModelLecturers = new DefaultListModel();
        JList lecturersList = new JList();/**Создание списка JList преподавателей */
        lecturersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lecturersList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                JList sourceList = (JList)arg0.getSource();
                System.out.println("InfoListPanel::lecturersList::valueChanged(); -- " + sourceList.getSelectedIndex());
                defaultListModelStudents.removeAllElements();
                if(!sourceList.isSelectionEmpty()) {
                    Lecturer lecturer = dataCollector.lecturers.get(sourceList.getSelectedIndex());
                    for(Student student : lecturer.students) {
                        defaultListModelStudents.addElement(student.getFIO());
                    }
                    System.out.println("InfoListPanel::lecturersList::valueChanged(); -- defaultListModelStudents" +
        defaultListModelStudents);
                }
            }
        });
        lecturersList.setModel(defaultListModelLecturers);
        verticalBox.add(lecturersList);

        Box verticalBox_1 = Box.createVerticalBox();
        horizontalBox.add(verticalBox_1);

        JLabel lblNewLabel_1 = new JLabel("Студент");/**Создаём надпись "Студент"*/
        lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox_1.add(lblNewLabel_1);

        defaultListModelStudents = new DefaultListModel();
        studentsList = new JList();/**Создание списка JList студентов */
        studentsList.setModel(defaultListModelStudents);
        verticalBox_1.add(studentsList);

        JButton btnBack = new JButton("Назад");/**Создаём кнопку "Назад"*/
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelsManager.setMainPanel();
            }
        });
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        verticalBox_2.add(btnBack);
        System.out.println("InfoListPanel::InfoListPanel(); -- dataCollector:" + dataCollector);

    }

    public void updateInfoList() /**Метод для обновления списка преподавателей*/
    {
        System.out.println("InfoListPanel::updateInfoList(); -- dataCollector:" + dataCollector);
        defaultListModelLecturers.removeAllElements();
        for (Lecturer lecturer : dataCollector.lecturers) {
            defaultListModelLecturers.addElement(lecturer.getFIO());
        }
    }
}
