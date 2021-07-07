package com.example.demo.controller;

import com.example.demo.dto.SubForumDto;
import com.example.demo.model.SubForum;
import com.example.demo.service.SubForumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subforum")
@AllArgsConstructor
@Slf4j
public class SubForumController {

    private final SubForumService subForumService;

    @PostMapping
    public ResponseEntity<SubForumDto> createSubForum(@RequestBody SubForumDto subForumDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subForumService.save(subForumDto));
    }

    @GetMapping
    public ResponseEntity<List<SubForumDto>> getAllSubForums(){
        return ResponseEntity.status(HttpStatus.OK).body(subForumService.getAll());
    }

    // endpoint to receive a subforum based on the id
    @GetMapping("/{id}")
    public ResponseEntity<SubForumDto> getSubForum(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subForumService.getSubForum(id));
    }

}
