package ru.levin.tmws.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.entity.Status;

import java.util.Date;

public interface IContainsDatesAndStatus {

    @Nullable
    Date getStartDate();

    @Nullable
    Date getEndDate();

    @NotNull
    Status getStatus();

}
