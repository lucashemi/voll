package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String city;
    private String state;

    public Address(AddressData data) {
        this.addressLine1 = data.addressLine1();
        this.addressLine2 = data.addressLine2();
        this.postalCode = data.postalCode();
        this.city = data.city();
        this.state = data.state();
    }

    public void updateInformation(AddressData data) {
        if (data.addressLine1() != null) {
            this.addressLine1 = data.addressLine1();
        }
        if (data.addressLine2() != null) {
            this.addressLine2 = data.addressLine2();
        }
        if (data.postalCode() != null) {
            this.postalCode = data.postalCode();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.state() != null) {
            this.state = data.state();
        }
    }
}
