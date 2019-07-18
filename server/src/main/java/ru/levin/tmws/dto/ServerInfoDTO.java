package ru.levin.tmws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class ServerInfoDTO {

    @NotNull private String hostname;
    @NotNull private String port;

}
