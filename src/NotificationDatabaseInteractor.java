import java.util.Interactor;

public class NotificationDatabaseInteractor implements Interactor{

  private static SessionFactory factory;

   public ArrayList<Notification> readData(){
        List notifList = new ArrayList<Notification>
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List notifs = session.createQuery("FROM NotificationRequest").list();
            for (Iterator iterator = notifs.iterator(); iterator.hasNext();){
                NotificationRequest notif = (NotificationRequest) iterator.next();
                notifList.add(notif)
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
            List notifs = session.createQuery("FROM NotificationResponse").list();
            for (Iterator iterator = notifs.iterator(); iterator.hasNext();){
                NotificationResponse notif = (NotificationResponse) iterator.next();
                notifList.add(notif)
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
     
        return notifList;

  }

  public void resolveRequest(Integer notifId){

    Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            NotificationRequest notif = (NotificationRequest)session.get(NotificationRequest.class, notifId);
            notif.resolve();
            session.update(notif);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    
  }

  public void resolveResponse(Integer notifId){

    Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            NotificationResponse notif = (NotificationResponse)session.get(NotificationReseponse.class, notifId);
            notif.resolve();
            session.update(notif);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    
  }


  public void writeData(Notification notification){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(notification, notification.getnotifId());
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
  
}
