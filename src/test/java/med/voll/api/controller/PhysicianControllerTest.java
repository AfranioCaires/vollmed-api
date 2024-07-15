package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.physician.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
class PhysicianControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<PhysicianRegisterData> physicianRegisterDataJacksonTester;

    @Autowired
    private JacksonTester<PhysicianDetailData> physicianDetailDataJacksonTester;

    @MockBean
    private PhysicianRepository repository;


    @Test
    @DisplayName("should return http status code 400 when data is invalid")
    @WithMockUser
    void register_case1() throws Exception {
        var response = mvc
                .perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("should return http status code 200 when data is valid ")
    @WithMockUser
    void register_case2() throws Exception {
        var registerData = new PhysicianRegisterData(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Specialty.CARDIOLOGIA,
                addressData());

        when(repository.save(any())).thenReturn(new Physician(registerData));

        var response = mvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(physicianRegisterDataJacksonTester.write(registerData).getJson()))
                .andReturn().getResponse();

        var physicianDetailData = new PhysicianDetailData(
                null,
                registerData.name(),
                registerData.email(),
                registerData.crm(),
                registerData.phoneNumber(),
                registerData.specialty(),
                new Address(registerData.address())
        );
        var jsonEsperado = physicianDetailDataJacksonTester.write(physicianDetailData).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
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