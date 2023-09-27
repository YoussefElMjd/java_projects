package jbdc;

import dto.StationsDto;
import myException.RepositoryException;
import repisotory.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationsNlDao implements Dao<Integer, StationsDto> {

    private Connection connexion;

    private StationsNlDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static StationsNlDao getInstance() throws RepositoryException {
        return StationsNlDao.StationsNlDaoHolder.getInstance();
    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
        if (key == null)
            throw new RepositoryException("Aucune clé donnée en paramètre");
        String sql = "SELECT id, name from Stations_NL where id = ? ";
        StationsDto station = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                station = new StationsDto(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return station;
    }

    public StationsDto getByName(String name) throws RepositoryException{
        if(name == null)
            throw new RepositoryException("Aucune clé donnée en paramètre");
        String sql = "SELECT id, name from Stations_NL where name = ? ";
        StationsDto station = null;
        try(PreparedStatement pstmt = connexion.prepareStatement(sql)){
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                station = new StationsDto(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return station;
    }
    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return get(key) != null;
    }

    @Override
    public List<StationsDto> getAll() throws RepositoryException {
        String sql = "SELECT id, name from Stations_NL";
        List<StationsDto> station = new ArrayList<>();
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                station.add(new StationsDto(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return station;
    }

    @Override
    public void insert(StationsDto item) throws RepositoryException {

    }

    @Override
    public void delete(Integer key) throws RepositoryException {

    }

    @Override
    public void update(StationsDto item) throws RepositoryException {

    }

    private static class StationsNlDaoHolder {
        private static StationsNlDao getInstance() throws RepositoryException {
            return new StationsNlDao();
        }
    }
}
