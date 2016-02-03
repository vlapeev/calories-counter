package com.lapeevvd.service;

import com.lapeevvd.util.Profiles;
import com.lapeevvd.util.logger.LoggerWrapper;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(Profiles.POSTGRES)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractServiceTest {

    private final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        private void logInfo(long nanos, Description description){
            LOG.info(String.format("~~~ Test %s, spent %d microseconds",
                    description.getMethodName(), TimeUnit.NANOSECONDS.toMicros(nanos)));
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(nanos, description);
        }
    };
}
