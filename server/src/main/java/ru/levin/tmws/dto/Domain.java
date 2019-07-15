package ru.levin.tmws.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.IProjectService;
import ru.levin.tmws.api.service.ITaskService;

import javax.inject.Inject;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Inject
    @Nullable
    @XmlTransient
    @JsonIgnore
    private transient IProjectService projectService;

    @Inject
    @Nullable
    @XmlTransient
    @JsonIgnore
    private transient ITaskService taskService;

    public void initFromInternalStorage() {
        if (projectService == null || taskService == null) return;
        projects.addAll(projectService.getAll());
        tasks.addAll(taskService.getAll());
    }

}
