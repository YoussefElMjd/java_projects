package repisotory;

import dto.StopsDto;
import jbdc.StopsDao;
import myException.RepositoryException;

import java.util.List;

public class StopsRepository {
    private final StopsDao dao;

    public StopsRepository() throws RepositoryException {
        dao = StopsDao.getInstance();
    }

    public StopsRepository(StopsDao dao) {
        this.dao = dao;
    }
    
    public List<List<StopsDto>>  constructGraph(String language) throws RepositoryException {
        List<List<StopsDto>> stops = dao.constructGraph(language);
        return stops;
    }

}
