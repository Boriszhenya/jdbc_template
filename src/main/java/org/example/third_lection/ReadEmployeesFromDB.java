package org.example.third_lection;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReadEmployeesFromDB {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                CriteriaQuery<Employee> criteriaQuery = session.getCriteriaBuilder().createQuery(Employee.class);
                Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
                List<String> names = Arrays.asList(new String[]{"John Doe", "Jane Doe"});
                List<Employee> employees = session.createQuery(criteriaQuery.select(employeeRoot).where(employeeRoot.get("name").in(names))).list();
                //List<Employee> employees = session.createQuery(criteriaQuery.select(employeeRoot)).list();

                employees.forEach(System.out::println);
            }
        }
    }
}

