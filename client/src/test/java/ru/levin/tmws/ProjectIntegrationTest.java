package ru.levin.tmws;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.levin.tmws.api.endpoint.*;
import ru.levin.tmws.endpoint.ProjectEndpointService;
import ru.levin.tmws.endpoint.SessionEndpointService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectIntegrationTest {

    @NotNull
    public static final String SESSION_IS_NULL = "Session is null.";
    @NotNull
    public static final String PROJECT_ENDPOINT_IS_NULL = "Project endpoint port is null.";

    @Nullable SessionDTO session;
    @Nullable ISessionEndpoint sessionEndpointPort;
    @Nullable IProjectEndpoint projectEndpointPort;

    @Before
    public void init() {
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final SessionEndpointService sessionEndpointService = new SessionEndpointService();
        projectEndpointPort = projectEndpointService.getProjectEndpointPort();
        sessionEndpointPort = sessionEndpointService.getSessionEndpointPort();
        session = sessionEndpointPort.openSession("user", "user");
    }

    @Test
    public void testACreateProject() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (projectEndpointPort == null) Assert.fail(PROJECT_ENDPOINT_IS_NULL);
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName("Integration test project");
        project.setDescription("Integration test description");
        Assert.assertEquals(ProjectDTO.class, projectEndpointPort.createProject(session, project).getClass());
    }

    @Test
    public void testBGetProjectByIndex() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (projectEndpointPort == null) Assert.fail(PROJECT_ENDPOINT_IS_NULL);
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName("Integration test project");
        project.setDescription("Integration test description");
        projectEndpointPort.createProject(session, project);
        @NotNull final ProjectDTO savedProject = projectEndpointPort.getProjectById(session, 1);
        Assert.assertNotEquals(null, savedProject);
    }

    @Test
    public void testCUpdateProject() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (projectEndpointPort == null) Assert.fail(PROJECT_ENDPOINT_IS_NULL);
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName("Integration test project");
        project.setDescription("Integration test description");
        projectEndpointPort.createProject(session, project);
        @NotNull final ProjectDTO savedProject = projectEndpointPort.getProjectById(session, 1);
        if (savedProject == null) Assert.fail("Could not get project from server.");
        savedProject.setStatus(Status.IN_PROCESS);
        projectEndpointPort.updateProject(session, savedProject);
        Assert.assertEquals(savedProject.getId(), projectEndpointPort.getProjectById(session, 1).getId());
    }

    @Test
    public void testDRemoveProject() {
        if (session == null) Assert.fail(SESSION_IS_NULL);
        if (projectEndpointPort == null) Assert.fail(PROJECT_ENDPOINT_IS_NULL);
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName("Integration test project");
        project.setDescription("Integration test description");
        projectEndpointPort.createProject(session, project);
        @NotNull final ProjectDTO savedProject = projectEndpointPort.getProjectById(session, 1);
        if (savedProject == null) Assert.fail("Could not get project from server.");
        projectEndpointPort.removeProject(session, savedProject);
        Assert.assertEquals(0, projectEndpointPort.getProjectAll(session).size());
    }

    @After
    public void clean() {
        if (sessionEndpointPort == null) Assert.fail("Session endpoint is null.");
        if (projectEndpointPort == null) Assert.fail(PROJECT_ENDPOINT_IS_NULL);
        projectEndpointPort.removeProjectAll(session);
        sessionEndpointPort.closeSession(session);
    }

}
