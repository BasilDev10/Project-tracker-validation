package com.example.projecttrackervalidation.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "You must not enter empty in id")
    @Size(min=2 , message = "id: the length is must more then 2")
    private String id;
    @NotEmpty(message = "You must not enter empty in title")
    @Size(min=8, message = "title: the length is must more then 8")
    private String title;
    @NotEmpty(message = "You must not enter empty in description")
    @Size(min=15, message = "description: the length is must more then 15")
    private String description;
    @NotEmpty(message = "You must not enter empty in status")
    @Pattern(regexp = "\\b(?:Not Started|Progress|Completed)\\b" , message = "status: Please enter Not Started or Progress or Completed")
    private String status;
    @NotEmpty(message = "You must not enter empty in Company Name")
    @Size(min=6, message = "Company Name :the length is must more then 6")
    private String companyName;
}
