package jbdc;

import dto.StationsDto;
import repisotory.Dao;
import myException.RepositoryException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationsDao implements Dao<Integer, StationsDto> {
    private Connection connexion;

    private StationsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }
    public static StationsDao getInstance() throws RepositoryException {
        return StationsDaoHolder.getInstance();
    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
            if(key == null)
                throw new RepositoryException("Aucune clé donnée en paramètre");
            String sql = "SELECT id, name from Stations where id = ? ";
            StationsDto station = null;
            try(PreparedStatement pstmt = connexion.prepareStatement(sql)){
                pstmt.setInt(1,key);
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
        String sql = "SELECT id, name from Stations";
        List<StationsDto> station = new ArrayList<>();
        try(PreparedStatement pstmt = connexion.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                station.add(new StationsDto(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return station;
    }

    public StationsDto getByName(String name) throws RepositoryException{
        if(name == null)
            throw new RepositoryException("Aucune clé donnée en paramètre");
        String sql = "SELECT id, name from Stations where name = ? ";
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

    private static class StationsDaoHolder{
        private static StationsDao getInstance() throws RepositoryException {
            return new StationsDao();
        }
    }
    @Override
    public void insert(StationsDto item) throws IOException, RepositoryException {

    }

    @Override
    public void delete(Integer key) throws IOException, RepositoryException {

    }

    @Override
    public void update(StationsDto item) throws IOException, RepositoryException {

    }
}
