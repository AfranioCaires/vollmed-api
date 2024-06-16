package med.voll.api.physician;

import med.voll.api.address.Address;

public record PhysicianDetailData(
        Long id,
        String name,
        String email,
        String crm,
        String phoneNumber,
        Specialty specialty,
        Address address) {
    public PhysicianDetailData(Physician data) {
        this(data.getId(),
                data.getName(),
                data.getEmail(),
                data.getCrm(),
                data.getPhoneNumber(),
                data.getSpecialty(),
                data.getAddress());
    }
}
