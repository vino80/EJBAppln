package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OutsideFacade {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Model-1");

    public OutsideFacade() {
    }

    public static void main(String [] args) {
        final OutsideFacade outsideFacade = new OutsideFacade();
        //  TODO:  Call methods on outsideFacade here...
        Employees a = outsideFacade.getEmployeesFindByName("P%").get(0);
        System.out.println(a.getLastName());
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = getEntityManager().createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    private Object _persistEntity(Object entity) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                em.persist(entity);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    entity = null;
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return entity;
    }

    public Employees persistEmployees(Employees employees) {
        return (Employees)_persistEntity(employees);
    }

    private Object _mergeEntity(Object entity) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                em.merge(entity);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    entity = null;
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return entity;
    }

    public Employees mergeEmployees(Employees employees) {
        return (Employees)_mergeEntity(employees);
    }

    public void removeEmployees(Employees employees) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                employees = em.find(Employees.class, employees.getEmployeeId());
                em.remove(employees);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Employees o</code> */
    public List<Employees> getEmployeesFindAll() {
        return getEntityManager().createNamedQuery("Employees.findAll").getResultList();
    }

    /** <code>select o from Employees o where o.departments.departmentId= :theDept</code> */
    public List<Employees> getEmployeesFindByDept(Object theDept) {
        return getEntityManager().createNamedQuery("Employees.findByDept").setParameter("theDept", theDept).getResultList();
    }

    /** <code>select o from Employees o where o.firstName like :p_name</code> */
    public List<Employees> getEmployeesFindByName(Object p_name) {
        return getEntityManager().createNamedQuery("Employees.findByName").setParameter("p_name", p_name).getResultList();
    }

    public Departments persistDepartments(Departments departments) {
        return (Departments)_persistEntity(departments);
    }

    public Departments mergeDepartments(Departments departments) {
        return (Departments)_mergeEntity(departments);
    }

    public void removeDepartments(Departments departments) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                departments = em.find(Departments.class, departments.getDepartmentId());
                em.remove(departments);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Departments o</code> */
    public List<Departments> getDepartmentsFindAll() {
        return getEntityManager().createNamedQuery("Departments.findAll").getResultList();
    }
}
