package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.AppointmentListingData;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DoctorRegistrationData> jacksonTesterRegistration;
    @Autowired
    private JacksonTester<DoctorDetailingData> jacksonTesterListing;
    @MockBean
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("Should return http error 400 when receiving invalid data")
    @WithMockUser
    void registerTest1() throws Exception {
        var response = mockMvc.perform(post("/doctors"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http error 200 when receiving valid data")
    @WithMockUser
    void registerTest2() throws Exception {
        var addressData = new AddressData("Route 66", "", "55555", "Los Angeles", "CA");
        var registerData = new DoctorRegistrationData("doctor", "doctor@voll.med", "9999999999",  Specialty.CARDIOLOGY, addressData);

        when(doctorRepository.save(any())).thenReturn(new Doctor(registerData));

        var response = mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterRegistration.write(registerData).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var detailingData = new DoctorDetailingData(null, registerData.name(), registerData.email(), registerData.phone(), registerData.specialty(), new Address(registerData.addressData()));

        var expectedJson = jacksonTesterListing.write(
                detailingData
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}