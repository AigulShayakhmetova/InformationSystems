package dataPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataCollector implements Serializable {
    public ArrayList<Lecturer> lecturers;
    public ArrayList<Student> freeStudents;
    private String dataCollectorFileName = "dataCollector";

    public DataCollector() {
        lecturers = new ArrayList<Lecturer>();
        freeStudents = new ArrayList<Student>();
        File file = new File(dataCollectorFileName);
        if(file.exists() && !file.isDirectory()) {
            DataCollector dataCollector = deSerializeFromFile(file);
            if(dataCollector != null) {
                lecturers.addAll(dataCollector.lecturers);
                freeStudents.addAll(dataCollector.freeStudents);
            }
        }
        System.out.println("DataCollector::DataCollector(); -- lecturers.size():" + lecturers.size());
        for(Lecturer lecturer : lecturers) {
            System.out.println("DataCollector::DataCollector(); -- lecturer:" + lecturer);
        }
    }

    public void addLecturer(Lecturer newLecturer) {
        lecturers.add(newLecturer);
        serializeToFile();
    }

    public Lecturer addStudent(Student newStudent) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.students.size() < lecturer.maxStudens) {
                lecturer.students.add(newStudent);
                serializeToFile(); // twice
                return lecturer;
            }
        }
        freeStudents.add(newStudent);
        serializeToFile(); // twice
        return null;
    }

    public void serializeToFile() {
        System.out.println("DataCollector::serializeToFile(); -- Try!");
        try {
            FileOutputStream fos = new FileOutputStream(dataCollectorFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("DataCollector::serializeToFile(); -- Good!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static DataCollector deSerializeFromFile(File file) {
        System.out.println("DataCollector::deSerializeFromFile(); -- Try! file.getName():" + file.getName());
        try {
            FileInputStream fis = new FileInputStream(file.getName());
            ObjectInputStream ois = new ObjectInputStream(fis);
            DataCollector dataCollector = (DataCollector) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("DataCollector::deSerializeFromFile(); -- Good!");
            for(Lecturer lecturer : dataCollector.lecturers) {
                System.out.println("DataCollector::deSerializeFromFile(); -- lecturer:" + lecturer);
            }
            return dataCollector;
        } catch (IOException ioe) {
            System.err.println("DataCollector::deSerializeFromFile(); -- ioe:" + ioe);
            file.deleteOnExit();
//            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.err.println("DataCollector::deSerializeFromFile(); -- Class not found | c:" + c);
//            c.printStackTrace();
            return null;
        }
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DataCollector:[");
        stringBuilder.append("lecturers.size():" + lecturers.size());
        stringBuilder.append(", " + "freeStudents.size():" + freeStudents.size());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
