package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentListingData;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.appointment.AppointmentsSchedule;
import med.voll.api.domain.doctor.Specialty;
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
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentRegistrationData> jacksonTesterRegistration;

    @Autowired
    private JacksonTester<AppointmentListingData> jacksonTesterListing;

    @MockBean
    private AppointmentsSchedule appointmentsSchedule;

    @Test
    @DisplayName("Should return http error 400 when receiving invalid data")
    @WithMockUser
    void registerTest1() throws Exception {
        var response = mockMvc.perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http error 200 when receiving valid data")
    @WithMockUser
    void registerTest2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;

        var listingData = new AppointmentListingData(null, date, 1l, 1l);

        when(appointmentsSchedule.schedule(any())).thenReturn(listingData);

        var response = mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterRegistration.write(
                                new AppointmentRegistrationData(1l, 1l, date, specialty)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = jacksonTesterListing.write(
                listingData
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}