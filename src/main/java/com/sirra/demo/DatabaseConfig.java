package com.sirra.demo;

import com.zaxxer.hikari.*;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        config.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
        config.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    @Bean
    public DataSource dataSource() {
        return ds;
    }
}
