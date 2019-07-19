package ru.levin.tmws.test;

import org.jetbrains.annotations.NotNull;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.api.service.ISessionService;
import ru.levin.tmws.api.service.ITaskService;
import ru.levin.tmws.api.service.IUserService;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.dto.UserDTO;

@ContextConfiguration("/beans.xml")
@RunWith(SpringRunner.class)
public abstract class AbstractServiceTest {

    @NotNull
    @Autowired
    protected ISessionService sessionService;

    @NotNull
    @Autowired
    protected IProjectService projectService;

    @NotNull
    @Autowired
    protected ITaskService taskService;

    @NotNull
    @Autowired
    protected IUserService userService;

    protected void fillDb() {
        @NotNull final UserDTO userOne = new UserDTO();
        userOne.setLogin("admin");
        userOne.setPassword("admin");
        userService.save(userOne);

        @NotNull final UserDTO userTwo = new UserDTO();
        userTwo.setLogin("user");
        userTwo.setPassword("user");
        userService.save(userTwo);

        @NotNull final ProjectDTO projectOne = new ProjectDTO();
        projectOne.setUserId(userOne.getId());
        projectOne.setName("Project 1");
        projectService.save(projectOne);

        @NotNull final ProjectDTO projectTwo = new ProjectDTO();
        projectTwo.setUserId(userTwo.getId());
        projectTwo.setName("Project 2");
        projectService.save(projectTwo);

        @NotNull final TaskDTO taskOne = new TaskDTO();
        taskOne.setUserId(userOne.getId());
        taskOne.setName("Task 1");
        taskService.save(taskOne);

        @NotNull final TaskDTO taskTwo = new TaskDTO();
        taskTwo.setUserId(userOne.getId());
        taskTwo.setName("Task 2");
        taskTwo.setProjectId(projectOne.getId());
        taskService.save(taskTwo);

        @NotNull final TaskDTO taskThree = new TaskDTO();
        taskThree.setUserId(userTwo.getId());
        taskThree.setName("Task 3");
        taskThree.setProjectId(projectTwo.getId());
        taskService.save(taskThree);

        @NotNull final TaskDTO taskFour = new TaskDTO();
        taskFour.setUserId(userTwo.getId());
        taskFour.setName("Task 4");
        taskService.save(taskFour);

        @NotNull final SessionDTO sessionOne = new SessionDTO();
        sessionOne.setUserId(userOne.getId());
        sessionService.save(sessionOne);

        @NotNull final SessionDTO sessionTwo = new SessionDTO();
        sessionTwo.setUserId(userTwo.getId());
        sessionService.save(sessionTwo);
    }

}
