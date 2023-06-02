package com.axelote.demo.rules;

import com.axelote.adapter.services.rules.AxeloteRule;
import com.axelote.commons.enums.QueryType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserContextRule implements AxeloteRule<UserDetails> {

    @Override
    public boolean execute(String query, Map<String, Object> params, QueryType queryType, UserDetails userContext) {
        return params.get("username").equals(userContext.getUsername());
    }
}
