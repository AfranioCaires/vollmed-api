package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentDetailData;
import med.voll.api.domain.physician.Specialty;
import med.voll.api.service.AppointmentSchedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentData> appointmentDataJacksonTester;

    @Autowired
    private JacksonTester<AppointmentDetailData> appointmentDetailDataJacksonTester;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Should return 400 when data is invalid")
    void createAppointmentCase1 () throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    };

    @Test
    @DisplayName("Should return 201 when data is valid")
    void createAppointmentCase2 () throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;

        var appointmentDetailData = new AppointmentDetailData(null, 2l, 5l, date);

        when(appointmentSchedule.schedule(any())).thenReturn(appointmentDetailData);

        var response = mvc.perform
                        (post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentDataJacksonTester.write(
                                        new AppointmentData(2l, 5l, date, specialty)
                                ).getJson())
                        )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var expectedJSON = appointmentDetailDataJacksonTester.write(
                appointmentDetailData
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJSON);
    };
}