package daos.mysql;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectionPool {
    // TODO: ensure we're not re-committing this after initial
    // Do NOT push passwords to a public repository
    private final String PROTOCOL = "jdbc:mysql://";
    private final String DBURI = "db.example.com";
    private final String PORT = ":3306";
    private final String DBNAME = "/exampleDB";
    private final String DBUSERNAME = "user";
    private final String DBPASSWORD = "pass";

    // Use MySQL Connector/J driver
    // We probably don't need this, but leaving it here just in case
    private final String DRIVER = "com.mysql.jdbc.Driver";

    private PoolProperties prop = null;
    private DataSource datasource = null;

    public MySQLConnectionPool(){
        // Create our PoolProperties (configuration) object
        prop = new PoolProperties();

        // Define basic configuration information
        prop.setUrl(PROTOCOL + DBURI + PORT + DBNAME);
        prop.setDriverClassName("com.mysql.jdbc.Driver");
        prop.setUsername(DBUSERNAME);
        prop.setPassword(DBPASSWORD);

        // Define logging, max connections, etc
        prop.setTestOnBorrow(true); // validate object before borrowing from pool
        prop.setTestWhileIdle(true); // validate object by idle object evictor; drop if it fails to evaluate
        prop.setTimeBetweenEvictionRunsMillis(30000);
        prop.setValidationQuery("SELECT 1");
        prop.setValidationInterval(30000);
        prop.setJmxEnabled(true); // create connection pool that exposes an MBean
        prop.setTestOnReturn(false);
        prop.setMaxActive(100);
        prop.setInitialSize(10);
        prop.setLogAbandoned(true);
        prop.setRemoveAbandoned(true);
        prop.setRemoveAbandonedTimeout(60);
        prop.setMinIdle(10);

        // ConnectionState:
        // Cache connection for autoCommit, readOnly, transactionIsolation, and catalog;
        // avoids roundtrip to DB when getters are called or setters are called with an already-set value
        // StatementFinalizer:
        // Track all statements created using createStatement,prepareStatement, and prepareCall, and
        // close statements when connection is returned to pool
        prop.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer"
        );

        datasource = new DataSource();
        datasource.setPoolProperties(prop);
    }

    // Use this#getConnection() to get connections, rather than DriverManager.getConnection

    public Connection getConnection() throws SQLException{
        return datasource.getConnection();
        // todo: it might be worthwhile to look into JNDI datasource via Tomcat's Context
    }
}
