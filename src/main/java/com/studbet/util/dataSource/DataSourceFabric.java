package com.studbet.util.dataSource;

import javax.sql.DataSource;

public interface DataSourceFabric {
    DataSource getDataSource(String url, String username, String password) throws ClassNotFoundException;
}
