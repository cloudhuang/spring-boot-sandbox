package com.example.demo.controller;

import com.example.demo.service.FlowableService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flow")
public class FLowableController {
    private final FlowableService flowableService;

    @PostMapping(value = "/process")
    public void startProcessInstance() {
        log.info("Starting deployed process[{}]", "oneTaskProcess");
        flowableService.startProcess("oneTaskProcess");
    }

    @GetMapping("")
    public ResponseEntity<?> tasks(@RequestParam String assignee) {
        List<Task> tasks = flowableService.getTasks(assignee);
        List<TaskRepresentation> list = tasks.stream().map(t -> {
            TaskRepresentation task = new TaskRepresentation();
            task.setId(t.getId());
            task.setName(t.getName());
            return task;
        }).collect(toList());

        return ResponseEntity.ok(list);
    }

    @Data
    static class TaskRepresentation {
        private String id;
        private String name;
    }
}
