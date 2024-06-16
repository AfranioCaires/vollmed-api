package med.voll.api.patient;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;


@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "patients")
@Entity(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Boolean active;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private Address address;

    public Patient(PatientRegisterData data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.phoneNumber = data.phoneNumber();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updateData(PatientUpdateData data) {
        if (data.phoneNumber() != null){
            this.phoneNumber = data.phoneNumber();
        }

        if (data.address() != null){
            this.address.updateData(data.address());
        }

        if (data.name() != null){
            this.name = data.name();
        }
    }

    public void delete() {
        this.active = false;
    }

}
