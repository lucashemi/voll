package med.voll.api.domain.appointment;

import med.voll.api.domain.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findAllByActiveTrue(Pageable pagination);

    boolean existsByDoctorIdAndDateAndActiveIsTrue(Long idDoctor, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime earliestTime, LocalDateTime latestTime);
}
