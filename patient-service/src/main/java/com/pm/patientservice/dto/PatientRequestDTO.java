package com.pm.patientservice.dto;

import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max=1000, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address required")
    private String address;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    // validated only in POST requestDTO
    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered Date is required")
    private String registeredDate;
}
