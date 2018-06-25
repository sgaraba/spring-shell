package com.example.crmshell;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
class ConnectedPromptProvider implements PromptProvider {

    private final CrmService crm;

    ConnectedPromptProvider(CrmService crm) {
        this.crm = crm;
    }

    @Override
    public AttributedString getPrompt() {
        String msg = String.format("spring CRM (%s)> ", this.crm.isConnected() ? "connected" : "disconnected");
        return new AttributedString(msg);
    }
}
