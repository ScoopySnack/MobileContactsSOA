package gr.cf.java.app.controller;

import gr.cf.java.app.core.serializer.Serializer;
import gr.cf.java.app.dao.IMobileContactDAO;
import gr.cf.java.app.dao.IMobileContactDAOImpl;
import gr.cf.java.app.dto.MobileContactInsertDTO;
import gr.cf.java.app.dto.MobileContactReadOnlyDTO;
import gr.cf.java.app.dto.MobileContactUpdateDTO;
import gr.cf.java.app.exceptions.MobileContactNotFoundException;
import gr.cf.java.app.exceptions.PhoneNumberAlreadyExistsException;
import gr.cf.java.app.mapper.Mapper;
import gr.cf.java.app.model.MobileContact;
import gr.cf.java.app.service.IMobileContactService;
import gr.cf.java.app.service.MobileContactServiceImpl;
import gr.cf.java.app.validation.ValidationUtil;


import java.util.ArrayList;
import java.util.List;

import static gr.cf.java.app.core.serializer.Serializer.serializeDTO;
import static gr.cf.java.app.mapper.Mapper.mapMobileContactToDTO;

public class MobileContactController {

    IMobileContactDAO dao = new IMobileContactDAOImpl();
    IMobileContactService service = new MobileContactServiceImpl(dao);

    public String insertContact(MobileContactInsertDTO mobileContactInsertDTO) {

        try {

            //Validating input data, otherwise return error message
            String error = ValidationUtil.validateDTO(mobileContactInsertDTO);
            if (error.isEmpty()) return "Error. Validation Error\n" + error;

            MobileContact mobileContact = service.insertMobileContact(mobileContactInsertDTO);
            MobileContactReadOnlyDTO readOnlyDTO = mapMobileContactToDTO(mobileContact);

            return "OK\n" + serializeDTO(readOnlyDTO) + "\n";
        }catch (MobileContactNotFoundException e){
            return "Error during update. Contact not found\n";
        }catch (PhoneNumberAlreadyExistsException e){
            return "Error during update. Phone number already exists\n";
        }
    }

    public String updateContact(MobileContactUpdateDTO updateDTO)  {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;

        try {
            // Validate input data
            String errorVector = ValidationUtil.validateDTO(updateDTO);
            if (!errorVector.isEmpty()) {
                return "Error.\n" + "Validation error\n" + errorVector;
            }

            // If validation is ok, insert contact
            mobileContact = service.updateMobileContact(updateDTO);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK\n" + Serializer.serializeDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException | MobileContactNotFoundException e) {
            return "Error.\n" + e.getMessage() + "\n";
        }
    }

    public String deleteContactById(Long id) {
        try {
            service.deleteContactById(id);
            return "OK. Contact deleted\n";
        } catch (MobileContactNotFoundException e) {
            return "Error during delete. Contact not found\n";
        }
    }

    public String getContactById(Long id) {
        try {
            MobileContact mobileContact = service.getContactById(id);
            MobileContactReadOnlyDTO readOnlyDTO = mapMobileContactToDTO(mobileContact);
            return "OK\n" + serializeDTO(readOnlyDTO);
        } catch (MobileContactNotFoundException e) {
            return "Error during get. Contact not found\n";
        }
    }

    public List<String>getAllContacts() {
        List<MobileContact> contacts;
        List<String> serializedList = new ArrayList<>();

        contacts = service.getAllContacts();

        for (MobileContact contact : contacts) {
            String serialized = serializeDTO(mapMobileContactToDTO(contact));
            serializedList.add(serialized);
        }
        return serializedList;

        //return contacts.stream().map(this::mapMobileContactToDTO).map(this::serializeDTO).collect(Collectors.toList());

    }

    public String getContactByPhoneNumber(String phoneNumber) {
        try {
            MobileContact mobileContact = service.getContactByPhoneNumber(phoneNumber);
            MobileContactReadOnlyDTO readOnlyDTO = mapMobileContactToDTO(mobileContact);
            return "OK\n" + serializeDTO(readOnlyDTO);
        } catch (MobileContactNotFoundException e) {
            return "Error in fetching. Contact not found\n";
        }
    }

    public String deleteContact(String phoneNumber){
        try {
            MobileContact mobileContact = service.getContactByPhoneNumber(phoneNumber);
            MobileContactReadOnlyDTO readOnlyDTO = mapMobileContactToDTO(mobileContact);
            service.deleteContactByPhoneNumber(phoneNumber);
            return "OK\n" + serializeDTO(readOnlyDTO) + "\n";
        }catch (MobileContactNotFoundException e) {
            return "Error in deleting. Contact not found\n";
        }
    }
}
