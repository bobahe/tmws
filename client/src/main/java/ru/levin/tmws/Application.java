package ru.levin.tmws;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.levin.tmws.context.Bootstrap;

public final class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
        ((BeanFactory) appContext).getBean(Bootstrap.class).init(appContext);
    }

}
