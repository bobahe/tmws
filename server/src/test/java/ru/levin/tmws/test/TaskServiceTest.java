package ru.levin.tmws.test;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import ru.levin.tmws.dto.TaskDTO;
import ru.levin.tmws.dto.UserDTO;

@RunWith(SpringRunner.class)
public class TaskServiceTest extends AbstractServiceTest {

    @Before
    public void init() {
        fillDb();
    }

    @After
    public void close() {
        projectService.removeAll();
        sessionService.removeAll();
        taskService.removeAll();
        userService.removeAll();
    }

    @Test
    public void testCreateTask() {
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName("tsk");
        taskService.save(task);
        Assert.assertEquals(5, taskService.getAll().size());
    }

    @Test
    public void testGetAllTask() {
        Assert.assertEquals(4, taskService.getAll().size());
    }

    @Test
    public void testFindByUserIdTask() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        Assert.assertEquals(2, taskService.findAllByUserId(user.getId()).size());
    }

    @Test
    public void testFindByIdTask() {
        @Nullable final String id = taskService.getAll().get(0).getId();
        Assert.assertNotNull(id);
        Assert.assertNotNull(taskService.findOneById(id));
        Assert.assertNull(taskService.findOneById(null));
    }

    @Test
    public void testFindByIndexTask() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        Assert.assertNotNull(taskService.findOneByIndex(user.getId(), 2));
        Assert.assertNull(taskService.findOneByIndex(null, 1));
    }

    @Test
    public void testUpdateTask() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        @Nullable final TaskDTO task = taskService.findOneByIndex(user.getId(), 1);
        Assert.assertNotNull(task);
        task.setDescription("desc");
        taskService.update(task);
        Assert.assertEquals(1, taskService.findAllByPartOfNameOrDescription("d").size());
    }

    @Test
    public void testRemoveByUserIdTask() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        taskService.removeByUserId(user.getId());
        Assert.assertEquals(2, taskService.getAll().size());
    }

    @Test
    public void testRemoveAllTask() {
        taskService.removeAll();
        Assert.assertEquals(0, taskService.getAll().size());
    }

}
