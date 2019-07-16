package ru.levin.tmws;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.levin.tmws.api.endpoint.*;
import ru.levin.tmws.endpoint.SessionEndpointService;
import ru.levin.tmws.endpoint.TaskEndpointService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskIntegrationTest {

    @NotNull
    public static final String SESSION_IS_NULL = "Session is null.";
    @NotNull
    public static final String TASK_ENDPOINT_IS_NULL = "Task endpoint port is null.";

    @Nullable SessionDTO session;
    @Nullable ISessionEndpoint sessionEndpointPort;
    @Nullable ITaskEndpoint taskEndpointPort;

    @Before
    public void init() {
        @NotNull final SessionEndpointService sessionEndpointService = new SessionEndpointService();
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        taskEndpointPort = taskEndpointService.getTaskEndpointPort();
        sessionEndpointPort = sessionEndpointService.getSessionEndpointPort();
        session = sessionEndpointPort.openSession("user", "user");
    }

    @Test
    public void testACreateTask() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (taskEndpointPort == null) Assert.fail(TASK_ENDPOINT_IS_NULL);
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName("Integration test task");
        task.setDescription("Integration test task");
        Assert.assertEquals(TaskDTO.class, taskEndpointPort.createTask(session, task).getClass());
    }

    @Test
    public void testBGetTaskByIndex() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (taskEndpointPort == null) Assert.fail(TASK_ENDPOINT_IS_NULL);
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName("Integration test task");
        task.setDescription("Integration test task");
        taskEndpointPort.createTask(session, task);
        @NotNull final TaskDTO savedTask = taskEndpointPort.getTaskById(session, 1);
        Assert.assertNotEquals(null, savedTask);
    }

    @Test
    public void testCUpdateTask() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (taskEndpointPort == null) Assert.fail(TASK_ENDPOINT_IS_NULL);
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName("Integration test task");
        task.setDescription("Integration test task");
        taskEndpointPort.createTask(session, task);
        @NotNull final TaskDTO savedTask = taskEndpointPort.getTaskById(session, 1);
        if (savedTask == null) Assert.fail("Could not get task from server.");
        savedTask.setStatus(Status.IN_PROCESS);
        taskEndpointPort.updateTask(session, savedTask);
        Assert.assertEquals(savedTask.getId(), taskEndpointPort.getTaskById(session, 1).getId());
    }

    @Test
    public void testDTaskProject() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (taskEndpointPort == null) Assert.fail(TASK_ENDPOINT_IS_NULL);
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName("Integration test task");
        task.setDescription("Integration test task");
        taskEndpointPort.createTask(session, task);
        @NotNull final TaskDTO savedTask = taskEndpointPort.getTaskById(session, 1);
        if (savedTask == null) Assert.fail("Could not get task from server.");
        taskEndpointPort.removeTask(session, savedTask);
        Assert.assertEquals(0, taskEndpointPort.getTaskAll(session).size());
    }

    @After
    public void clean() {
        if (sessionEndpointPort == null) Assert.fail("Session endpoint is null.");
        if (taskEndpointPort == null) Assert.fail(TASK_ENDPOINT_IS_NULL);
        taskEndpointPort.removeTaskAll(session);
        sessionEndpointPort.closeSession(session);
    }

}
