package InterfaceAdapters;

import UseCases.*;

public class FileHandler {
    private String fileName;
    private FileProcessor processor;

    public FileHandler(String fileName){
        this.fileName = fileName;
        setStrategy();

    }

    public void setStrategy(){
        if (fileName.equals(FileNameConstants.SHIFT_FILE_NAME)){
            processor = ShiftFileProcessor.getInstance(); //maybe make filename parameter for interactors?
        } else if (fileName.equals(FileNameConstants.USER_FILE_NAME)){
            processor = UserFileProcessor.getInstance();
        } else if (fileName.equals(FileNameConstants.NOTIFICATION_FILE_NAME)){
            processor = NotificationFileProcessor.getInstance();
        } else if (fileName.equals(FileNameConstants.PAYMENT_FILE_NAME)) {
            processor = PaymentFileProcessor.getInstance();
        }
    }

    public FileProcessor getStrategy(){
        return processor;
    }
}
