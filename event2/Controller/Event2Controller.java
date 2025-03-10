package com.example.event2.Controller;

import com.example.event2.Api.ApiResponse;
import com.example.event2.model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/event")
public class Event2Controller {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }
    @PostMapping("/add")
    public ResponseEntity addEvents(@RequestBody @Valid Event event,Errors errors) {
        if(errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("events added Successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvents(@PathVariable int index, @RequestBody @Valid Event event,Errors errors) {
       if (errors.hasErrors()){
           String message =errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(new ApiResponse(message));
       }
       events.set(index,event);
       return ResponseEntity.status(200).body(new ApiResponse("events updated Successfully"));
    }

    @DeleteMapping("/delete{index}")
    public ResponseEntity deleteEvents(@PathVariable int index) {
        if (index > events.size()) {
            return ResponseEntity.status(400).body(new ApiResponse("not found"));

        }
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("event deleted"));
    }

    @PutMapping("/change/{id}")
    public ResponseEntity changeCapacity(@PathVariable String id, @RequestBody int newCapacity) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(id)) {
                events.get(i).setCapacity(newCapacity);
                return ResponseEntity.status(200).body(new ApiResponse("capacity changed"));
            }

        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }
    @GetMapping("/search/{id}")
    public ResponseEntity searchEvent(@PathVariable String id){

        for(Event event:events){
            if(event.getId().equals(id)){
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }
}
