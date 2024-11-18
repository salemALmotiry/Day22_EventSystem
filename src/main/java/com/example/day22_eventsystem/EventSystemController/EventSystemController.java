package com.example.day22_eventsystem.EventSystemController;


import com.example.day22_eventsystem.ApiResponse.ApiResponse;
import com.example.day22_eventsystem.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-system")

public class EventSystemController {

    ArrayList<Event> events = new ArrayList<>();


    @GetMapping("/get")
    public ResponseEntity getEvents(){
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity newEvent(@RequestBody @Valid Event event, Errors errors) {

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added"));

    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index , @RequestBody @Valid Event event, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated"));
    }


    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){
        if (index<0 || index > events.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Event not found"));
        }

        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted"));

    }


    @GetMapping("/search/{id}")
    public ResponseEntity getEventById(@PathVariable int id) {
        for (Event event : events) {
            if (event.getID() == id) {
                return ResponseEntity.status(200).body(event);
            }
        }

        return ResponseEntity.status(400).body(new ApiResponse("Event not found"));

    }

    @PutMapping("/change-capacity/{id}/{newCapacity}")
    public ResponseEntity changeCapacity(@PathVariable int id ,@PathVariable int newCapacity) {


        if ( newCapacity < 0 ) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid capacity"));
        }
        for (Event event : events) {
            if (event.getID() == id ) {
                if (newCapacity > event.getCapacity()) {

                    return ResponseEntity.status(400).body(new ApiResponse("Capacity exceeded"));

                }
                event.setCapacity(event.getCapacity() - newCapacity);
            }
        }
        return ResponseEntity.status(200).body( new ApiResponse("capacity changed"));

    }



}
