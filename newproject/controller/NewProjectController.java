package com.example.newproject.controller;

import com.example.newproject.Api.ApiResponse;
import com.example.newproject.model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/project")
public class NewProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("project added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("project updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        if (index > projects.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("project not found"));

        }
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted"));
    }
    @GetMapping("/search/{title}")
    public ResponseEntity searchTask(@PathVariable String title){

        for(Project project: projects){
            if(project.getTitle().equals(title)){
                return ResponseEntity.status(200).body(project);
            }
        }

        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }
    @GetMapping("/company/{companyName}")

    public ResponseEntity getProjectsByCompany(@PathVariable String companyName){

        for(Project project : projects){
            if(project.getCompanyName().equals(companyName)){
                return ResponseEntity.status(200).body(project);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));

    }
}