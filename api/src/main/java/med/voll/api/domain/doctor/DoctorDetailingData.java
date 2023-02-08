package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DoctorDetailingData(Long id, String name, String email, String phone, Specialty specialty, Address address) {
    public DoctorDetailingData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
