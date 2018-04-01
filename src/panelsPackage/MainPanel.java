package panelsPackage;

import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel {
    public MainPanel(PanelsManager panelsManager) {
        System.out.println("MainPanel::MainPanel(); -- this.getClass().getName():" + this.getClass().getName());
        setName(this.getClass().getName());

        JButton lectureButton = new JButton("Преподаватель");
        lectureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelsManager.setLecturePanel();
            }
        });
        JButton studentButton = new JButton("Студент");
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelsManager.setStudentPanel();
            }
        });
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        add(lectureButton);
        add(studentButton);

        JButton btnInfoList = new JButton("Список");
        btnInfoList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panelsManager.setInfoListPanel();
            }
        });
        add(btnInfoList);
    }
}
