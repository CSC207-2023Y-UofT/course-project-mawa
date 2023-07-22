import java.util.Interactor;

public class EmployeeDataBaseInteractor implements Interactor{

    private static SessionFactory factory
    public ArrayList<Employee> readData(){
        List empList = new ArrayList<Employee>
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Volunteer").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                Volunteer employee = (Volunteer) iterator.next();
                empList.add(employee)
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM SalariedEmployee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                SalariedEmployee employee = (SalariedEmployee) iterator.next();
                empList.add(employee)
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM WageWorker").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                WageWorker employee = (WageWorker) iterator.next();
                empList.add(employee)
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return empList;

    }

    public void updateActivationVolunteer(Integer EmployeeID, Boolean status){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Volunteer employee = (Volunteer)session.get(Volunteer.class, EmployeeID);
            employee.setActive(status);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateActivationSalariedEmployee(Integer EmployeeID, Boolean status){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SalariedEmployee employee = (SalariedEmployee)session.get(SalariedEmployee.class, EmployeeID);
            employee.setActive(status);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateActivationSalariedEmployee(Integer EmployeeID, Boolean status){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            WageWorker employee = (WageWorker)session.get(WageWorker.class, EmployeeID);
            employee.setActive(status);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateActivation(Integer EmployeeID, Boolean status){
        employees = this.readData();
        for (Employee employee: employees){
            if (employee.employeeNum == EmployeeID){
                if (employee instanceof Volunteer){
                    this.updateActivationVolunteer(Integer EmployeeID, Boolean status);
              } else if (employee instanceof WageWorker){
                    this.updateActivationWageWorker(Integer EmployeeID, Boolean status);
              } else{
                    this.updateActivationSalariedEmployee(Integer EmployeeID, Boolean status);
            }
        }
      
    }


    public void writeData(Employee employee){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(employee, employee.employeeNum);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}

