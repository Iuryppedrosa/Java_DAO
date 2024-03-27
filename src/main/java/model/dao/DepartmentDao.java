package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department obj); //done
    void update(Department obj); //done
    void deleteById(Integer id); //done
    Department findById(Integer id);

    List<Department> findAll();
}
