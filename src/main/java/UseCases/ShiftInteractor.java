package UseCases;

import Entities.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The ShiftInteractor class manages reading, writing, updating, and retrieving shift data.
 * It implements the Interactor interface for shift objects.
 */
public class ShiftInteractor implements Interactor<Shift> {

    private String fileName; // Stores the name of the shift data file.

    /**
     * Default constructor that initializes the ShiftInteractor with the default shift data file name.
     */
    public ShiftInteractor() {
        this.fileName = FileNameConstants.SHIFT_FILE_NAME;
    }

    /**
     * Constructor that specifies the file name to be that of testing data.
     *
     * @param isTest A string identifier indicating the use of a test shift data file.
     */
    public ShiftInteractor(String isTest) {
        this.fileName = "testShifts.ser";
    }

    /**
     * Reads shift data from the shift data file.
     *
     * @return An ArrayList of Shift objects read from the file.
     */
    public ArrayList<Shift> readData() {
        ArrayList<Shift> shiftList = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            shiftList.addAll((ArrayList<Shift>) input.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return shiftList;
    }

    /**
     * Retrieves a shift by its unique shift ID.
     *
     * @param shiftID The ID of the shift to retrieve.
     * @return The Shift object corresponding to the given shift ID, or null if not found.
     */
    public Shift getShiftByID(int shiftID) {
        ArrayList<Shift> shifts = this.readData();
        for (Shift shift : shifts) {
            if (shift.getShiftId() == shiftID) {
                return shift;
            }
        }
        return null;
    }

    /**
     * Updates an existing shift in the shift data file.
     *
     * @param s The updated Shift object.
     */
    @Override
    public void update(Shift s) {
        ArrayList<Shift> shifts = this.readData();
        shifts.removeIf(shift -> s.getShiftId() == shift.getShiftId());
        shifts.add(s);

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(shifts);
            output.close();
            ShiftFileReader.getInstance().update();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Writes a new shift object to the shift data file.
     *
     * @param shift The Shift object to be written to the file.
     */
    public void writeData(Shift shift) {
        ArrayList<Shift> shiftList = this.readData();
        shiftList.add(shift);

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(shiftList);
            output.close();
            ShiftFileReader.getInstance().update();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
