package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Should return null when there's no doctor available in the date")
    void chooseRandomDoctorAvailableTest1() {
        var nextMonday10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("doctor", "doctor@voll.med", Specialty.CARDIOLOGY);
        var patient = registerPatient("patient", "patient@voll.med", "00000000");
        scheduleAppointment(nextMonday10AM, doctor, patient);

        var availableDoctor = doctorRepository.chooseRandomDoctorAvailable(Specialty.CARDIOLOGY, nextMonday10AM);
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Should return doctor when there's an doctor available in the date")
    void chooseRandomDoctorAvailableTest2() {
        var nextMonday10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("doctor", "doctor@voll.med", Specialty.CARDIOLOGY);

        var availableDoctor = doctorRepository.chooseRandomDoctorAvailable(Specialty.CARDIOLOGY, nextMonday10AM);
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    private void scheduleAppointment(LocalDateTime date, Doctor doctor, Patient patient) {
        testEntityManager.persist(new Appointment(null, date, doctor, patient));
    }

    private Doctor registerDoctor(String name, String email, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, specialty));
        testEntityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String ssn) {
        var patient = new Patient(patientData(name, email, ssn));
        testEntityManager.persist(patient);
        return patient;
    }

    private DoctorRegistrationData doctorData(String name, String email, Specialty specialty) {
        return new DoctorRegistrationData(
                name,
                email,
                "999999999",
                specialty,
                addressData()
        );
    }

    private PatientRegistrationData patientData(String name, String email, String ssn) {
        return new PatientRegistrationData(
                name,
                email,
                "999999999",
                ssn,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "Route 66",
                "",
                "55555",
                "Los Angeles",
                "CA"
        );
    }
}