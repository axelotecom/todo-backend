package com.axelote.demo.config.axelote;

import com.axelote.adapter.database.Executor;
import com.axelote.adapter.database.decorators.AxeloteExecutorDecorator;
import com.axelote.adapter.dto.CommandDto;
import com.axelote.commons.enums.QueryType;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CustomAxeloteExecutorDecorator implements AxeloteExecutorDecorator {

    private final CacheManager cacheManager;

    public CustomAxeloteExecutorDecorator(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    @Cacheable(value = "todos")
    public Object invoke(Executor executor, CommandDto commandDto) {
        if (!commandDto.getQueryType().equals(QueryType.RETURNING)) {
            clearCache();
        }
        return executor.invoke(commandDto);
    }

    private void clearCache() {
        Cache cache = cacheManager.getCache("todos");
        if (cache != null) {
            cache.clear();
        }
    }
}
