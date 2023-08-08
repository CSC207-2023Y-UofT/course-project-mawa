package UseCases;

import Entities.UserNotification;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.*;

public class NotificationFileProcessor implements FileProcessor<UserNotification> {
    private static NotificationFileProcessor instance;
    private UserNotificationInteractor interactor = new UserNotificationInteractor();
    private HashMap<Integer, ArrayList<Object>> idToList = new HashMap<Integer, ArrayList<Object>>();
    private HashMap<Integer, ArrayList<Integer>> senderIdToId = new HashMap<Integer, ArrayList<Integer>>();
    private HashMap<Integer, ArrayList<Integer>> recipientIdToId = new HashMap<Integer, ArrayList<Integer>>();
    private HashMap<LocalDateTime, ArrayList<Integer>> dateCreatedToId = new HashMap<LocalDateTime, ArrayList<Integer>>();
    private HashMap<Boolean, ArrayList<Integer>> resolvedToId = new HashMap<Boolean, ArrayList<Integer>>();
    private HashMap<String, ArrayList<Integer>> typeToId = new HashMap<String, ArrayList<Integer>>();
    private ArrayList<HashMap> hmList = new ArrayList<>();


    private NotificationFileProcessor(){
        makeHM();
    }

    public static NotificationFileProcessor getInstance(){
        if (instance == null) {
            synchronized (NotificationFileProcessor.class) {
                if (instance == null) {
                    instance = new NotificationFileProcessor();
                }
            }
        }
        return instance;
    }
    @Override
    public void makeHM() {
        ArrayList<UserNotification> notifList = interactor.readData();

        makeIdtoList(idToList, notifList);
        hmList.add(idToList);
        makeSenderIdtoId(senderIdToId,notifList);
        hmList.add(senderIdToId);
        makeRecIdtoId(recipientIdToId, notifList);
        hmList.add(recipientIdToId);
        makeDatetoId(dateCreatedToId, notifList);
        hmList.add(dateCreatedToId);
        makeRestoId(resolvedToId, notifList);
        hmList.add(resolvedToId);
        makeTypetoId(typeToId, notifList);
        hmList.add(typeToId);
    }

    public void makeTypetoId(HashMap<String, ArrayList<Integer>> typeToid,
                            ArrayList<UserNotification> notifList){
        for (UserNotification n : notifList){
            if (typeToid.containsKey(n.getClass().getName())){
                typeToid.get(n.getClass().getName()).add(n.getNotifId());
            } else {
                typeToid.put(n.getClass().getName(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }
        }
    }

    public void makeTypetoId(HashMap<String, ArrayList<Integer>> typeToid,
                             ArrayList<UserNotification> notifList, boolean append){
        if(!append){
            typeToid.clear();
        }
        for (UserNotification n : notifList){
            if (typeToid.containsKey(n.getClass().getName())){
                typeToid.get(n.getClass().getName()).add(n.getNotifId());
            } else {
                typeToid.put(n.getClass().getName(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }
        }
    }

    public void makeRestoId(HashMap<Boolean, ArrayList<Integer>> resToid,
                             ArrayList<UserNotification> notifList){
        for (UserNotification n : notifList){
            if (resToid.containsKey(n.getResolvedStatus())){
                resToid.get(n.getResolvedStatus()).add(n.getNotifId());
            } else {
                resToid.put(n.getResolvedStatus(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }
        }
    }

    public void makeRestoId(HashMap<Boolean, ArrayList<Integer>> resToid,
                            ArrayList<UserNotification> notifList, boolean append){
        if(!append){
            resToid.clear();
        }
        for (UserNotification n : notifList){
            if (resToid.containsKey(n.getResolvedStatus())){
                resToid.get(n.getResolvedStatus()).add(n.getNotifId());
            } else {
                resToid.put(n.getResolvedStatus(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }
        }
    }
    public void makeDatetoId(HashMap<LocalDateTime, ArrayList<Integer>> dateToid,
                              ArrayList<UserNotification> notifList){
        for (UserNotification n : notifList){
            if (dateToid.containsKey(n.getDate())){
                dateToid.get(n.getDate()).add(n.getNotifId());
            } else {
                dateToid.put(n.getDate(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }

        }
    }

    public void makeDatetoId(HashMap<LocalDateTime, ArrayList<Integer>> dateToid,
                             ArrayList<UserNotification> notifList, boolean append){
        if (!append){
            dateToid.clear();
        }
        for (UserNotification n : notifList){
            if (dateToid.containsKey(n.getDate())){
                dateToid.get(n.getDate()).add(n.getNotifId());
            } else {
                dateToid.put(n.getDate(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }

        }
    }

    public void makeRecIdtoId(HashMap<Integer, ArrayList<Integer>> idToid,
                                 ArrayList<UserNotification> notifList){
        for (UserNotification n : notifList){
            if (idToid.containsKey(n.getRecipientId())){
                idToid.get(n.getRecipientId()).add(n.getNotifId());
            } else {
                idToid.put(n.getRecipientId(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }

        }
    }

    public void makeRecIdtoId(HashMap<Integer, ArrayList<Integer>> idToid,
                              ArrayList<UserNotification> notifList, boolean append){
        if (!append){
            idToid.clear();
        }
        for (UserNotification n : notifList){
            if (idToid.containsKey(n.getRecipientId())){
                idToid.get(n.getRecipientId()).add(n.getNotifId());
            } else {
                idToid.put(n.getRecipientId(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }

        }
    }
    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<UserNotification> notifList){
        for (UserNotification n : notifList){
            idToList.put(n.getNotifId(), toList(n));
        }
    }
    public void makeSenderIdtoId(HashMap<Integer, ArrayList<Integer>> idToid,
                             ArrayList<UserNotification> notifList){
        for (UserNotification n : notifList){
            if (idToid.containsKey(n.getSenderId())){
                idToid.get(n.getSenderId()).add(n.getNotifId());
            } else {
                idToid.put(n.getSenderId(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }

        }
    }

    public void makeSenderIdtoId(HashMap<Integer, ArrayList<Integer>> idToid,
                                 ArrayList<UserNotification> notifList, boolean append){
        if (!append){
            idToid.clear();
        }
        for (UserNotification n : notifList){
            if (idToid.containsKey(n.getSenderId())){
                idToid.get(n.getSenderId()).add(n.getNotifId());
            } else {
                idToid.put(n.getSenderId(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }

        }
    }

    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<UserNotification> notifList, boolean append){
        if (!append){
            idToList.clear();
        }
        for (UserNotification n : notifList){
            idToList.put(n.getNotifId(), toList(n));
        }
    }

    public ArrayList<Object> toList(UserNotification notification){
        ArrayList<Object> list = new ArrayList<>();
        list.add(notification.getNotifId());
        list.add(notification.getSenderId());
        list.add(notification.getRecipientId());
        list.add(notification.getDate());
        list.add(notification.getResolvedStatus());

        return list;
    }


}
