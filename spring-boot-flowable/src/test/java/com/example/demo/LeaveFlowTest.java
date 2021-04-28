package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricActivityInstanceQuery;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipInputStream;

@Log4j2
@SpringBootTest
class LeaveFlowTest {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    @Test
    void testLeaveFlow() throws Exception {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/leave.bpmn")
                .addClasspathResource("bpmn/leave.png")
                .name("请假申请流程")
                .deploy();

        System.out.println("部署流程ID: " + deployment.getId());
        System.out.println("部署流程名称: " + deployment.getName());
        Assertions.assertNotNull(deployment.getId());
        Assertions.assertEquals("请假申请流程", deployment.getName());

        this.queryProcessDefinitionDetails();
        String processInstanceId = this.startProcess();
        this.employeeCompleteTask();
        this.managerCompleteTask();

        // Verify the process status - if the end time is not null, means the process is end
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("holiday-request").processDefinitionId(processInstanceId).singleResult();
        boolean isEnd = historyService.createHistoricProcessInstanceQuery().deploymentId(processDefinition.getDeploymentId()).singleResult().getEndTime() != null;
        Assertions.assertTrue(isEnd);
    }

    void testZipDeployment() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("bpmn/holiday-request.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
        System.out.println("部署流程ID: " + deployment.getId());
        System.out.println("部署流程名称: " + deployment.getName());
    }

    String startProcess() throws Exception {
        HashMap<String, Object> vars = new HashMap<>();
        vars.put("employee", "Leo");
        vars.put("manager", "Jacky");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday-request", vars);

        System.out.println("流程定义ID: " + processInstance.getProcessDefinitionId());
        System.out.println("流程实例ID: " + processInstance.getId());
        System.out.println("当前活动ID: " + processInstance.getActivityId());

        Task task = taskService.createTaskQuery().processDefinitionKey("holiday-request").singleResult();
        Assertions.assertEquals("提交请假申请", task.getName());

        return processInstance.getProcessDefinitionId();
    }

    void employeeCompleteTask() {
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("holiday-request").list();

        for (Task task : tasks) {
            System.out.println("流程定义ID: " + task.getProcessDefinitionId());
            System.out.println("流程实例ID: " + task.getProcessInstanceId());
            System.out.println("任务ID: " + task.getId());
            System.out.println("任务名称: " + task.getName());
            System.out.println("任务负责人: " + task.getAssignee());
        }

        Task task = tasks.get(0);
        taskService.complete(task.getId());

        Task nextTask = taskService.createTaskQuery().processDefinitionKey("holiday-request").singleResult();
        Assertions.assertEquals("经理审批", nextTask.getName());
    }

    @Test
    void testQueryAllTasks() throws Exception {
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("holiday-request").list();

        for (Task task : tasks) {
            System.out.println("流程定义ID: " + task.getProcessDefinitionId());
            System.out.println("流程实例ID: " + task.getProcessInstanceId());
            System.out.println("任务ID: " + task.getId());
            System.out.println("任务名称: " + task.getName());
            System.out.println("任务负责人: " + task.getAssignee());
        }
    }

    @Test
    void testQueryAssigneeTasks() throws Exception {
        String employee = "Leo";
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("holiday-request").taskAssignee(employee).list();

        for (Task task : tasks) {
            System.out.println("流程定义ID: " + task.getProcessDefinitionId());
            System.out.println("流程实例ID: " + task.getProcessInstanceId());
            System.out.println("任务ID: " + task.getId());
            System.out.println("任务名称: " + task.getName());
            System.out.println("任务负责人: " + task.getAssignee());
        }
    }

    void managerCompleteTask() throws Exception {
        String employee = "Jacky";
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("holiday-request").taskAssignee(employee).list();

        for (Task task : tasks) {
            System.out.println("流程定义ID: " + task.getProcessDefinitionId());
            System.out.println("流程实例ID: " + task.getProcessInstanceId());
            System.out.println("任务ID: " + task.getId());
            System.out.println("任务名称: " + task.getName());
            System.out.println("任务负责人: " + task.getAssignee());
        }

        Task task = tasks.get(0);
        taskService.complete(task.getId());
    }

    void queryProcessDefinitionDetails() {
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().processDefinitionKey("holiday-request").list();
        for (ProcessDefinition processDefinition : definitions) {
            System.out.println("流程定义详情: ");
            System.out.println("\t流程部署ID: " + processDefinition.getDeploymentId());
            System.out.println("\t流程名称: " + processDefinition.getName());
            System.out.println("\t流程ID: " + processDefinition.getId());
        }
    }

    @Test
    void testHistory() throws Exception {
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        List<HistoricActivityInstance> activityInstances = historicActivityInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();

        for (HistoricActivityInstance activityInstance : activityInstances) {
            System.out.println(activityInstance.getId());
            System.out.println(activityInstance.getActivityId());
            System.out.println(activityInstance.getAssignee());
            System.out.println(activityInstance.getActivityName());
            System.out.println(activityInstance.getActivityType());
            System.out.println("------------------------------------------");
        }
    }
}
