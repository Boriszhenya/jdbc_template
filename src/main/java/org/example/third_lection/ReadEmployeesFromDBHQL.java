package org.example.third_lection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReadEmployeesFromDBHQL {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM Employee";
                List<Employee> employees = session.createQuery(hql, Employee.class).list();

                employees.forEach(System.out::println);
            }
        }
    }
}

