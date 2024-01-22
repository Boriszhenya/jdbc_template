package org.example.third_lection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class WriteEmployeesToDB {

    public static void main(String[] args) {
        // Предположим, что у нас есть всё для работы SessionFactory, а у нас есть =)
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            // Создаём лист экземпляров
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(new Employee("Mon Roe", 65000.0));
            employeeList.add(new Employee("Sam Smith", 75000.0));
            employeeList.add(new Employee("Boss Boson", 955000.0));
            // Создаём транзакцию и сохраняем все экземпляры в БД
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                for (Employee employee : employeeList) {
                    session.persist(employee);
                }

                transaction.commit();
            }
        }
    }
}
