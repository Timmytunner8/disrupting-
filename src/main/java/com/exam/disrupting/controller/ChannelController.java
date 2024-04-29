package com.exam.disrupting.controller;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.disrupting.model.Channel;
import com.exam.disrupting.model.Output;
import com.exam.disrupting.service.ServiceChannel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/desrupting")
public class ChannelController {

    @Autowired
    ServiceChannel serviceChannel;

    @GetMapping("/channels")
    public ResponseEntity<?> getAll() {
        if (!serviceChannel.getChannels().isEmpty()) {
            return new ResponseEntity<>(serviceChannel.getChannels(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> createChannel(@RequestBody Channel channel) {
        if (serviceChannel.create(channel) != null) {
            return new ResponseEntity<>(channel,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Ya existe el programa o esta duplicado",HttpStatus.OK);
        }
    }

    @PostMapping("/actualizar/{id}")
    public ResponseEntity<?> updateChannel(@PathVariable Integer id, @RequestBody Channel channel) {
        if (serviceChannel.update(id,channel) != null) {
            return new ResponseEntity<>(channel, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>("No se puede modificar el horario", HttpStatus.FOUND);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteChannel(@PathVariable Integer id) {
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
