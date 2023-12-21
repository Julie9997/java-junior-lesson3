package ru.geekbrains.lesson3.hw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Program {
    static ObjectMapper objectMapper = new ObjectMapper();
    static XmlMapper xmlMapper = new XmlMapper();
    static String TITLE_BIN = "students.bin";
    static String TITLE_JSON = "students.json";
    static String TITLE_XML = "students.xml";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Иван", 24, 4.9);

        doSerial(student, TITLE_BIN);
        Student deserialStudent = (Student) doDeserial(TITLE_BIN);

        System.out.println(deserialStudent);
        // Средний балл 0.0,так как поле не было сериализовано в соответствие с модификатором transient

        objectMapper.writeValue(new File(TITLE_JSON), student);
        System.out.println("Выполнена сериализация JSON");
        Student deserialJSONStudent = objectMapper.readValue(new File(TITLE_JSON), Student.class);
        System.out.println("Выполнена десериализация JSON");

        xmlMapper.writeValue(new File(TITLE_XML), student);
        System.out.println("Выполнена сериализация XML");
        Student deserialXMLStudent = xmlMapper.readValue(new File(TITLE_XML), Student.class);
        System.out.println("Выполнена десериализация XML");

    }

    static void doSerial(Object obj, String title) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(title))) {
            objectOutputStream.writeObject(obj);
            System.out.println("Выполнена сериализация BIN");
        }
    }

    static Object doDeserial(String title) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(title))) {
            Object obj = objectInputStream.readObject();
            System.out.println("Выполнена десериализация BIN");
            return obj;
        }
    }
}
