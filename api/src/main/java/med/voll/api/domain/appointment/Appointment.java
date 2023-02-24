package med.voll.api.domain.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private LocalDateTime date;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "doctor_id")
        private Doctor doctor;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "patient_id")
        private Patient patient;
        private Boolean active = true;

        @Column(name = "reason_for_cancellation")
        @Enumerated(EnumType.STRING)
        private ReasonForCancellation reasonForCancellation;

        public Appointment(Long id, LocalDateTime date, Doctor doctor, Patient patient) {
                this.id = id;
                this.date = date;
                this.doctor = doctor;
                this.patient = patient;
        }

        public void cancel(ReasonForCancellation reasonForCancellation) {
                this.reasonForCancellation = reasonForCancellation;
                this.active = false;
        }
}
