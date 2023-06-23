/*package com.crud.app.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class SqlScriptRunner implements CommandLineRunner {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        Resource scriptResource = resourceLoader.getResource("classpath:init.sql");
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, scriptResource);
        }
    }
}
*/