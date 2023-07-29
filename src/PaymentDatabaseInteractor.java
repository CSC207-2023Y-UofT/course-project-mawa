import java.util.Interactor;

public class PaymentDatabaseInteractor implements Interactor{
  
  private static SessionFactory factory

  public ArrayList<Payment> readData(){
        List payList = new ArrayList<Payment>
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List payments = session.createQuery("FROM Payment").list();
            for (Iterator iterator = payments.iterator(); iterator.hasNext();){
                Payment payment = (Payment) iterator.next();
                payList.add(payment)
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return payList;

  public void writeData(Payment payment){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(payment, payment.paymentNum);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}
