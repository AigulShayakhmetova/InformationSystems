package panelsPackage;

import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel {
    public MainPanel(PanelsManager panelsManager) /** конструктор */
    {
        System.out.println("MainPanel::MainPanel(); -- this.getClass().getName():" + this.getClass().getName());/**вывод объекта через его имя*/
        setName(this.getClass().getName());

        JButton lectureButton = new JButton("Преподаватель");/** создание кнопки "Прнеподаватель" */
        lectureButton.addActionListener(new ActionListener()   /** добавление прподавателя*/
                                        {
            public void actionPerformed(ActionEvent arg0)  
            {
                panelsManager.setLecturePanel();/** установка панели "Преподаватель" */
            }
        });
        JButton studentButton = new JButton("Студент");/** создание кнопки "Студент" */
        studentButton.addActionListener(new ActionListener() /** добавление студента */
                                        {
            public void actionPerformed(ActionEvent arg0) {
                panelsManager.setStudentPanel();/** установка панели "Стидент" */
            }
        });
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        add(lectureButton);
        add(studentButton);

        JButton btnInfoList = new JButton("Список");/** создание кнопки "Список" */
        btnInfoList.addActionListener(new ActionListener()/** добавление преподавателя */
                                      {
            public void actionPerformed(ActionEvent arg0) { 
                panelsManager.setInfoListPanel(); /** установка панели "Список" */
            }
        });
        add(btnInfoList);
    }
}
