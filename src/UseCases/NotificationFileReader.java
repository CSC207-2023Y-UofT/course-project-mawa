package UseCases;

import InterfaceAdapters.NotificationProcessorConstants;
import UseCases.NotificationFileProcessor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class NotificationFileReader{
    private ArrayList listHM;
    private NotificationFileProcessor processor = NotificationFileProcessor.getInstance();

    public NotificationFileReader() {
        listHM = processor.getHMList();
    }

    public ArrayList<Integer> getIds(LocalDateTime date){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.DATE_CREATED);
        return (ArrayList<Integer>)hm.get(date);
    }

    public ArrayList<Integer> getIdsBySender(int id){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.SENDER_ID);
        return (ArrayList<Integer>)hm.get(id);
    }

    public ArrayList<Integer> getIdsByRecipient(int id){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.RECIPIENT_ID);
        return (ArrayList<Integer>)hm.get(id);
    }

    public ArrayList<Integer> getIds(boolean resolved){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.RESOLVED);
        return (ArrayList<Integer>)hm.get(resolved);
    }

    public ArrayList<Integer> getIds(String type){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.TYPE);
        return (ArrayList<Integer>)hm.get(type);
    }

    public String getType(int id){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.ID);
        return (String)((ArrayList)hm.get(id)).get(NotificationProcessorConstants.TYPE);
    }

    public boolean getResolved(int id){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.ID);
        return (boolean)((ArrayList)hm.get(id)).get(NotificationProcessorConstants.RESOLVED);
    }
    public LocalDateTime getDateCreated(int id){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.ID);
        return (LocalDateTime) ((ArrayList)hm.get(id)).get(NotificationProcessorConstants.DATE_CREATED);
    }

    public int getRecipientId(int id){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.ID);
        return (int)((ArrayList)hm.get(id)).get(NotificationProcessorConstants.RECIPIENT_ID);
    }

    public int getSenderId(int id){
        HashMap hm = (HashMap) listHM.get(NotificationProcessorConstants.ID);
        return (int)((ArrayList)hm.get(id)).get(NotificationProcessorConstants.SENDER_ID);
    }


}
