package ru.levin.tmws.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.api.service.ITaskService;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@XmlRootElement(name = "Domain")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Domain implements Serializable {

    @NotNull
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    private final List<ProjectDTO> projects = new ArrayList<>();

    @NotNull
    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks")
    private final List<TaskDTO> tasks = new ArrayList<>();

    @NotNull
    @XmlTransient
    @JsonIgnore
    private transient IProjectService projectService;
    @Autowired
    public void setProjectService(@NotNull final IProjectService projectService) { this.projectService = projectService; }

    @NotNull
    @XmlTransient
    @JsonIgnore
    private transient ITaskService taskService;
    @Autowired
    public void setTaskService(@NotNull final ITaskService taskService) { this.taskService = taskService; }

    public void initFromInternalStorage() {
        projects.addAll(projectService.getAll());
        tasks.addAll(taskService.getAll());
    }

}
