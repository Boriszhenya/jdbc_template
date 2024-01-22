package org.example.third_lection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HQLExample {
    public static void main(String[] args) {
        // Создаём объект конфигурации
        Configuration configuration = new Configuration().configure();

        // Используем SessionFactory для создания сессии
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // Использование HQL для получения всех сотрудников с зарплатой, превышающей указанную сумму
            String hql = "FROM Employee WHERE salary > :salaryThreshold";
            try (Session session = sessionFactory.openSession()) {
                Query<Employee> query = session.createQuery(hql, Employee.class);
                query.setParameter("salaryThreshold", 60000.0);
                List<Employee> employees = query.list();
                employees.forEach(System.out::println);
            }
        }
    }
}
