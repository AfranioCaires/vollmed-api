package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.appointment.AppointmentData;

public interface AppointmentValidator {

    void validate(AppointmentData data);
}
