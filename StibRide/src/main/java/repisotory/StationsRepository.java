package repisotory;

import dto.StationsDto;
import jbdc.StationsDao;
import myException.RepositoryException;

import java.io.IOException;
import java.util.List;

public class StationsRepository implements Repository<Integer, StationsDto> {
    private StationsDao dao;
    public StationsRepository() throws RepositoryException {
        this.dao = StationsDao.getInstance();
    }

    public StationsRepository(StationsDao mock) {
        this.dao = mock;
    }

    @Override
    public void add(StationsDto item) throws IOException, RepositoryException {

    }

    @Override
    public void remove(Integer key) throws IOException, RepositoryException {

    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
        return dao.get(key);
    }
    public StationsDto getByName(String name) throws RepositoryException {
        return dao.getByName(name);
    }
    @Override
    public List<StationsDto> getAll() throws RepositoryException {
        return dao.getAll();
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return dao.contains(key);
    }
}
