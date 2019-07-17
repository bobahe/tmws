package ru.levin.tmws.test;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.dto.UserDTO;

import javax.inject.Inject;

public abstract class AbstractServiceTest {

    @NotNull
    @Inject
    protected ISessionService sessionService;

    @NotNull
    @Inject
    protected IProjectService projectService;

    @NotNull
    @Inject
    protected ITaskService taskService;

    @NotNull
    @Inject
    protected IUserService userService;

    protected void fillDb() {
        @NotNull UserDTO userOne = new UserDTO();
        userOne.setLogin("admin");
        userOne.setPassword("admin");
        userService.save(userOne);

        @NotNull UserDTO userTwo = new UserDTO();
        userTwo.setLogin("user");
        userTwo.setPassword("user");
        userService.save(userTwo);

        @NotNull ProjectDTO projectOne = new ProjectDTO();
        projectOne.setUserId(userOne.getId());
        projectOne.setName("Project 1");
        projectService.save(projectOne);

        @NotNull ProjectDTO projectTwo = new ProjectDTO();
        projectTwo.setUserId(userTwo.getId());
        projectTwo.setName("Project 2");
        projectService.save(projectTwo);

        @NotNull TaskDTO taskOne = new TaskDTO();
        taskOne.setUserId(userOne.getId());
        taskOne.setName("Task 1");
        taskService.save(taskOne);

        @NotNull TaskDTO taskTwo = new TaskDTO();
        taskTwo.setUserId(userOne.getId());
        taskTwo.setName("Task 2");
        taskTwo.setProjectId(projectOne.getId());
        taskService.save(taskTwo);

        @NotNull TaskDTO taskThree = new TaskDTO();
        taskThree.setUserId(userTwo.getId());
        taskThree.setName("Task 3");
        taskThree.setProjectId(projectTwo.getId());
        taskService.save(taskThree);

        @NotNull TaskDTO taskFour = new TaskDTO();
        taskFour.setUserId(userTwo.getId());
        taskFour.setName("Task 4");
        taskService.save(taskFour);

        @NotNull SessionDTO sessionOne = new SessionDTO();
        sessionOne.setUserId(userOne.getId());
        sessionService.save(sessionOne);

        @NotNull SessionDTO sessionTwo = new SessionDTO();
        sessionTwo.setUserId(userTwo.getId());
        sessionService.save(sessionTwo);
    }

}