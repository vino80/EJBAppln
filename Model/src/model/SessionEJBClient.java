package model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SessionEJBClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            SessionEJB sessionEJB = (SessionEJB)context.lookup("EJBTrialAppln-Model-SessionEJB#model.SessionEJB");
            for (Employees employees : (List<Employees>)sessionEJB.getEmployeesFindAll()) {
                printEmployees(employees);
            }

            for (Employees employees : (List<Employees>)sessionEJB.getEmployeesFindByDept( 120 )) {
                printEmployees(employees);
            }
            for (Employees employees : (List<Employees>)sessionEJB.getEmployeesFindByName( "A%" )) {
                printEmployees(employees);
            }
            for (Departments departments : (List<Departments>)sessionEJB.getDepartmentsFindAll()) {
                printDepartments(departments);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printEmployees(Employees employees) {
        System.out.println( "commissionPct = " + employees.getCommissionPct() );
        System.out.println( "email = " + employees.getEmail() );
        System.out.println( "employeeId = " + employees.getEmployeeId() );
        System.out.println( "firstName = " + employees.getFirstName() );
        System.out.println( "hireDate = " + employees.getHireDate() );
        System.out.println( "jobId = " + employees.getJobId() );
        System.out.println( "lastName = " + employees.getLastName() );
        System.out.println( "phoneNumber = " + employees.getPhoneNumber() );
        System.out.println( "salary = " + employees.getSalary() );
        System.out.println( "employees = " + employees.getEmployees() );
        System.out.println( "employeesList = " + employees.getEmployeesList() );
        System.out.println( "departmentsList = " + employees.getDepartmentsList() );
        System.out.println( "departments = " + employees.getDepartments() );
    }

    private static void printDepartments(Departments departments) {
        System.out.println( "departmentId = " + departments.getDepartmentId() );
        System.out.println( "departmentName = " + departments.getDepartmentName() );
        System.out.println( "locationId = " + departments.getLocationId() );
        System.out.println( "employees = " + departments.getEmployees() );
        System.out.println( "employeesList = " + departments.getEmployeesList() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://localhost:7101");
        return new InitialContext( env );
    }
}
