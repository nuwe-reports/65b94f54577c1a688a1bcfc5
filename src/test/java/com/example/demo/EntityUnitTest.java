package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    private LocalDateTime startsAt1;

    LocalDateTime finishesAt1;

    private LocalDateTime startsAt2;

    LocalDateTime finishesAt2;

    private LocalDateTime startsAt3;

    LocalDateTime finishesAt3;


    @BeforeAll
    public void initialize(){
        d1 = new Doctor("Carlos", "Perez", 45, "carlos.perez@medicur.com");
        p1 = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        r1 = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        startsAt1= LocalDateTime.parse("19:30 24/04/2023", formatter);
        finishesAt1 = LocalDateTime.parse("20:30 24/04/2023", formatter);

        startsAt2= LocalDateTime.parse("19:35 24/04/2023", formatter);
        finishesAt2 = LocalDateTime.parse("20:25 24/04/2023", formatter);

        startsAt3= LocalDateTime.parse("20:31 24/04/2023", formatter);
        finishesAt3 = LocalDateTime.parse("20:50 24/04/2023", formatter);

        a1 = new Appointment(p1, d1, r1, startsAt1, finishesAt1);
        a2 = new Appointment(p1, d1, r1, startsAt2, finishesAt2);
        a3 = new Appointment(p1, d1, r1, startsAt3, finishesAt3);
    }


    @Test
    void entity_Doctor_Properties(){

        d1.setId(12312L);

        assertThat(d1.getFirstName()).isEqualTo("Carlos");
        assertThat(d1.getLastName()).isEqualTo("Perez");
        assertThat(d1.getAge()).isEqualTo(45);
        assertThat(d1.getEmail()).isEqualTo("carlos.perez@medicur.com");
        assertThat(d1.getId()).isEqualTo(12312L);
    }

    @Test
    void entity_Patient_Properties(){

        p1.setId(12341234L);

        assertThat(p1.getFirstName()).isEqualTo("Jose Luis");
        assertThat(p1.getLastName()).isEqualTo("Olaya");
        assertThat(p1.getAge()).isEqualTo(37);
        assertThat(p1.getEmail()).isEqualTo("j.olaya@email.com");
        assertThat(p1.getId()).isEqualTo(12341234L);
    }

    @Test
    void entity_Room_Properties(){

        assertThat(r1.getRoomName()).isEqualTo("Dermatology");
    }

    @Test
    void entity_Appointment_Properties(){
        a1.setId(1234L);

        assertThat(a1.getDoctor()).isEqualTo(d1);
        assertThat(a1.getPatient()).isEqualTo(p1);
        assertThat(a1.getRoom()).isEqualTo(r1);
        assertThat(a1.getStartsAt()).isEqualTo(startsAt1);
        assertThat(a1.getFinishesAt()).isEqualTo(finishesAt1);
        assertThat(a1.getId()).isEqualTo(1234L);
    }

    @Test
    void entity_Appointment_Method_Overlaps_Should_Return_True(){
        assertThat(a1.overlaps(a2)).isTrue();
    }

    @Test
    void entity_Appointment_Method_Overlaps_Should_Return_False(){
        assertThat(a1.overlaps(a3)).isFalse();
    }
}
