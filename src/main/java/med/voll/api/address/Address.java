package med.voll.api.address;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor

public class Address {
    @NotBlank
    private String street;

    @NotBlank
    private String neighbour;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String zipcode;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    private String complement;

    private String number;

    public Address(AddressData address) {
        this.street = address.street();
        this.neighbour = address.neighbour();
        this.zipcode = address.zipcode();
        this.city = address.city();
        this.state = address.state();
        this.complement = address.complement();
        this.number = address.number();
    }
}
