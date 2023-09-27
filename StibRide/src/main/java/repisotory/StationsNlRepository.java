package repisotory;

import dto.StationsDto;
import jbdc.StationsDao;
import jbdc.StationsNlDao;
import myException.RepositoryException;

import java.io.IOException;
import java.util.List;

public class StationsNlRepository implements Repository<Integer, StationsDto> {

    private StationsNlDao dao;

    public StationsNlRepository() throws RepositoryException {
        this.dao = StationsNlDao.getInstance();
    }
    public StationsNlRepository(StationsNlDao mock) {
        this.dao = mock;
    }

    @Override
    public void add(StationsDto item) throws RepositoryException {

    }

    @Override
    public void remove(Integer key) throws RepositoryException {

    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
        return dao.get(key);
    }

    @Override
    public List<StationsDto> getAll() throws RepositoryException {
        return dao.getAll();
    }
    public StationsDto getByName(String name) throws RepositoryException {
        return dao.getByName(name);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return dao.contains(key);
    }
}
