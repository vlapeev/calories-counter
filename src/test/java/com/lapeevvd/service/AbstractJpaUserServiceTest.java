package com.lapeevvd.service;

import com.lapeevvd.repository.datajpa.JpaUtil;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJpaUserServiceTest extends AbstractUserServiceTest{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    protected JpaUtil jpaUtil;

    @Before
    public void setUp(){
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
