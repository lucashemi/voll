package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(@NotBlank(message = "Email is required") String login, @NotBlank(message = "Password is required") String password) {
}
