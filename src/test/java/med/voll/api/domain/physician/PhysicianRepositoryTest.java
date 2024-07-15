package med.voll.api.domain.physician;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegisterData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PhysicianRepositoryTest {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Should return null when the only registered physician is not available on the date.")
    void choosePhysicianRandomlyCase1() {
//      given
        var nextMondayAt10AM = LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

//      when
        var physician = createAPhysician("Physician", "test@email.com", "123456", Specialty.CARDIOLOGIA);
        var patient = createAPatient("Patient", "patient@email.com", "12345678900");
        makeAnAppointment(physician, patient, nextMondayAt10AM);

        var physicianAvailable = physicianRepository.choosePhysicianRandomly(Specialty.CARDIOLOGIA, nextMondayAt10AM);
//      then
        assertThat(physicianAvailable).isNull();
    }

    @Test
    @DisplayName("Should return a physician when an active physician is available on the date.")
    void choosePhysicianRandomlyCase2() {
//      given
        var nextMondayAt10AM = LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

//      when
        var physician = createAPhysician("Physician", "test@email.com", "123456", Specialty.CARDIOLOGIA);

        var physicianAvailable = physicianRepository.choosePhysicianRandomly(Specialty.CARDIOLOGIA, nextMondayAt10AM);
//      then
        assertThat(physicianAvailable).isEqualTo(physician);
    }


    private void makeAnAppointment(Physician physician, Patient patient, LocalDateTime date) {
        em.persist(Appointment.builder()
                .physician(physician)
                .patient(patient)
                .date(date)
                .build());
    }

    private Physician createAPhysician(String name, String email, String crm, Specialty specialty) {
        var physician = new Physician(physicianData(name, email, crm, specialty));
        em.persist(physician);
        return physician;
    }

    private Patient createAPatient(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private PhysicianRegisterData physicianData(String name, String email, String crm, Specialty specialty) {
        return new PhysicianRegisterData(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private PatientRegisterData patientData(String nome, String email, String cpf) {
        return new PatientRegisterData(
                nome,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}