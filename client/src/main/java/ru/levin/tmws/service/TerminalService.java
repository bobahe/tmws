package ru.levin.tmws.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.api.service.ITerminalService;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStreamReader;
import java.util.Scanner;

@ApplicationScoped
public class TerminalService implements ITerminalService {

    @NotNull
    private final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    @Override
    public void println(@Nullable final String text) {
        System.out.println(text);
    }

    @Override
    public void printerr(@Nullable final String text) {
        System.err.println(text);
    }

    @Override
    @NotNull
    public String getLine() {
        return scanner.nextLine();
    }

}
