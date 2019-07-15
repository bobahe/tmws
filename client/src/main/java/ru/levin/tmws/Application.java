package ru.levin.tmws;

import ru.levin.tmws.context.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public final class Application {

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(Application.class).initialize()
                .select(Bootstrap.class).get().init();
    }

}
