package com.example.projecttrackervalidation.Controller;

import com.example.projecttrackervalidation.ApiResponse.ApiResponse;
import com.example.projecttrackervalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    public ProjectController() {
        projects.add(new Project("CD15312","Website","Creating website for company","Not Started","Elm"));
        projects.add(new Project("CD24322","Website","Creating website for company","Not Started","Tuwaiq Academy"));
        projects.add(new Project("CD34545","Mobile App","Creating Mobile App for company","Not Started","Elm"));
        projects.add(new Project("CD45656","ERP system","Creating ERP system for company","Not Started","Elm"));
        projects.add(new Project("CD45656","ERP system","update future ERP system for company","Not Started","STC"));
    }

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getProject() {
        return ResponseEntity.ok().body(projects);
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getProjectByTitle(@PathVariable String title) {
        Project projectsFound = null;
        for(Project project : projects){
            if(project.getTitle().toLowerCase().contains(title.toLowerCase())){
                projectsFound =project;
                break;
            }
        }
        return ResponseEntity.status(200).body(projectsFound);
    }


    @GetMapping("/get-by-company-name/{CompanyName}")
    public ResponseEntity getProjectByCompanyName(@PathVariable String CompanyName) {
        ArrayList<Project> projectsFound = new ArrayList<>();
        for(Project project : projects){
            if(project.getCompanyName().toLowerCase().equals(CompanyName.toLowerCase())){
                projectsFound.add(project);
            }
        }
        return ResponseEntity.status(200).body(projectsFound);
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project , Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        projects.add(project);
        return ResponseEntity.ok().body(new ApiResponse("Project added"));

    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project , Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(index >= projects.size() || index < 0) return ResponseEntity.status(400).body(new ApiResponse("Project not found"));
        projects.set(index, project);
        return ResponseEntity.ok().body(new ApiResponse("Project updated"));
    }
    @PutMapping("/update-status/{index}")
    public ResponseEntity updateProjectStatus(@PathVariable int index) {
        if(index >= projects.size() || index < 0) return ResponseEntity.status(400).body(new ApiResponse("Project not found"));
        Project project = projects.get(index);
        if(project.getStatus().equals("Not Started") || project.getStatus().equals("Progress")) {
            project.setStatus("Completed");
            return ResponseEntity.ok().body(new ApiResponse("Project status updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("Project already completed"));

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        if(index >= projects.size()) return ResponseEntity.status(400).body(new ApiResponse("Project not found"));
        projects.remove(index);
        return ResponseEntity.ok().body(new ApiResponse("Project deleted"));
    }
}
