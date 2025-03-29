package gr.cf.java.app.dto;

public class MobileContactUpdateDTO extends BaseDTO{
    private String firstname;
    private String lastname;
    private String phone;

    public MobileContactUpdateDTO() {
    }

    public MobileContactUpdateDTO(Long id,String firstname, String lastname, String phone) {
        setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
