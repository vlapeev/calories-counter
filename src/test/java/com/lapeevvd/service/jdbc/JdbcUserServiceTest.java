package com.lapeevvd.service.jdbc;

import com.lapeevvd.service.AbstractUserServiceTest;
import com.lapeevvd.util.Profiles;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest{
}
