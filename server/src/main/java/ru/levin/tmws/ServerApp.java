package ru.levin.tmws;

import ru.levin.tmws.context.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public class ServerApp {

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(ServerApp.class).initialize()
                .select(Bootstrap.class).get().init();
    }

}
