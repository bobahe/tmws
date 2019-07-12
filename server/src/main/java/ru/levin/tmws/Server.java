package ru.levin.tmws;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.context.Bootstrap;
import ru.levin.tmws.endpoint.*;

import javax.enterprise.inject.se.SeContainerInitializer;

public class Server {

    @NotNull
    private final static Class[] endpoints = {
            SessionEndpoint.class, ProjectEndpoint.class, TaskEndpoint.class,
            UserEndpoint.class, AdminEndpoint.class
    };

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(Server.class).initialize()
                .select(Bootstrap.class).get().init(endpoints);
    }

}
