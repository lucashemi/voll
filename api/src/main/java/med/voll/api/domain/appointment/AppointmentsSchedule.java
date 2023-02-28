package med.voll.api.domain.appointment;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.validations.cancellation.AppointmentCancellationValidator;
import med.voll.api.domain.appointment.validations.scheduling.AppointmentSchedulingValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentsSchedule {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private List<AppointmentSchedulingValidator> validators;

    @Autowired
    private List<AppointmentCancellationValidator> cancellationValidators;

    public AppointmentListingData schedule(AppointmentRegistrationData data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Patient id doesn't exist");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Doctor id doesn't exist");
        }

        validators.forEach(v -> v.validate(data));

        var doctor = chooseDoctor(data);
        if (doctor == null) {
            throw new ValidationException("There are no available doctors on this date");
        }
        var patient = patientRepository.getReferenceById(data.idPatient());

        var appointment = new Appointment(null, data.date(), doctor, patient, true, null);

        appointmentRepository.save(appointment);

        return new AppointmentListingData(appointment);
    }

    public Page<AppointmentDetailingData> list(Pageable pagination) {
        return appointmentRepository.findAllByActiveTrue(pagination).map(AppointmentDetailingData::new);
    }

    public void cancel(AppointmentDeletionData data) {
        if (!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("Invalid appointment id");
        }

        cancellationValidators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reasonForCancellation());
    }

    private Doctor chooseDoctor(AppointmentRegistrationData data) {
        if(data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is mandatory when a doctor is not chosen");
        }

        return doctorRepository.chooseRandomDoctorAvailable(data.specialty(), data.date());
    }

    public Appointment getReferenceById(Long id) {
        return appointmentRepository.getReferenceById(id);
    }

    public Doctor getDoctor(AppointmentUpdateData data) throws Exception {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        Doctor doctor = doctorRepository.chooseRandomDoctorAvailable(data.specialty(), data.date());
        if (doctor == null) {
            throw new ValidationException("No doctor available!");
        }
        return doctor;
    }

    public Patient getPatient(Long idPatient) {
        return patientRepository.getReferenceById(idPatient);
    }
}
