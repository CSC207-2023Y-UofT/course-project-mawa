package InterfaceAdapters;

import UseCases.*;

public class FileHandler {
    private String fileName;
    private Interactor interactor;

    FileHandler(String fileName){
        this.fileName = fileName;

    }

    public void setStrategy() throws InvalidFileNameException {
        if (fileName.equals(FileNameConstants.SHIFT_FILE_NAME)){
            interactor = new ShiftInteractor();
        } else if (fileName.equals(FileNameConstants.USER_FILE_NAME)){
            interactor = new UserInteractor();
        } else if (fileName.equals(FileNameConstants.NOTIFICATION_FILE_NAME)){
            interactor = new UserNotificationInteractor();
        } else if (fileName.equals(FileNameConstants.PAYMENT_FILE_NAME)){
            interactor = new PaymentInteractor();
        } else {
            throw new InvalidFileNameException();
        }
    }
}
