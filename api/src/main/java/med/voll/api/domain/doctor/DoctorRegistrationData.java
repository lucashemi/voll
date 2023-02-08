package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

public record DoctorRegistrationData(
        @NotBlank(message = "Name is required")
        String name,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,
        @Pattern(regexp = "\\d{10}", message = "Telephone number format is invalid")
        @NotBlank(message = "Telephone number is required")
        String phone,
        @NotNull(message = "Specialty is required")
        Specialty specialty,
        @NotNull(message = "Address data is required")
        @Valid AddressData addressData) {
}
