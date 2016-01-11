package com.lapeevvd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;

import javax.sql.DataSource;


public class DBPopulator extends ResourceDatabasePopulator{
    private static final ResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();

    @Autowired
    private DataSource dataSource;

    public DBPopulator(String scriptLocation) {
        super(RESOURCE_LOADER.getResource(scriptLocation));
    }

    public void execute() throws ScriptException {
        DatabasePopulatorUtils.execute(this, dataSource);
    }
}
