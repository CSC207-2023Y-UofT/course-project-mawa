import java.util.Interactor;

public class ShiftDatabaseInteractor implements Interactor{

  private static SessionFactory factory;
  
  public ArrayList<Shift> readData(){
        List shiftList = new ArrayList<Shift>
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List shifts = session.createQuery("FROM Shift").list();
            for (Iterator iterator = shifts.iterator(); iterator.hasNext();){
                Shift shift = (Shift) iterator.next();
                shiftList.add(shift)
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return shiftList;

  }

   public void writeData(Shift shift){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(shift, shift.shiftNum);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
  
}
