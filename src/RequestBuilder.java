public class RequestBuilder{

    public Notification createRequest(Shift[] shifts, LocalDate[] days, String message, User sender, User reciever){
        text = sender.name;
        if (len(shifts) == 0){
            text += ' is requesting the following days off: ';
            text += days[0].toString();
            for (i = 1, i < len(days), i++){
                text += ', ' + days[i].toString();
            }
        } else if (len(days) == 0) {
            text += ' is requesting the following shifts off: ';
            text += shifts[0].time.toString();
            for (i = 1, i < len(days), i++){
                text += ', ' + shifts[i].time.toString();
            }
        } else{
            text += ' is requesting the following days off: ';
            text += days[0].toString();
            for (i = 1, i < len(days), i++){
                text += ', ' + days[i].toString();
            }
            text += ', and the following shifts off: ';
            text += shifts[0].time.toString();
            for (i = 1, i < len(days), i++){
                text += ', ' + shifts[i].time.toString();
            }
        }
        text += '. ' + sender.name + ' wrote: ' + message
        return new RequestNotification(text, sender, reciever, LocalDateTime.now(), shifts, days)
    }

}
