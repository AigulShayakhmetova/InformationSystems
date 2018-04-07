package panelsPackage;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import dataPackage.DataCollector;
import dataPackage.Lecturer;
import dataPackage.Positions;
import dataPackage.Rates;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.FlowLayout;
import javax.swing.Box;
import java.awt.Component;

public class LecturerPanel extends JPanel {
    private PanelsManager panelsManager;
    private DataCollector dataCollector;
    private JTextField surname;
    private JTextField name;
    private JTextField patronymic;
    private JTextField cathedra;
    private JComboBox position;
    private JComboBox rate;
    private JLabel label_err;
    private JButton btnSafeData;
    private JButton btnGetListStudents;
    private boolean editMode;

    public LecturerPanel(PanelsManager panelsManager, DataCollector dataCollector) {
        this.panelsManager = panelsManager;
        this.dataCollector = dataCollector;

        System.out.println("LecturerPanel::LecturerPanel(); -- this.getClass().getName():" + this.getClass().getName());
        setName(this.getClass().getName());/** класс "преподаватель" */

        setLayout(new FlowLayout(FlowLayout.CENTER));

        Box verticalBox = Box.createVerticalBox();

        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);

        JLabel label_surname = new JLabel("Фамилия"); /** создание подписи "Фамилия" */
        horizontalBox.add(label_surname);
        label_surname.setFont(new Font("Tahoma", Font.PLAIN, 15));

        surname = new JTextField();/** создание поля ввода для фамилии */
        horizontalBox.add(surname);
        surname.setColumns(10);

        Box horizontalBox_1 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_1);

        JLabel label_name = new JLabel("Имя");/** создание подписи "Имя" */
        horizontalBox_1.add(label_name);
        label_name.setFont(new Font("Tahoma", Font.PLAIN, 15));

        name = new JTextField();/** создание поля ввода для имени */
        horizontalBox_1.add(name);
        name.setColumns(10);

        Box horizontalBox_2 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_2);

        JLabel label_patronymic = new JLabel("Отчество");/** создание подписи "Отчество" */
        horizontalBox_2.add(label_patronymic);
        label_patronymic.setFont(new Font("Tahoma", Font.PLAIN, 15));

        patronymic = new JTextField();/** создание поля ввода для отчества */
        horizontalBox_2.add(patronymic);
        patronymic.setColumns(10);

        Box horizontalBox_3 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_3);

        JLabel label_cathedra = new JLabel("Кафедра");/** создание подписи "Кафедра" */
        horizontalBox_3.add(label_cathedra);
        label_cathedra.setFont(new Font("Tahoma", Font.PLAIN, 15));

        cathedra = new JTextField();/** создание поля ввода для кафедры */
        horizontalBox_3.add(cathedra);
        cathedra.setColumns(10);

        Box horizontalBox_4 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_4);

        JLabel label_post = new JLabel("Должность");/** создание подписи "Должность" */
        horizontalBox_4.add(label_post);
        label_post.setFont(new Font("Tahoma", Font.PLAIN, 15));

        position = new JComboBox(Positions.values());/* для выбора должности из выпадающего списка */
        horizontalBox_4.add(position);
        position.setEditable(true);

        Box horizontalBox_5 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_5);

        JLabel label_rate = new JLabel("Ставка");/** создание подписи "Ставка" */
        label_rate.setAlignmentX(Component.CENTER_ALIGNMENT);
        horizontalBox_5.add(label_rate);
        label_rate.setFont(new Font("Tahoma", Font.PLAIN, 15));

        rate = new JComboBox(Rates.values());/* для выбора ставки из выпадающего списка */
        rate.setEditable(true);
        horizontalBox_5.add(rate);

        label_err = new JLabel("");
        verticalBox.add(label_err);

        btnSafeData = new JButton("Сохранить данные"); /** создание кнопки "Сохранить данные" */
        btnSafeData.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSafeData.addActionListener(new ActionListener() /** при нажатии на кнопку "Сохранить данные" заносится приподаватель*/
                                      {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("LecturerPanel::btnSafeData::actionPerformed(); -- ");/** прошло успешное добавление */
                if (validateEnterAllData()) /** проверка на заполненность всех полей */
                {
                    changeEditMode();
                }
            }
        });
        verticalBox.add(btnSafeData);
        add(verticalBox);

        btnGetListStudents = new JButton("Получить список дипломников"); /** создание кнопки "Получить список дипломников" */
        btnGetListStudents.setVisible(false); /** кнопка не видима*/
        btnGetListStudents.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetListStudents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("LecturerPanel::btnGetListStudents::actionPerformed(); -- ");
                Lecturer newLecturer = new Lecturer(surname.getText(), name.getText(), patronymic.getText(),
                        cathedra.getText(), (Positions) position.getSelectedItem(), (Rates) rate.getSelectedItem());
                dataCollector.addLecturer(newLecturer);
                System.out.println("LecturerPanel::btnGetListStudents::actionPerformed(); -- newLecturer:" + newLecturer);
                panelsManager.setInfoListPanel();
            }
        });
        verticalBox.add(btnGetListStudents);

        editMode = true;
        System.out.println("LecturerPanel::LecturerPanel(); -- getPreferredSize():" + getPreferredSize());
    }

    private boolean validateEnterAllData() {
        if (surname.getText().isEmpty() || name.getText().isEmpty() || patronymic.getText().isEmpty()
                || cathedra.getText().isEmpty() || position.getSelectedIndex() == 0 || rate.getSelectedIndex() == 0) {
            label_err.setText("Заполните все поля!!!");/** обязательное заполнение всех полей */
            new java.util.Timer().schedule(new TimerTask() {
                public void run() {
                    label_err.setText("");
                }
            }, 2000);/** через 2 секунда тест "заполните все поля" пропадает */
            return false;
        } else {
            return true;
        }
    }

    private void changeEditMode() /** функция для изменение данных преподавателя */
    {
        System.out.println("LecturerPanel::changeEditMode(); -- editMode:" + editMode);
        editMode = !editMode; /** панель в режиме редактирования */
        surname.setEnabled(editMode);/** установка новых данных, панель не редактируется */
        name.setEnabled(editMode); 
        patronymic.setEnabled(editMode);
        cathedra.setEnabled(editMode);
        position.setEnabled(editMode);
        rate.setEnabled(editMode);
        if (editMode) {
            btnSafeData.setText("Сохранить данные");
        } else {
            btnSafeData.setText("Изменить информацию");
        }
        btnGetListStudents.setVisible(!editMode);/** кнопка видима, когда данные внесены 
    }
}
