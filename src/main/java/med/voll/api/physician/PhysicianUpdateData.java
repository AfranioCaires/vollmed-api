package med.voll.api.physician;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record PhysicianUpdateData(
        @NotNull
        Long id,
        String name,
        String phoneNumber,
        AddressData address) {
}
