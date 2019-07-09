package ru.levin.tmws.client.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.client.command.AbstractCommand;
import ru.levin.tmws.server.api.endpoint.Project;
import ru.levin.tmws.server.api.endpoint.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public final class CommandUtil {

    @NotNull
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    @Nullable
    public static Date parseDate(@Nullable final String date) {
        if (date == null || date.isEmpty()) return null;

        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException pe) {
            System.out.println(AbstractCommand.ERR_PARSE_DATE_MESSAGE);
            return null;
        }
    }

    public static void sortTaskList(@NotNull final String sortType, @NotNull final List<Task> list) {
        switch (sortType) {
            case "2":
                list.sort((o1, o2) -> {
                    if (o1.getStartDate() == null) return -1;
                    if (o2.getStartDate() == null) return -1;
                    return o1.getStartDate().compare(o2.getStartDate());
                });
                break;
            case "3":
                list.sort((o1, o2) -> {
                    if (o1.getEndDate() == null) return -1;
                    if (o2.getEndDate() == null) return -1;
                    return o1.getEndDate().compare(o2.getEndDate());
                });
                break;
            case "4":
                list.sort(Comparator.comparing(Task::getStatus));
                break;
            default:
                break;
        }
    }

    public static void sortProjectList(@NotNull final String sortType, @NotNull final List<Project> list) {
        switch (sortType) {
            case "2":
                list.sort((o1, o2) -> {
                    if (o1.getStartDate() == null) return -1;
                    if (o2.getStartDate() == null) return -1;
                    return o1.getStartDate().compare(o2.getStartDate());
                });
                break;
            case "3":
                list.sort((o1, o2) -> {
                    if (o1.getEndDate() == null) return -1;
                    if (o2.getEndDate() == null) return -1;
                    return o1.getEndDate().compare(o2.getEndDate());
                });
                break;
            case "4":
                list.sort(Comparator.comparing(Project::getStatus));
                break;
            default:
                break;
        }
    }

}
