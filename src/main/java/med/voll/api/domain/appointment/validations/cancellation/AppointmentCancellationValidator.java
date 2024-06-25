package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.AppointmentCancelData;

public interface AppointmentCancellationValidator {
    void validate(AppointmentCancelData data);
}
