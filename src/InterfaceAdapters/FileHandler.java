package InterfaceAdapters;

import UseCases.*;

public class FileHandler {
    private String fileName;
    private FileProcessor processor;

    public FileHandler() throws InvalidFileNameException {
        setStrategy();

    }

    public void setStrategy() throws InvalidFileNameException {
        if (fileName.equals(FileNameConstants.SHIFT_FILE_NAME)){
            processor = ShiftFileProcessor.getInstance();
        } else if (fileName.equals(FileNameConstants.USER_FILE_NAME)){
            processor = UserFileProcessor.getInstance();
        } else if (fileName.equals(FileNameConstants.NOTIFICATION_FILE_NAME)){
            processor = NotificationFileProcessor.getInstance();
        } else if (fileName.equals(FileNameConstants.PAYMENT_FILE_NAME)){
            processor = PaymentFileProcessor.getInstance();
        } else {
            throw new InvalidFileNameException();
        }
    }

    public FileProcessor getStrategy(){
        return processor;
    }
}
