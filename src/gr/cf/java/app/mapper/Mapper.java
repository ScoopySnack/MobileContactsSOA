package gr.cf.java.app.mapper;

import gr.cf.java.app.dto.MobileContactInsertDTO;
import gr.cf.java.app.dto.MobileContactReadOnlyDTO;
import gr.cf.java.app.dto.MobileContactUpdateDTO;
import gr.cf.java.app.model.MobileContact;

public class Mapper {

    public Mapper() {
    }

    public static MobileContact mapInsertDTOToContact(MobileContactInsertDTO dto) {
        return new MobileContact(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

    public static MobileContact mapUpdateDTOToContact(MobileContactUpdateDTO dto) {
        return new MobileContact(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

    public static MobileContactReadOnlyDTO mapMobileContactToDTO(MobileContact mobileContact) {
        return new MobileContactReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstname(),
                mobileContact.getLastname(), mobileContact.getPhoneNumber());
    }

    private String serializeDTO(MobileContactReadOnlyDTO dto) {
        return String.format("MobileContactReadOnlyDTO{id=%d, firstname='%s', lastname='%s', phoneNumber='%s'}",
                dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }
}
