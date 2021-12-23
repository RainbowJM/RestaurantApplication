package com.restaurant.TableService.core.port;

import com.restaurant.TableService.core.domain.event.TableEvent;

public interface TableEventPublisher {
    void publish(TableEvent event);
}
