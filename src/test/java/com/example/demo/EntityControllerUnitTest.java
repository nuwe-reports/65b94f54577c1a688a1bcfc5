
package com.example.demo;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetNoDoctors() throws Exception {
        when(doctorRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetAllDoctors() throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor ("Carina", "Zaray", 49, "c.zaray@hospital.accwe"));
        doctors.add(new Doctor ("Reyna", "Cayetana", 28, "r.cayetana@hospital.accwe"));

        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetDoctorById() throws Exception {
        long id = 1;
        Doctor doctor = new Doctor ("Reyna", "Cayetana", 28, "r.cayetana@hospital.accwe");;
        doctor.setId(id);
        Optional<Doctor> optionalDoctor = Optional.of(doctor);

        when(doctorRepository.findById(id)).thenReturn(optionalDoctor);
        mockMvc.perform(get("/api/doctors/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetDoctorById() throws Exception {
        long id = 1;
        when(doctorRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/doctors/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateDoctor() throws Exception {
        Doctor doctor = new Doctor ("Reyna", "Cayetana", 28, "r.cayetana@hospital.accwe");;
        mockMvc.perform(post("/api/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteDoctor() throws Exception {
        long id = 1;
        Doctor doctor = new Doctor ("Reyna", "Cayetana", 28, "r.cayetana@hospital.accwe");;
        doctor.setId(id);
        Optional<Doctor> optionalDoctor = Optional.of(doctor);

        when(doctorRepository.findById(id)).thenReturn(optionalDoctor);
        mockMvc.perform(delete("/api/doctors/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteNonExistentDoctor() throws Exception {
        long id = 1;
        when(doctorRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/doctors/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllDoctors() throws Exception {
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());
    }



}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetNoPatients() throws Exception {
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetAllPatients() throws Exception {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com"));
        patients.add(new Patient("Teresa", "Calderon", 84, "none"));

        when(patientRepository.findAll()).thenReturn(patients);
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetPatientById() throws Exception {
        long id = 1;
        Patient patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        patient.setId(id);
        Optional<Patient> optionalPatient = Optional.of(patient);

        when(patientRepository.findById(id)).thenReturn(optionalPatient);
        mockMvc.perform(get("/api/patients/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetPatientById() throws Exception {
        long id = 1;
        when(patientRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/patients/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreatePatient() throws Exception {
        Patient patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        mockMvc.perform(post("/api/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeletePatient() throws Exception {
        long id = 1;
        Patient patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        patient.setId(id);
        Optional<Patient> optionalPatient = Optional.of(patient);

        when(patientRepository.findById(id)).thenReturn(optionalPatient);
        mockMvc.perform(delete("/api/patients/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteNonExistentPatient() throws Exception {
        long id = 1;
        when(patientRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/patients/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllPatients() throws Exception {
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk());
    }


}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetNoRooms() throws Exception {
        when(roomRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetAllRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room1"));
        rooms.add(new Room("Room2"));

        when(roomRepository.findAll()).thenReturn(rooms);
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetRoomByRoomName() throws Exception {
        String roomName = "Room1";
        Room room = new Room(roomName);
        Optional<Room> optionalRoom = Optional.of(room);

        when(roomRepository.findByRoomName(roomName)).thenReturn(optionalRoom);
        mockMvc.perform(get("/api/rooms/{roomName}", roomName))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetRoomByNonExistentRoomName() throws Exception {
        String roomName = "NonExistentRoom";
        when(roomRepository.findByRoomName(roomName)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/rooms/{roomName}", roomName))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateRoom() throws Exception {
        Room room = new Room("Room3");
        mockMvc.perform(post("/api/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteRoomByRoomName() throws Exception {
        String roomName = "Room1";
        Room room = new Room(roomName);
        Optional<Room> optionalRoom = Optional.of(room);

        when(roomRepository.findByRoomName(roomName)).thenReturn(optionalRoom);
        mockMvc.perform(delete("/api/rooms/{roomName}", roomName))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteNonExistentRoom() throws Exception {
        String roomName = "NonExistentRoom";
        when(roomRepository.findByRoomName(roomName)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/rooms/{roomName}", roomName))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllRooms() throws Exception {
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk());
    }

}
