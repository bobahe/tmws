package ru.levin.tmws.test;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.levin.tmws.dto.SessionDTO;
import ru.levin.tmws.dto.UserDTO;

@RunWith(CdiTestRunner.class)
public class SessionServiceTest extends AbstractServiceTest {

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
    public void testCreateSession() {
        @NotNull SessionDTO session = new SessionDTO();
        sessionService.save(session);
        Assert.assertEquals(3, sessionService.getAll().size());
    }

    @Test
    public void testGetAllSession() {
        Assert.assertEquals(2, sessionService.getAll().size());
    }

    @Test
    public void testFindByUserIdSession() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        Assert.assertEquals(1, sessionService.findAllByUserId(user.getId()).size());
    }

    @Test
    public void testFindByIdSession() {
        @Nullable final String sessionId = sessionService.getAll().get(0).getId();
        Assert.assertNotNull(sessionId);
        Assert.assertNotNull(sessionService.findById(sessionId));
        Assert.assertNull(sessionService.findById(null));
    }

    @Test
    public void testUpdateSession() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        sessionService.getAll().forEach(session -> {
            session.setUserId(user.getId());
            sessionService.update(session);
        });
        Assert.assertEquals(2, sessionService.findAllByUserId(user.getId()).size());
    }

    @Test
    public void testRemoveByUserIdSession() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        sessionService.removeByUserId(user.getId());
        Assert.assertEquals(1, sessionService.getAll().size());
    }

    @Test
    public void testRemoveAllSession() {
        sessionService.removeAll();
        Assert.assertEquals(0, sessionService.getAll().size());
    }

}
