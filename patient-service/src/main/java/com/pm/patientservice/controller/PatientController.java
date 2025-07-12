package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.mapper.PatientMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {

    private final PatientService  patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }

    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        patientService.savePatient(patientRequestDTO);
        return ResponseEntity.ok().body("New patient added");
    }
}
