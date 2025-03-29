package gr.cf.java.app.service;

import gr.cf.java.app.dao.IMobileContactDAO;
import gr.cf.java.app.dto.MobileContactInsertDTO;
import gr.cf.java.app.dto.MobileContactUpdateDTO;
import gr.cf.java.app.exceptions.MobileContactNotFoundException;
import gr.cf.java.app.exceptions.PhoneNumberAlreadyExistsException;
import gr.cf.java.app.model.MobileContact;

import java.util.List;

import static gr.cf.java.app.mapper.Mapper.mapInsertDTOToContact;
import static gr.cf.java.app.mapper.Mapper.mapUpdateDTOToContact;

public class MobileContactServiceImpl implements IMobileContactService{

    private final IMobileContactDAO dao; // Dependency Injection

    public MobileContactServiceImpl(IMobileContactDAO dao) {  // Constructor Injection
        this.dao = dao;
    }

    @Override
    public MobileContact insertMobileContact(MobileContactInsertDTO mobileContactInsertDto) throws PhoneNumberAlreadyExistsException {
        MobileContact mobileContact;

        try {

            // Check if the phone number already exists
            if (dao.phoneNumberExists(mobileContactInsertDto.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException("User with phone number " + mobileContactInsertDto.getPhoneNumber() + " already exists");
            }

            mobileContact = mapInsertDTOToContact(mobileContactInsertDto);  // Mapping DTO to Entity
            System.err.printf("User Logger: %s was inserted. \n", mobileContact);
            return dao.insert(mobileContact);
        }catch (PhoneNumberAlreadyExistsException e) {
            System.err.printf("User with phone number %s already exists. \n", mobileContactInsertDto.getPhoneNumber());
            throw e;
        }
    }

    @Override
    public MobileContact updateMobileContact(MobileContactUpdateDTO updateDTO) throws PhoneNumberAlreadyExistsException, MobileContactNotFoundException {
        MobileContact mobileContact;
        MobileContact newContact;

        try {
            if(dao.userIdExists(updateDTO.getId())) {
                throw new MobileContactNotFoundException("Contact not found");
            }

            mobileContact = dao.getById(updateDTO.getId());
            boolean isPhoneNumberOurOwn = mobileContact.getPhoneNumber().equals(updateDTO.getPhoneNumber());
            boolean isPhoneNumberExists = dao.phoneNumberExists(updateDTO.getPhoneNumber());

            if (isPhoneNumberExists && !isPhoneNumberOurOwn) {
                throw new PhoneNumberAlreadyExistsException("User with phone number " + updateDTO.getPhoneNumber() + " already exists");
            }

            newContact = mapUpdateDTOToContact(updateDTO);
            System.err.printf("User Logger: %s was updated with following info: %s \n", newContact, updateDTO);
            return dao.update(updateDTO.getId(), newContact);
        }catch (PhoneNumberAlreadyExistsException | MobileContactNotFoundException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteContactById(long id) throws MobileContactNotFoundException {
        try {
            if(dao.userIdExists(id)) {
                throw new MobileContactNotFoundException("Contact not found");
            }
            dao.deleteById(id);

            System.err.printf("User Logger: User with id %d was deleted. \n", id);
            dao.deleteById(id);
        }catch (MobileContactNotFoundException e) {
            System.err.printf("User with id %d not found for delete. \n", id);
            throw e;
        }

    }

    @Override
    public MobileContact getContactById(long id) throws MobileContactNotFoundException {
        MobileContact mobileContact;

        try {
            if (dao.userIdExists(id)) {
                throw new MobileContactNotFoundException("Contact not found");
            }
            mobileContact = dao.getById(id);
            System.err.printf("User Logger: User with id %d was found. \n", id);
            return mobileContact;
        }catch (MobileContactNotFoundException e) {
            System.err.printf("User with id %d not found. \n", id);
            throw e;
        }
    }

    @Override
    public List<MobileContact> getAllContacts() {
        return dao.getAll();
    }

    @Override
    public MobileContact getContactByPhoneNumber(String phoneNumber) throws MobileContactNotFoundException {
        try {

            MobileContact mobileContact = dao.getByPhoneNumber(phoneNumber);

            if (mobileContact == null) {
                throw new MobileContactNotFoundException("Contact with phone number " + phoneNumber + " not found");
            }
            System.err.printf("User Logger: User with phone number %s was found. \n", phoneNumber);
            return mobileContact;
        }catch (MobileContactNotFoundException e) {
            System.err.printf("User with phone number %s not found. \n", phoneNumber);
            throw e;
        }
    }

    @Override
    public void deleteContactByPhoneNumber(String phoneNumber) throws MobileContactNotFoundException {
        try {
            if (!dao.phoneNumberExists(phoneNumber)) {
                throw new MobileContactNotFoundException("Contact with phone number " + phoneNumber + " not found");
            }
            dao.deleteByPhoneNumber(phoneNumber);
            System.err.printf("User Logger: User with phone number %s was deleted. \n", phoneNumber);
        }catch (MobileContactNotFoundException e) {
            System.err.printf("User with phone number %s not found for delete. \n", phoneNumber);
            throw e;
        }

    }
}
