package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable pagination);

    @Query("""
        select p.active from Patient p
        where p.id = :id
        """)
    boolean findActiveById(Long id);

    @Query("""
        select p.id from Patient p join Appointment a on p.id = a.patient.id where a.active = true and p.id = :id
        """)
    Long findAnyAppointmentFromPatient(Long id);
}
