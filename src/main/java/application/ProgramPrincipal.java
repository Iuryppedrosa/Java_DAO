package application;


import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProgramPrincipal {
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("\n++++ TEST 1 ++++ SELLER findById \n");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller.toString());


        System.out.println("\n++++ TEST 2 ++++ SELLER findDepartmentById \n");
        Department department = new Department(2 , null);
        List<Seller> list = sellerDao.findAllByDepartment(department);

        for (Seller seller1 : list) {
            System.out.println(seller1.toString());
        }

        System.out.println("\n++++ TEST 3 ++++ SELLER Find All \n");
        List<Seller> sellers = sellerDao.findAll();

        for (Seller seller1 : sellers) {
            System.out.println(seller1.toString());
        }

        System.out.println("\n++++ TEST 4 ++++ SELLER Insert \n");
        Department department1 = new Department(4 , null);
        Seller seller1 = new Seller(9, "John", "Doe", new java.sql.Date(sdf.parse("12/09/1996").getTime()), 4500.0, department);

        sellerDao.insert(seller1);
        System.out.println(seller1.toString());


        System.out.println("\n++++ TEST 5 ++++ SELLER Update \n");
        Department department2 = new Department(4,null);
        Seller seller2 = new Seller(9, "John", "Doe", new java.sql.Date(sdf.parse("12/09/1996").getTime()), 500.0, department1);
        sellerDao.update(seller2);
        System.out.println(seller2.toString());

        System.out.println("\n++++ TEST 5 ++++ SELLER DELETE     \n");
        System.out.println("Qual id vc deseja excluir um vendedor?");
        int choice = scanner.nextInt();

        sellerDao.deleteById(choice);
        System.out.println("Excluido com sucesso!");


    }
}
