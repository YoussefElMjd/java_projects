package jbdc;

import dto.LinesDto;
import dto.StationsDto;
import dto.StopsDto;
import myException.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StopsDao {

    private final Connection connexion;

    private StopsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static StopsDao getInstance() throws RepositoryException {
        return StopsDaoHolder.getInstance();
    }

    public List<List<StopsDto>> constructGraph(String language) throws RepositoryException {
        String sql = "";
        if (language.equals("FR")) {
            sql = "Select s.id_line, s.id_station, s.id_order, station.name,s2.id_line, s2.id_station, s2.id_order, station2.name " +
                    "from STOPS s " +
                    "Join STOPS s2 on s.id_line = s2.id_line " +
                    "Join STATIONS station on s.id_station = station.id " +
                    "Join STATIONS station2 on s2.id_station = station2.id " +
                    "where (s.id_order = s2.id_order +1 or s.id_order = s2.id_order -1)" +
                    "Order by s.id_station, s.id_line, s.id_order";
        } else {
            sql = "Select s.id_line, s.id_station, s.id_order, station.name,s2.id_line, s2.id_station, s2.id_order, station2.name " +
                    "from STOPS s " +
                    "Join STOPS s2 on s.id_line = s2.id_line " +
                    "Join STATIONS_NL station on s.id_station = station.id " +
                    "Join STATIONS_NL station2 on s2.id_station = station2.id " +
                    "where (s.id_order = s2.id_order +1 or s.id_order = s2.id_order -1)" +
                    "Order by s.id_station, s.id_line, s.id_order";
        }
        List<List<StopsDto>> stops = new ArrayList<>();
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            List<StopsDto> allNeighbour = new ArrayList<>();
            boolean needToSaveStation = true;
            int saveStationId = rs.getInt(2);
            while (rs.next()) {
                LinesDto line = new LinesDto(rs.getInt(1));
                StationsDto stations1 = new StationsDto(rs.getInt(2), rs.getString(4));
                StationsDto stations2 = new StationsDto(rs.getInt(6), rs.getString(8));
                if (rs.getInt(2) != saveStationId) {
                    stops.add(allNeighbour);
                    allNeighbour = new ArrayList<>();
                    needToSaveStation = true;
                }
                if (needToSaveStation) {
                    saveStationId = rs.getInt(2);
                    needToSaveStation = false;
                }
                allNeighbour.add(new StopsDto(line, stations1, rs.getInt(3), new StopsDto(new LinesDto(rs.getInt(5)), stations2, rs.getInt(7))));


            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return stops;
    }


    private static class StopsDaoHolder {
        private static StopsDao getInstance() throws RepositoryException {
            return new StopsDao();
        }

    }
}
