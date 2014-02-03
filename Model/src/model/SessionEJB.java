package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface SessionEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Employees persistEmployees(Employees employees);

    Employees mergeEmployees(Employees employees);

    void removeEmployees(Employees employees);

    List<Employees> getEmployeesFindAll();

    List<Employees> getEmployeesFindByDept(Object theDept);

    Departments persistDepartments(Departments departments);

    Departments mergeDepartments(Departments departments);

    void removeDepartments(Departments departments);

    List<Departments> getDepartmentsFindAll();

    List<Employees> getEmployeesFindByName(Object p_name);
}
