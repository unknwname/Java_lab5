package model;

import java.util.ArrayList;

public class Company {
    private ArrayList<Staff> employees = new ArrayList<>();

    public Staff get(int index){
        return employees.get(index);
    }

    public void Hire(Staff employee){
        employees.add(employee);
    }

    public void Dismiss(Staff employee){
        employees.remove(employee);
    }

    public void Dismiss(int index){
        employees.remove(index);
    }

    public String GetInfo(Staff employee){
        StringBuilder info = new StringBuilder();

        info.append(employee.GetJob());
        info.append(employee.getName() + "\n");
        info.append("Зарплата: " + employee.getSalary() + "\n");
        info.append(employee.GetDetails());

        return info.toString();
    }

    public String PrintList(){
        StringBuilder list = new StringBuilder();
        for (Staff emp : employees) {
            list.append("[" + emp.GetJob() + "] ");
            list.append(emp.getName() + " ");
            list.append(emp.getSalary() + "\n");
        }
        return list.toString();
    }

    public Staff FindEmployee(String name){
        for (Staff emp : employees) {
            if (emp.getName().equals(name)) {
                return emp;
            }
        }
        return null;
    }

    public void StartWorking(){
        for (Staff emp : employees) {
            System.out.println(emp.DoWork() + "\n");
        }
    }

    public ArrayList<Staff> getEmployees() {
        return employees;
    }
}
