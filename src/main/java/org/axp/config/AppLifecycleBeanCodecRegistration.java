package org.axp.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
import com.datastax.oss.driver.api.core.type.codec.registry.MutableCodecRegistry;
import com.datastax.oss.driver.internal.core.type.codec.extras.enums.EnumNameCodec;
import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import org.axp.entity.Ticket;
import org.axp.entity.User;

import static com.datastax.oss.driver.api.core.type.codec.ExtraTypeCodecs.LOCAL_TIMESTAMP_UTC;

@ApplicationScoped
public class AppLifecycleBeanCodecRegistration {

    @Inject
    CqlSession session;

    // to check db before start (CassandraClientStarter) - quarkus.cassandra.init.eager-init=true
    void onStart(@Observes @Priority(Priorities.AUTHORIZATION - 10) StartupEvent ev) {
        TypeCodec<User.Role> roleTypeCodec = new EnumNameCodec<>(User.Role.class);
        TypeCodec<Ticket.Priority> priorityTypeCodec = new EnumNameCodec<>(Ticket.Priority.class);

        MutableCodecRegistry registry = (MutableCodecRegistry) session.getContext().getCodecRegistry();
        registry.register(roleTypeCodec);
        registry.register(priorityTypeCodec);
        registry.register(LOCAL_TIMESTAMP_UTC);
    }

}
