package com.example.demo;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleTaskTest {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    @Test
    void testListProcesses() throws Exception {
        runtimeService.createProcessInstanceQuery().list().forEach(processInstance -> {
            System.out.println(processInstance.getName());
        });

        for (Deployment deployment : repositoryService.createDeploymentQuery().list()) {
            System.out.println(deployment.getId() + " " + deployment.getName() + " " + deployment.isNew());
        }

    }
}
