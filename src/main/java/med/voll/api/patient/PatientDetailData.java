package med.voll.api.patient;

import med.voll.api.address.Address;

public record PatientDetailData(
        Long id,
        String name,
        String email,
        String cpf,
        String phoneNumber,
        Address address) {
    public PatientDetailData (Patient data){
        this(data.getId(), data.getName(), data.getEmail(), data.getCpf(), data.getPhoneNumber(), data.getAddress());
    }
}
