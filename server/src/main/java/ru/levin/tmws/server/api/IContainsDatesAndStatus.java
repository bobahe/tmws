package ru.levin.tmws.server.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.entity.Status;

import java.util.Date;

public interface IContainsDatesAndStatus {

    @Nullable
    Date getStartDate();

    @Nullable
    Date getEndDate();

    @NotNull
    Status getStatus();

}
