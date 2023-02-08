package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String ssn;
    private Boolean active;

    @Embedded
    private Address address;

    public Patient(PatientRegistrationData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.ssn = data.ssn();
        this.phone = data.phone();
        this.address = new Address(data.addressData());
    }

    public void updateInformation(PatientUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone() != null) {
            this.phone = data.phone();
        }
        if (data.addressData() != null) {
            this.address.updateInformation(data.addressData());
        }
    }

    public void delete() {
        this.active = false;
    }
}
