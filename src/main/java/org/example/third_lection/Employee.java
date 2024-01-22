package org.example.third_lection;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private double salary;

    public Employee() {}

    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString(){
        return String.format("Employee with Id: %s; Name: %s; Salary: %.2f.", getId(), getName(), getSalary());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
