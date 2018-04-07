package dataPackage;

import java.io.Serializable;

public class Student implements Serializable /** сериализация для студента */
{
    public String surname;
    public String name;
    public String patronymic;
    public String faculty;
    public String groupName;

    public Student(String surname, String name, String patronymic, String faculty, String groupName) /** установка данных студента */
    {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.faculty = faculty;
        this.groupName = groupName;
    }

    public String getFIO()  /** для отображения в списке*/
    {
        StringBuilder stringBuilder = new StringBuilder(); /** создание строчки с данными студента */
        stringBuilder.append(surname);
        stringBuilder.append(" " + name);
        stringBuilder.append(" " + patronymic);
        return stringBuilder.toString();
    }

    public String toString() /* запись данных студента */
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Student:[");
        stringBuilder.append("surname:" + surname);
        stringBuilder.append(", " + "name:" + name);
        stringBuilder.append(", " + "patronymic:" + patronymic);
        stringBuilder.append(", " + "faculty:" + faculty);
        stringBuilder.append(", " + "groupName:" + groupName);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
