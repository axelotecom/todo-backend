package com.axelote.demo.config.axelote;

import com.axelote.adapter.services.rules.security.context.AxeloteSecurityContextProvider;
import com.axelote.adapter.services.rules.security.context.DefaultAxeloteSecurityContextProvider;
import com.axelote.commons.enums.QueryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomAxeloteSecurityContextProvider implements AxeloteSecurityContextProvider<UserDetails> {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomAxeloteSecurityContextProvider.class);

    @Override
    public UserDetails getUserContext() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void throwUnauthorizedException(String ruleName, String query, UserDetails userContext, Map<String, Object> params, QueryType queryType) {
        LOGGER.error(DefaultAxeloteSecurityContextProvider.LOGGER_MSG, queryType, userContext, params, query, ruleName);
        throw new RuntimeException("Unauthorized");
    }
}
