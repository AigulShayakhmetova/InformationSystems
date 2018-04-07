package dataPackage;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Serializable {
    public String surname;
    public String name;
    public String patronymic;
    public String cathedra;
    public Positions position;
    public Rates rate;
    public int maxStudens;
    public ArrayList<Student> students;

    public Lecturer(String surname, String name, String patronymic, String cathedra, Positions position, Rates rate) /** установка информации о преподавателе */
    {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.cathedra = cathedra;
        this.position = position;
        this.rate = rate;
        float fRate = Float.parseFloat(rate.toString());
        if(position.equals(Positions.PROFESSOR)) /** рассчет количества студентов для профессора */
        {
            maxStudens = (int)(8*fRate);
        } else if(position.equals(Positions.DOCENT)) /** расчет количества студентов для доцента */
        {
            maxStudens = (int)(6*fRate);
        } else if(position.equals(Positions.SENIOR_LECTURER)) /** расчет количества студентов для старшего преподавателя */
        {
            maxStudens = (int)(4*fRate);
        } else if(position.equals(Positions.LECTURER)) /** расчет количества студентов для преподавателя */
        {
            maxStudens = (int)(2*fRate);
        }
        students = new ArrayList<Student>(maxStudens); /** список максимального количества студентов */
        System.out.println("Lecturer::Lecturer(); -- fRate:" + fRate + " maxStudens:" + maxStudens);
    }

    public String getFIO() /**для возврата студенту своего дипломного руководителя */
    {
        StringBuilder stringBuilder = new StringBuilder(); /** создание строки для информации о преподавателе */
        stringBuilder.append(surname);
        stringBuilder.append(" " + name);
        stringBuilder.append(" " + patronymic);
        return stringBuilder.toString();
    }

    public String toString() /** для отображения в списке */
    {
        StringBuilder stringBuilder = new StringBuilder();/** создание строки для информации о преподавателе */
        stringBuilder.append("Lecturer:[");
        stringBuilder.append("surname:" + surname);
        stringBuilder.append(", " + "name:" + name);
        stringBuilder.append(", " + "patronymic:" + patronymic);
        stringBuilder.append(", " + "cathedra:" + cathedra);
        stringBuilder.append(", " + "position:" + position);
        stringBuilder.append(", " + "rate:" + rate);
        stringBuilder.append(", " + "maxStudens:" + maxStudens);
        stringBuilder.append(", " + "students:" + students);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
