package gr.cf.java.app.core.serializer;

import gr.cf.java.app.dto.MobileContactReadOnlyDTO;

public class Serializer {

    public Serializer() {
    }

    public static String serializeDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Όνομα: " + readOnlyDTO.getFirstname()
                + ", Επώνυμο: " + readOnlyDTO.getLastname() + ", Τηλ. Αριθμός: " + readOnlyDTO.getPhoneNumber();
    }
}
