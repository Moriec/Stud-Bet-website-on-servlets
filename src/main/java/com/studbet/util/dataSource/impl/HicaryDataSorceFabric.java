package com.studbet.util.dataSource.impl;

import com.studbet.util.dataSource.DataSourceFabric;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HicaryDataSorceFabric implements DataSourceFabric {

    public DataSource getDataSource(String url, String username, String password) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(url);
        cfg.setUsername(username);
        cfg.setPassword(password);
        cfg.setMaximumPoolSize(10);
        cfg.setMinimumIdle(2);
        return new HikariDataSource(cfg);

    }
}
