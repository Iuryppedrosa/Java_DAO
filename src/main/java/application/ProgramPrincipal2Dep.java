package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class ProgramPrincipal2Dep {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DepartmentDao dao = DaoFactory.createDepartmentDao();
        //Department department = new Department(null, "Food");
        //System.out.println("\n++++ TEST 1 ++++ DEPARTMENT Insert \n");
        //dao.insert(department);
        //System.out.println("Exito: " + department.getName());

        //System.out.println("\n++++ TEST 2 ++++ DEPARTMENT Update \n");
        //Department department = new Department(2, "Coffee");
        //dao.update(department);
        //System.out.println("Exito em update: " + department.getName());

        //System.out.println("\n++++ TEST 3 ++++ DEPARTMENT Delete \n");
        //System.out.print("Qual ID vc deseja excluir da tabela DEPARTMENT? ");
        //int choice = scanner.nextInt();
        //dao.deleteById(choice);
        //System.out.println("Excluido com sucesso!");


//        System.out.println("\n++++ TEST 3 ++++ DEPARTMENT FindById \n");
//        Department department2 = dao.findById(3);
//        System.out.println("Exito em findById: " + department2.toString());


        System.out.println("\n++++ TEST 4 ++++ DEPARTMENT FindAll \n");

        List<Department> departments = dao.findAll();
        for (Department department : departments) {
            System.out.println(department.toString());
        }

    }
}
