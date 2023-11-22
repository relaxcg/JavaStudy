package com.relaxcg.multidatasource.ards.config;

import com.relaxcg.multidatasource.ards.base.DataSourceContext;
import org.apache.ibatis.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author relaxcg
 * @date 2023/11/22 14:20
 */
public class MultiDataSourceTransaction implements Transaction {
    private final DataSource dataSource;
    private final Map<String, Connection> connectionMap;
    private boolean autoCommit;

    public MultiDataSourceTransaction(DataSource dataSource) {
        this.dataSource = dataSource;
        this.connectionMap = new ConcurrentHashMap<>();
    }

    @Override
    public Connection getConnection() throws SQLException {
        String dsName = DataSourceContext.get();
        Connection connection = this.connectionMap.get(dsName);
        if (connection == null) {
            connection = this.dataSource.getConnection();
            this.autoCommit = false;
            connection.setAutoCommit(false);
            this.connectionMap.put(dsName, connection);
        }
        return connection;
    }

    @Override
    public void commit() throws SQLException {

    }

    @Override
    public void rollback() throws SQLException {

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public Integer getTimeout() throws SQLException {
        return null;
    }
}
