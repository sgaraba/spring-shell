package com.example.crmshell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
class ConnectionCommands {

    private final ConsoleService console;
    private final CrmService crm;

    ConnectionCommands(ConsoleService console, CrmService crm) {
        this.console = console;
        this.crm = crm;
    }

    @ShellMethod("connect to the CRM")
    public void connect(String username, String password) {
        this.crm.connect(username, password);
        this.console.write("connected %s.", username);
    }


    @ShellMethod("disconnect from the CRM")
    public void disconnect() {
        this.crm.disconnect();
    }

    Availability connectAvailability() {
        return !this.crm.isConnected() ?
                Availability.available() : Availability.unavailable("you're already connected");
    }

    Availability disconnectAvailability() {
        return this.crm.isConnected() ?
                Availability.available() : Availability.unavailable("you're not connected");
    }

}
