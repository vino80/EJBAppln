package model;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "SessionEJB", mappedName = "EJBTrialAppln-Model-SessionEJB")
public class SessionEJBBean implements SessionEJB, SessionEJBLocal {
    @PersistenceContext(unitName="Model")
    private EntityManager em;

    public SessionEJBBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Employees persistEmployees(Employees employees) {
        em.persist(employees);
        return employees;
    }

    public Employees mergeEmployees(Employees employees) {
        return em.merge(employees);
    }

    public void removeEmployees(Employees employees) {
        employees = em.find(Employees.class, employees.getEmployeeId());
        em.remove(employees);
    }

    /** <code>select o from Employees o</code> */
    public List<Employees> getEmployeesFindAll() {
        return em.createNamedQuery("Employees.findAll").getResultList();
    }

    /** <code>select o from Employees o where o.departments.departmentId= :theDept</code> */
    public List<Employees> getEmployeesFindByDept(Object theDept) {
        return em.createNamedQuery("Employees.findByDept").setParameter("theDept", theDept).getResultList();
    }

    public Departments persistDepartments(Departments departments) {
        em.persist(departments);
        return departments;
    }

    public Departments mergeDepartments(Departments departments) {
        return em.merge(departments);
    }

    public void removeDepartments(Departments departments) {
        departments = em.find(Departments.class, departments.getDepartmentId());
        em.remove(departments);
    }

    /** <code>select o from Departments o</code> */
    public List<Departments> getDepartmentsFindAll() {
        return em.createNamedQuery("Departments.findAll").getResultList();
    }

    /** <code>select o from Employees o where o.firstName like :p_name</code> */
    public List<Employees> getEmployeesFindByName(Object p_name) {
        return em.createNamedQuery("Employees.findByName").setParameter("p_name", p_name).getResultList();
    }
}
