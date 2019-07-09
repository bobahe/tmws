package ru.levin.tmws.command;

import org.jetbrains.annotations.NotNull;
import ru.levin.tmws.api.IServiceLocator;

public abstract class AbstractCommand {

    @NotNull
    public static final String ERR_PARSE_DATE_MESSAGE = "[CAN'T PARSE DATE, SAVING NULL]\n";

    @NotNull
    protected final IServiceLocator serviceLocator;

    public AbstractCommand(@NotNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getTitle();

    @NotNull
    public abstract String getDescription();

    public abstract boolean isRequiredAuthorization();

    public abstract void execute() throws Exception;

}
