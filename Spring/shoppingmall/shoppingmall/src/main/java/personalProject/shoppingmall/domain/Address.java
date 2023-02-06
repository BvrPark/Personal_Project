package personalProject.shoppingmall.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String detail;

    protected Address(){

    }

    public Address(String city, String street, String detail){
        this.city = city;
        this.street = street;
        this.detail = detail;
    }
}
