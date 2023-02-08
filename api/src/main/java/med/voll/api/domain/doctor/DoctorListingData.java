package med.voll.api.domain.doctor;

public record DoctorListingData(Long id, String name, String email, Specialty specialty) {

    public DoctorListingData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getSpecialty());
    }
}
