package com.example.crmshell;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class PersonValueProvider implements ValueProvider {

    private final CrmService crm;

    PersonValueProvider(CrmService crm) {
        this.crm = crm;
    }

    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
        return parameter.getParameterType().isAssignableFrom(Person.class);
    }

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter,
                                             CompletionContext completionContext,
                                             String[] hints) {
        String currentInput = completionContext.currentWordUpToCursor();
        return this.crm
                .findByName(currentInput)
                .stream()
                .map(p -> String.format("(#%s) %s", p.getId(), p.getName()))
                .map(CompletionProposal::new)
                .collect(Collectors.toList());
    }
}
