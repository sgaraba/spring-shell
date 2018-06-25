package com.example.crmshell;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
class PersonConverter implements Converter<String, Person> {

    private final CrmService crm;

    private final Pattern pattern = Pattern.compile("\\(#(\\d+)\\).*");

    PersonConverter(CrmService crm) {
        this.crm = crm;
    }
    // (#42) foo bar

    @Nullable
    @Override
    public Person convert(String source) {

        Matcher matcher = this.pattern.matcher(source);
        if (matcher.find()) {
            String group = matcher.group(1);
            if (StringUtils.hasText(group)) {
                Long id = Long.parseLong(group);
                return this.crm.findById(id);
            }
        }

        return null;
    }
}

