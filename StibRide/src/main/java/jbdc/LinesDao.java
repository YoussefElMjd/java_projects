package jbdc;

import dto.LinesDto;
import repisotory.Dao;
import myException.RepositoryException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinesDao implements Dao<Integer, LinesDto> {
    private Connection connexion;
    public LinesDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static LinesDao getInstance() throws RepositoryException {
        return LinesDaoHolder.getInstance();
    }

    @Override
    public LinesDto get(Integer key) throws IOException, RepositoryException {
        if(key == null)
            throw new RepositoryException("Aucune clé donnée en paramètre");
        String sql = "SELECT id from Lines where id = ? ";
        LinesDto lines = null;
        try(PreparedStatement pstmt = connexion.prepareStatement(sql)){
            pstmt.setInt(1,key);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lines = new LinesDto(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return lines;
    }

    @Override
    public boolean contains(Integer key) throws IOException, RepositoryException {
        return get(key) != null;
    }

    @Override
    public List<LinesDto> getAll() throws IOException, RepositoryException {
        String sql = "SELECT id from Lines";
        List<LinesDto> lines = new ArrayList<>();
        try(PreparedStatement pstmt = connexion.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lines.add(new LinesDto(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return lines;
    }

    @Override
    public void insert(LinesDto item) throws IOException, RepositoryException {
        //
    }

    @Override
    public void delete(Integer key) throws IOException, RepositoryException {
        //
    }

    @Override
    public void update(LinesDto item) throws IOException, RepositoryException {
        //
    }
    public static class LinesDaoHolder{
        public static LinesDao getInstance() throws RepositoryException {
            return new LinesDao();
        }
    }
}
