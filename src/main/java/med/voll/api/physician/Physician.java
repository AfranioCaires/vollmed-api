package med.voll.api.physician;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.address.Address;

@Table(name = "physicians")
@Entity(name = "Physician")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Physician {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private Boolean active;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Physician(PhysicianRegisterData data) {
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.phoneNumber = data.phoneNumber();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updateData(PhysicianUpdateData data) {

        if (data.name() != null){
            this.name = data.name();
        }

        if (data.phoneNumber() != null){
            this.phoneNumber = data.phoneNumber();
        }

        if (data.address() != null){
            this.address.updateData(data.address());
        }

    }

    public void delete() {
        this.active = false;
    }
}
