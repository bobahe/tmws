package ru.levin.tmws.test;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import ru.levin.tmws.dto.UserDTO;

@RunWith(SpringRunner.class)
public class UserServiceTest extends AbstractServiceTest {

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
    public void testCreateUser() {
        @NotNull final UserDTO user = new UserDTO();
        user.setLogin("usr");
        userService.save(user);
        Assert.assertEquals(2, userService.getAll().size());
        user.setPassword("usr");
        userService.save(user);
        Assert.assertEquals(3, userService.getAll().size());
    }

    @Test
    public void testGetAllUser() {
        Assert.assertEquals(2, userService.getAll().size());
    }

    @Test
    public void testFindByIdUser() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        Assert.assertNotNull(userService.findById(user.getId()));
    }

    @Test
    public void testUpdateUser() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        user.setFirstName("usr");
        userService.update(user);
        Assert.assertEquals("usr", userService.findById(user.getId()).getFirstName());
    }

    @Test
    public void testRemoveUser() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        Assert.assertEquals(true, userService.remove(user));
    }

}
