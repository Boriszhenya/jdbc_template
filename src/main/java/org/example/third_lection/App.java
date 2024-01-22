package org.example.third_lection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {
        // Создаём объект конфигурации
        Configuration configuration = new Configuration().configure();

        // Используем SessionFactory для создания сессии
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // Создаем сессию
            try (Session session = sessionFactory.openSession()) {
                // Создать и сохранить объект employee
                Employee newEmployee = new Employee();
                newEmployee.setName("Hibernate Doe");
                newEmployee.setSalary(220120.24);

                Employee anotherEmployee = new Employee("Hibernate Roe", 202401.22);

                Transaction transaction = session.beginTransaction();
                session.save(newEmployee);
                session.save(anotherEmployee);
                transaction.commit();

                // Получаем сохраненного employee и выводим в консоль
                Employee retrievedEmployee = session.get(Employee.class, newEmployee.getId());
                System.out.println("Полученный Employee 1: " + retrievedEmployee);
                Employee retrievedEmployee2 = session.get(Employee.class, anotherEmployee.getId());
                System.out.println("Полученный Employee 2: " + retrievedEmployee2);
            }
        }
    }
}
