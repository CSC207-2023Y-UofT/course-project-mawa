import java.util.ArrayList;

public class HRDataBaseInteractor implements Interactor{

  private static SessionFactory factory;
  public ArrayList<HR> readData(){
        List HRList = new ArrayList<HR>
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List hrs = session.createQuery("FROM HR").list();
            for (Iterator iterator = hrs.iterator(); iterator.hasNext();){
                HR hr = (HR) iterator.next();
                HRList.add(hr)
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return HRList;


  public void writeData(Hr hr){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(hr, hr.getUserNum());
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
  
}
