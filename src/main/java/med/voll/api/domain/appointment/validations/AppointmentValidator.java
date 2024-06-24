package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentData;

public interface AppointmentValidator {

    void validate(AppointmentData data);
}
