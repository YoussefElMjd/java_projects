package repisotory;

import dto.LinesDto;
import jbdc.LinesDao;
import myException.RepositoryException;

import java.io.IOException;
import java.util.List;

public class LinesRepository implements Repository<Integer, LinesDto> {
    private LinesDao dao;

    public LinesRepository() throws RepositoryException {
        this.dao = LinesDao.getInstance();
    }

    public LinesRepository(LinesDao dao){
        this.dao = dao;
    }
    @Override
    public void add(LinesDto item) throws IOException, RepositoryException {

    }

    @Override
    public void remove(Integer key) throws IOException, RepositoryException {

    }

    @Override
    public LinesDto get(Integer key) throws IOException, RepositoryException {
        return dao.get(key);
    }

    @Override
    public List<LinesDto> getAll() throws IOException, RepositoryException {
        return dao.getAll();
    }

    @Override
    public boolean contains(Integer key) throws IOException, RepositoryException {
        return dao.contains(key);
    }

}
