package gr.cf.java.app.dao;

import gr.cf.java.app.model.MobileContact;

import java.util.ArrayList;
import java.util.List;

public class IMobileContactDAOImpl implements IMobileContactDAO {
    private static Long id = 1L;
    private static final List<MobileContact> contacts = new ArrayList<>();

    @Override
    public MobileContact insert(MobileContact mobileContact) {
        mobileContact.setId(id++);
        contacts.add(mobileContact);
        return mobileContact;
    }

    @Override
    public MobileContact update(Long id, MobileContact mobileContact) {
        contacts.set(getIndexById(id), mobileContact);
        return mobileContact;
    }

    @Override
    public void deleteById(Long id) {
        //contacts.remove(getIndexById(id));
        contacts.removeIf(contact -> contact.getId().equals(id));
        //Here we use a lambda expression to remove the contact with the specified id, equals is used
        //to compare the id of the contact with the id passed as a parameter
    }

    /**
     * Επιστρέφει ένα mobile contact με βάση το id του.Μπορεί να υλοποιηθεί με την getIndexById και να επιστρέψει
     * το instance που αντιστοιχεί στο index αλλιώς null, είτε μπορεί να γίνει με τη λογική των streams
     * @param id   το id του contact που θέλουμε να επιστρέψουμε
     * @return     το contact που αντιστοιχεί στο id ή null αν δεν υπάρχει
     */
    @Override
    public MobileContact getById(Long id) {
        int position = getIndexById(id);
        return (position != -1) ? contacts.get(position) : null;

        //return contacts.stream()
        //              .filter(contact -> contact.getId().equals(id))
        //              .findFirst()
        //              .orElse(null);
    }

    /**
     * Επιστρέφει ένα fresh copy της λίστας με τα contacts
     * @return  η λίστα με τα contacts
     */
    @Override
    public List<MobileContact> getAll() {
        return new ArrayList<>(contacts);
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }

    /**
     * Επιστρέφει ένα mobile contact με βάση το phone number του.Μπορεί να υλοποιηθεί με την getIndexByPhoneNumber
     * και να επιστρέψει το instance που αντιστοιχεί στο index
     * αλλιώς null, είτε μπορεί να γίνει με τη λογική των streams
     * @param phoneNumber   το phone number του contact που θέλουμε να επιστρέψουμε
     * @return             το contact που αντιστοιχεί στο phone number ή null αν δεν υπάρχει
     */
    @Override
    public MobileContact getByPhoneNumber(String phoneNumber) {
        int position = getIndexByPhoneNumber(phoneNumber);
        return (position != -1) ? contacts.get(position) : null;

        //return contacts.stream()
        //              .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
        //              .findFirst()
        //              .orElse(null);
    }

    /**
     * Επιστρέφει true αν υπάρχει το id στη λίστα με τα contacts
     * @param id   το id του contact που θέλουμε να ελέγξουμε αν υπάρχει
     * @return     true αν υπάρχει το id, false αλλιώς
     */
    @Override
    public boolean userIdExists(Long id) {
        int position = getIndexById(id);
        return position != -1;

        //return contacts.stream().anyMatch(c -> c.getId().equals(id));
    }


    /**
     * Επιστρέφει true αν υπάρχει το phone number στη λίστα με τα contacts
     * @param phoneNumber   το phone number του contact που θέλουμε να ελέγξουμε αν υπάρχει
     * @return             true αν υπάρχει το phone number, false αλλιώς
     */
    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        int position = getIndexByPhoneNumber(phoneNumber);
        return position != -1;

        //return contacts.stream().anyMatch(c -> c.getPhoneNumber().equals(phoneNumber));
    }


    /**
     * Επιστρέφει το index του contact με το id που δώσαμε
     * @param id   το id του contact που θέλουμε να επιστρέψουμε
     * @return     το index του contact ή -1 αν δεν υπάρχει
     */
    private int getIndexById(Long id) {
        int positionToReturn = -1;

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                //return i;
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
        //return -1;
    }

    /**
     * Επιστρέφει το index του contact με το phone number που δώσαμε
     * @param phoneNumber   το phone number του contact που θέλουμε να επιστρέψουμε
     * @return             το index του contact ή -1 αν δεν υπάρχει
     */
    private int getIndexByPhoneNumber(String phoneNumber) {
        int positionToReturn = -1;

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                //return i;
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
        //return -1;
    }

}
