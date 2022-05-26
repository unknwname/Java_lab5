package controller;

import javax.swing.JOptionPane;

import model.*;
import view.*;


public class Controller {
    private Company company;
    private MainWindow mainWindow;
    private AddEmployeeWindow addEmployeeWindow;
    private CustomTableModel tableModel;

    public Controller (MainWindow mainWindow, AddEmployeeWindow addEmployeeWindow){
        this.addEmployeeWindow = addEmployeeWindow;
        this.mainWindow = mainWindow;
        company = new Company();
        tableModel = new CustomTableModel(company);

        this.mainWindow.setTableModel(tableModel);
        this.mainWindow.addListenerAddButton(e -> {
            this.addEmployeeWindow.start();
        });

        this.addEmployeeWindow.chooseComboBox(e -> {
            int index = this.addEmployeeWindow.getSelectedIndex();
            switch(index){
                case 1:
                    this.addEmployeeWindow.setVariableElement("Опыт работы (годы)", 1);
                    break;
                case 2:
                    this.addEmployeeWindow.setVariableElement("Выполняемая работа", 2);
            }
        });

        this.addEmployeeWindow.addListenerOKButton(e -> {
            String name = this.addEmployeeWindow.getName();
            String duty = this.addEmployeeWindow.getDuty();
            int salary = this.addEmployeeWindow.getSalary();
            int experience = this.addEmployeeWindow.getExperience();
            int index = this.addEmployeeWindow.getSelectedIndex();

            if(name.isEmpty()){
                JOptionPane.showMessageDialog(this.addEmployeeWindow, "Вы не ввели имя", "Ошибка", 2);
            }
            else{
                switch (index) {
                    case 0:
                        company.Hire(new Administration(name, salary));
                        break;
                    case 1:
                        company.Hire(new Engineer(name, salary, experience));
                        break;
                    case 2:
                        if(!duty.isEmpty()){
                            company.Hire(new Worker(name, salary, duty));
                        }
                        else{
                            JOptionPane.showMessageDialog(this.addEmployeeWindow, "Вы не ввели работу", "Ошибка", 2);
                        }
                        break;
                    default:
                        break;
                }
                tableModel.fireTableDataChanged();
            }
            this.addEmployeeWindow.close();
        });

        this.mainWindow.addListenerRemoveButton(e -> {
            int index = this.mainWindow.getSelectedIndex();
            company.Dismiss(index);
            tableModel.fireTableDataChanged();
        });

        this.mainWindow.addListenerInfoButton(e -> {
            int index = this.mainWindow.getSelectedIndex();
            String work = company.get(index).DoWork();
            JOptionPane.showMessageDialog(this.mainWindow, work, "О сотруднике", 1);
        });

        this.mainWindow.addListenerTable(e -> {
            if(this.mainWindow.getSelectedIndex() > -1){
                this.mainWindow.makeInfoEnabled(true);
                this.mainWindow.makeRemoveEnabled(true);
            }
            else{
                this.mainWindow.makeInfoEnabled(false);
                this.mainWindow.makeRemoveEnabled(false);
            }
        });
    }

}
