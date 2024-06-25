package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancelData(
        @NotNull
        Long appointmentId,

        @NotNull
        CancellationReason reason) {
}