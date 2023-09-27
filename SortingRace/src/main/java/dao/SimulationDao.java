package dao;

import dto.SimulationDto;
import exception.RepositoryException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SimulationDao {
    private Connection connexion;

    private SimulationDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static SimulationDao getInstance() throws RepositoryException {
        return SimulationDaoHolder.getInstance();
    }

    public void insert(SimulationDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "Insert into SIMULATION(timestamp,sort_type, max_size) values(?,?,?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            LocalDateTime date = item.getDate();
            Timestamp timestamp = Timestamp.valueOf(date);
            pstmt.setString(1, timestamp.toString());
            pstmt.setString(2, item.getSortType());
            pstmt.setLong(3, item.getMax_size());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public List<SimulationDto> getAll() throws RepositoryException {
        String sql = "SELECT timestamp,sort_type, max_size from SIMULATION";
        List<SimulationDto> simulations = new ArrayList<>();
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LocalDateTime dateTime =rs.getTimestamp(1).toLocalDateTime()    ;
                simulations.add(new SimulationDto(dateTime, rs.getString(2), rs.getLong(3)));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return simulations;

    }

    private static class SimulationDaoHolder {
        private static SimulationDao getInstance() throws RepositoryException {
            return new SimulationDao();
        }
    }
}
