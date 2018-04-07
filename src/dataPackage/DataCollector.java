package dataPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataCollector implements Serializable /** сериализация объектов класса (создание клона) */
{
    public ArrayList<Lecturer> lecturers;/** массив преподавателей */
    public ArrayList<Student> freeStudents; /** массив свободны студентов */ 
    private String dataCollectorFileName = "dataCollector";

    public DataCollector() /** конструктор */
    {
        lecturers = new ArrayList<Lecturer>();
        freeStudents = new ArrayList<Student>();
        File file = new File(dataCollectorFileName);/** определние файла */
        if(file.exists() && !file.isDirectory()) /** файл существует и он не переполнен */
        {
            DataCollector dataCollector = deSerializeFromFile(file); /** десериализация для файла (восстановление)  */
            if(dataCollector != null) /** файл не пустой */ 
            {
                lecturers.addAll(dataCollector.lecturers); /** добавляем (восстанавливаем )  преподавателя  */
                freeStudents.addAll(dataCollector.freeStudents); /** добавляем (востанавливаем ) студента  */
            }
        }
        System.out.println("DataCollector::DataCollector(); -- lecturers.size():" + lecturers.size());
        for(Lecturer lecturer : lecturers) {
            System.out.println("DataCollector::DataCollector(); -- lecturer:" + lecturer);
        }
    }

    public void addLecturer(Lecturer newLecturer) /** добавление преподвателя */
    {
        lecturers.add(newLecturer);
        serializeToFile();
    }

    public Lecturer addStudent(Student newStudent) /** добавление студента преподавателю */
    {
        
        for (Lecturer lecturer : lecturers) 
        {
            if (lecturer.students.size() < lecturer.maxStudens) /** преподаватель имеет возможность взять студента */
            {
                lecturer.students.add(newStudent); /** добавляем к преподавателю еще одного студента */
                serializeToFile(); 
                return lecturer;
            }
        }
        freeStudents.add(newStudent);/** добавление свободного студента */
        serializeToFile(); 
        return null;
    }

    public void serializeToFile() /** сериализация файла с объектами  */
    {
        System.out.println("DataCollector::serializeToFile(); -- Try!");
        try
        {
            FileOutputStream fos = new FileOutputStream(dataCollectorFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this); /** добавление в файл */
            oos.close();
            fos.close();
            System.out.println("DataCollector::serializeToFile(); -- Good!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static DataCollector deSerializeFromFile(File file)/**  десериализация файла с объектами */ 
    {
        System.out.println("DataCollector::deSerializeFromFile(); -- Try! file.getName():" + file.getName());
        try 
        {
            FileInputStream fis = new FileInputStream(file.getName());
            ObjectInputStream ois = new ObjectInputStream(fis);
            DataCollector dataCollector = (DataCollector) ois.readObject(); /** чтение файла с объектами */
            ois.close();
            fis.close();
            System.out.println("DataCollector::deSerializeFromFile(); -- Good!"); /** десериализация прошла успешно */
            for(Lecturer lecturer : dataCollector.lecturers) {
                System.out.println("DataCollector::deSerializeFromFile(); -- lecturer:" + lecturer); /** вывод обавленного ранее преподавателя с параметрами */
            }
            return dataCollector;
        } catch (IOException ioe) {
            System.err.println("DataCollector::deSerializeFromFile(); -- ioe:" + ioe);
            file.deleteOnExit();
            return null; /** что - то пошло не так */
        } catch (ClassNotFoundException c) {
            System.err.println("DataCollector::deSerializeFromFile(); -- Class not found | c:" + c);
            return null;
        }
    }
    
    public String toString() 
    {
        StringBuilder stringBuilder = new StringBuilder(); /** создание строчки */
        stringBuilder.append("DataCollector:["); 
        stringBuilder.append("lecturers.size():" + lecturers.size()); /** количество преподавателей */
        stringBuilder.append(", " + "freeStudents.size():" + freeStudents.size()); /* количество студентов */
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
