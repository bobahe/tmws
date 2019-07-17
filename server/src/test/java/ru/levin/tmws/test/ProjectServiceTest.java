package ru.levin.tmws.test;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.levin.tmws.dto.ProjectDTO;
import ru.levin.tmws.dto.UserDTO;

@RunWith(CdiTestRunner.class)
public class ProjectServiceTest extends AbstractServiceTest {

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
    public void testCreateProject() {
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName("prj");
        projectService.save(project);
        Assert.assertEquals(3, projectService.getAll().size());
    }

    @Test
    public void testGetAllProject() {
        Assert.assertEquals(2, sessionService.getAll().size());
    }

    @Test
    public void testFindByUserIdProject() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        Assert.assertEquals(1, projectService.findAllByUserId(user.getId()).size());
    }

    @Test
    public void testFindByIdProject() {
        @Nullable final String id = projectService.getAll().get(0).getId();
        Assert.assertNotNull(id);
        Assert.assertNotNull(projectService.findOneById(id));
        Assert.assertNull(projectService.findOneById(null));
    }

    @Test
    public void testFindByIndexProject() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        Assert.assertNotNull(projectService.findOneByIndex(user.getId(), 1));
        Assert.assertNull(projectService.findOneByIndex(null, 1));
    }

    @Test
    public void testUpdateProject() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        @Nullable final ProjectDTO project = projectService.findOneByIndex(user.getId(), 1);
        Assert.assertNotNull(project);
        project.setDescription("desc");
        projectService.update(project);
        Assert.assertEquals(1, projectService.findAllByPartOfNameOrDescription("desc").size());
    }

    @Test
    public void testRemoveByUserIdProject() {
        @Nullable final UserDTO user = userService.getUserByLoginAndPassword("admin", "admin");
        Assert.assertNotNull(user);
        projectService.removeByUserId(user.getId());
        Assert.assertEquals(1, projectService.getAll().size());
        Assert.assertEquals(3, taskService.getAll().size());
    }

    @Test
    public void testRemoveAllProject() {
        projectService.removeAll();
        Assert.assertEquals(0, projectService.getAll().size());
        Assert.assertEquals(2, taskService.getAll().size());
    }

}
