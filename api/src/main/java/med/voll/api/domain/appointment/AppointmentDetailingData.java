package med.voll.api.domain.appointment;

import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDetailingData(Long id, LocalDate date, LocalTime time, String doctorName, Specialty specialty, String patientName) {

    public AppointmentDetailingData(Appointment appointment) {
        this(appointment.getId(), appointment.getDate().toLocalDate(), appointment.getDate().toLocalTime(), appointment.getDoctor().getName(), appointment.getDoctor().getSpecialty(), appointment.getPatient().getName());
    }
}
