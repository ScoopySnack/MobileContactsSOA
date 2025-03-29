package gr.cf.java.app.service;

import gr.cf.java.app.dto.MobileContactInsertDTO;
import gr.cf.java.app.dto.MobileContactUpdateDTO;
import gr.cf.java.app.exceptions.MobileContactNotFoundException;
import gr.cf.java.app.exceptions.PhoneNumberAlreadyExistsException;
import gr.cf.java.app.model.MobileContact;

import java.util.List;

public interface IMobileContactService {

    MobileContact insertMobileContact(MobileContactInsertDTO mobileContactInsertDTO)
            throws PhoneNumberAlreadyExistsException;

    MobileContact updateMobileContact(MobileContactUpdateDTO mobileContactUpdateDTO)
            throws PhoneNumberAlreadyExistsException, MobileContactNotFoundException;

    void deleteContactById(long id)
            throws MobileContactNotFoundException;

    MobileContact getContactById(long id)
            throws MobileContactNotFoundException;

    List<MobileContact> getAllContacts();

    MobileContact getContactByPhoneNumber(String phoneNumber)
            throws MobileContactNotFoundException;

    void deleteContactByPhoneNumber(String phoneNumber)
            throws MobileContactNotFoundException;

}
