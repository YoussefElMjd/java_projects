package jbdc;

import dto.FavTravelDto;
import myException.RepositoryException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavTravelDao {
    private Connection connexion;

    private FavTravelDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }


    public static FavTravelDao getInstance() throws RepositoryException {
        return FavTravelDao.FavTravelDaoHolder.getInstance();
    }

    public void insert(FavTravelDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "Insert into FavTravel values(?,?,?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getOrigin());
            pstmt.setInt(3, item.getDest());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public void delete(String name) throws RepositoryException {
        if (name == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM FAVTRAVEL WHERE name = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void updateName(String newName, String name) throws RepositoryException {
        if (newName == null && name == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE FAVTRAVEL SET name=? where name=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void updateOrigin(String newOrigin, String name) throws RepositoryException {
        if (newOrigin == null && name == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE FAVTRAVEL SET origin=? where name=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, newOrigin);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void updateDestination(String newDestination, String name) throws RepositoryException {
        if (newDestination == null && name == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE FAVTRAVEL SET destination=? where name=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, newDestination);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public FavTravelDto get(String name) throws RepositoryException {
        if (name == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "Select name, origin, destination " +
                "from FAVTRAVEL  " +
                "where name = ?";
        FavTravelDto fav = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                fav = new FavTravelDto(rs.getString(1), rs.getInt(2), rs.getInt(2));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return fav;
    }

    public List<FavTravelDto> getAll() throws RepositoryException {
        String sql = "SELECT name, origin, destination from Favtravel";
        List<FavTravelDto> dtos = new ArrayList<>();
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dtos.add(new FavTravelDto(rs.getString(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return dtos;
    }

    public void update(String name, FavTravelDto favTravelDto) throws RepositoryException {
        if (favTravelDto == null || name == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE FAVTRAVEL SET name=?, origin = ?, destination = ? where name=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, favTravelDto.getName());
            pstmt.setInt(2, favTravelDto.getOrigin());
            pstmt.setInt(3, favTravelDto.getDest());
            pstmt.setString(4, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private static class FavTravelDaoHolder {
        private static FavTravelDao getInstance() throws RepositoryException {
            return new FavTravelDao();
        }
    }

}
