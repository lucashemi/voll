package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank(message = "Address1 is required")
        String addressLine1,
        String addressLine2,
        @NotBlank(message = "Zip code is required")
        @Pattern(regexp = "\\d{5}", message = "Zip code must have 9 digits")
        String postalCode,
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "State is required")
        String state) {

}
