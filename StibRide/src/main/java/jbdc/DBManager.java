package jbdc;
import config.ConfigManager;
import myException.RepositoryException;

import java.sql.*;
public class DBManager {

    private Connection connection;
    public DBManager(){
    }

    public Connection getConnection() throws RepositoryException {
        String jdbcUrl = "jdbc:sqlite:" + ConfigManager.getInstance().getProperties("db.url");
        if(connection == null){
            try {
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException ex) {
                throw new RepositoryException("Connexion imopossible " + ex.getMessage());
            }
        }
        return connection;
    }

    void startTransaction() throws RepositoryException {
        try{
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new RepositoryException("impossible de démarrer la transaction:  " + ex.getMessage());
        }
    }
    void startTransaction(int isolationLevel) throws RepositoryException {
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new RepositoryException("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
            throw new RepositoryException("Impossible de démarrer une transaction: " + ex.getMessage());
        }
    }

    void validateTransaction() throws RepositoryException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new RepositoryException("Impossible de valider la transaction: " + ex.getMessage());
        }
    }

    void cancelTransaction() throws RepositoryException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new RepositoryException("Impossible d'annuler la transaction: " + ex.getMessage());
        }
    }

   public static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    private static class DBManagerHolder {
        private static final DBManager INSTANCE = new DBManager();
    }
}
