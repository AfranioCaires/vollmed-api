package med.voll.api.domain.physician;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record PhysicianUpdateData(
        @NotNull
        Long id,
        String name,
        String phoneNumber,
        AddressData address) {
}
