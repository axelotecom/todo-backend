package com.axelote.demo.config.axelote;

import com.axelote.adapter.database.decorators.AxeloteParamsDecorator;
import com.axelote.adapter.dto.CommandDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomAxeloteParamsDecorator implements AxeloteParamsDecorator {

    private final CustomAxeloteSecurityContextProvider customAxeloteSecurityContextProvider;

    public CustomAxeloteParamsDecorator(CustomAxeloteSecurityContextProvider customAxeloteSecurityContextProvider) {
        this.customAxeloteSecurityContextProvider = customAxeloteSecurityContextProvider;
    }

    @Override
    public Map<String, Object> modifyParams(CommandDto commandDto) {
        Map<String, Object> params = commandDto.getParams();
        params.put("username", customAxeloteSecurityContextProvider.getUserContext().getUsername());
        return commandDto.getParams();
    }
}
