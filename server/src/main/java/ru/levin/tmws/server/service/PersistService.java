package ru.levin.tmws.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.IServiceLocator;
import ru.levin.tmws.server.api.service.IPersistService;
import ru.levin.tmws.server.dto.Domain;
import ru.levin.tmws.server.exception.DeserializeException;
import ru.levin.tmws.server.exception.SerializeException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PersistService implements IPersistService {

    @NotNull
    private static final String FILENAME = "data.";

    @Override
    public void serialize(@NotNull IServiceLocator bootstrap) {
        @NotNull final Domain domain = new Domain();
        domain.initFromInternalStorage(bootstrap);
        try (FileOutputStream dataFile = new FileOutputStream(FILENAME + "bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(dataFile)) {
            objectOutputStream.writeObject(domain);
        } catch (Exception e) {
            throw new SerializeException();
        }

    }

    @Override
    public void deserialize(@NotNull IServiceLocator bootstrap) {
        @Nullable Domain domain;
        try (FileInputStream dataFile = new FileInputStream(FILENAME + "bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(dataFile)) {
            domain = (Domain) objectInputStream.readObject();
        } catch (Exception e) {
            throw new DeserializeException();
        }

        domain.getProjects().forEach(project -> bootstrap.getProjectService().save(project));
        domain.getTasks().forEach(task -> bootstrap.getTaskService().save(task));
    }

    @Override
    public void saveFxmlXml(@NotNull IServiceLocator bootstrap) {
        @NotNull final Domain domain = new Domain();
        domain.initFromInternalStorage(bootstrap);

        try {
            @NotNull final XmlMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(FILENAME + "fxml.xml"), domain);
        } catch (Exception e) {
            throw new SerializeException();
        }
    }

    @Override
    public void saveFxmlJson(@NotNull IServiceLocator bootstrap) {
        @NotNull final Domain domain = new Domain();
        domain.initFromInternalStorage(bootstrap);

        try {
            @NotNull final ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(FILENAME + "fxml.json"), domain);
        } catch (Exception e) {
            throw new SerializeException();
        }
    }

    @Override
    public void loadFxmlXml(@NotNull IServiceLocator bootstrap) {
        try {
            @NotNull final XmlMapper mapper = new XmlMapper();
            @NotNull final Domain domain = mapper.readValue(new File(FILENAME + "fxml.xml"), Domain.class);
            if (domain.getProjects() != null) domain.getProjects().forEach(project -> bootstrap.getProjectService().save(project));
            if (domain.getTasks() != null) domain.getTasks().forEach(task -> bootstrap.getTaskService().save(task));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeserializeException();
        }
    }

    @Override
    public void loadFxmlJson(@NotNull IServiceLocator bootstrap) {
        try {
            @NotNull final ObjectMapper mapper = new ObjectMapper();
            @NotNull final Domain domain = mapper.readValue(new File(FILENAME + "fxml.json"), Domain.class);
            domain.getProjects().forEach(project -> bootstrap.getProjectService().save(project));
            domain.getTasks().forEach(task -> bootstrap.getTaskService().save(task));
        } catch (Exception e) {
            throw new DeserializeException();
        }
    }

    @Override
    public void saveJaxbXml(@NotNull IServiceLocator bootstrap) {
        @NotNull final Domain domain = new Domain();
        domain.initFromInternalStorage(bootstrap);

        try {
            @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            @NotNull final JAXBElement<Domain> jaxbElement = new JAXBElement<>(
                    new QName(null, "Domain"),
                    Domain.class,
                    domain
            );
            marshaller.marshal(jaxbElement, new File(FILENAME + "jaxb.xml"));
        } catch (Exception e) {
            throw new SerializeException();
        }
    }

    @Override
    public void saveJaxbJson(@NotNull IServiceLocator bootstrap) {
        @NotNull final Domain domain = new Domain();
        domain.initFromInternalStorage(bootstrap);

        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final Map<String, Object> jaxbProperties = new HashMap<>();
        jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        jaxbProperties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        try {
            @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {Domain.class}, jaxbProperties);
            @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(domain, new File(FILENAME + "jaxb.json"));
        } catch (Exception e) {
            throw new SerializeException();
        }
    }

    @Override
    public void loadJaxbXml(@NotNull IServiceLocator bootstrap) {
        try {
            @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
            @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(new File(FILENAME + "jaxb.xml"));
            domain.getProjects().forEach(project -> bootstrap.getProjectService().save(project));
            domain.getTasks().forEach(task -> bootstrap.getTaskService().save(task));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DeserializeException();
        }
    }

    @Override
    public void loadJaxbJson(@NotNull IServiceLocator bootstrap) {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final Map<String, Object> jaxbProperties = new HashMap<>();
        jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        jaxbProperties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        try {
            @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {Domain.class}, jaxbProperties);
            @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            @NotNull final StreamSource jsonFile = new StreamSource(FILENAME + "jaxb.json");
            @NotNull final Domain domain = unmarshaller.unmarshal(jsonFile, Domain.class).getValue();
            domain.getProjects().forEach(project -> bootstrap.getProjectService().save(project));
            domain.getTasks().forEach(task -> bootstrap.getTaskService().save(task));
        } catch (Exception e) {
            throw new DeserializeException();
        }

    }
}
