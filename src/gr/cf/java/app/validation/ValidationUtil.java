package gr.cf.java.app.validation;

import gr.cf.java.app.dto.MobileContactInsertDTO;
import gr.cf.java.app.dto.MobileContactUpdateDTO;

public class ValidationUtil {

    private ValidationUtil() {
        // Private constructor to prevent instantiation
    }

    public static String validateDTO(MobileContactInsertDTO insertDTO){
        String errorResponse = "";

        if (insertDTO.getPhoneNumber().trim().length() <= 5) errorResponse += "phone length must be greater than 5 symbols\n";
        if (insertDTO.getFirstname().trim().length() < 2) errorResponse += "firstname length must be greater-equal than 2 symbols\n";
        if (insertDTO.getLastname().trim().length() < 2) errorResponse += "lastname length must be greater-equal than 2 symbols\n";
        return errorResponse;
    }

    public static String validateDTO(MobileContactUpdateDTO updateDTO){
        String errorResponse = "";

        if (updateDTO.getPhoneNumber().trim().length() < 5) errorResponse += "phone length must be greater than 5 symbols\n";
        if (updateDTO.getFirstname().trim().length() < 2) errorResponse += "firstname length must be greater-equal than 2 symbols\n";
        if (updateDTO.getLastname().trim().length() < 2) errorResponse += "lastname length must be greater-equal than 2 symbols\n";
        return errorResponse;
    }
}
