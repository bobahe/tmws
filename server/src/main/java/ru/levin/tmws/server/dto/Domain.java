package ru.levin.tmws.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.entity.Project;
import ru.levin.tmws.server.entity.Task;
import ru.levin.tmws.server.entity.User;
import ru.levin.tmws.server.exception.NoCurrentUserException;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "Domain")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Domain implements Serializable {

    @Nullable
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    private final List<Project> projects = new ArrayList<>();

    @Nullable
    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks")
    private final List<Task> tasks = new ArrayList<>();

    public void initFromInternalStorage(@NotNull final IServiceLocator bootstrap) {
        projects.addAll(bootstrap.getProjectService().getAll());
        tasks.addAll(bootstrap.getTaskService().getAll());
    }

}
