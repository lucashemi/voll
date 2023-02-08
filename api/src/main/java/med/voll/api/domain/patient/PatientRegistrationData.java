package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

public record PatientRegistrationData(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Telephone number is required")
        @Pattern(regexp = "\\d{10}", message = "Telephone number format is invalid")
        String phone,
        @NotBlank(message = "SSN is required")
        String ssn,
        @NotNull(message = "Address is required")
        @Valid
        AddressData addressData) {
}
