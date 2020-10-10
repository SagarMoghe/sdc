package asdc.hl.database;

import java.sql.Connection;

public interface DBConnection {
    public Connection getConnection();
    public boolean terminateConnection();
}
