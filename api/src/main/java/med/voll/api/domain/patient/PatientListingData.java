package med.voll.api.domain.patient;

public record PatientListingData(Long id, String name, String phone) {

    public PatientListingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone());
    }

}
