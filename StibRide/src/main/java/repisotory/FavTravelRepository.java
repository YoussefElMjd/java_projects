package repisotory;

import dto.FavTravelDto;
import jbdc.FavTravelDao;
import myException.RepositoryException;

import java.io.IOException;
import java.util.List;

public class FavTravelRepository {

    private FavTravelDao dao;

    public FavTravelRepository() throws RepositoryException {
        this.dao = FavTravelDao.getInstance();
    }

    public FavTravelRepository(FavTravelDao mock) {
        this.dao = mock;
    }

    public void insert(FavTravelDto item) throws RepositoryException {
        if (dao.get(item.getName()) == null)
            dao.insert(item);
    }

    public void delete(String name) throws RepositoryException {
        dao.delete(name);
    }

    public void updateName(String newName, String name) throws RepositoryException {
        if (dao.get(name) != null)
            dao.updateName(newName, name);
    }

    public void updateOrigin(String newOrigin, String name) throws RepositoryException {
        if (dao.get(name) != null)
            dao.updateOrigin(newOrigin, name);
    }

    public void updateDestination(String newDestination, String name) throws RepositoryException {
        if (dao.get(name) != null)
            dao.updateDestination(newDestination, name);
    }

    public FavTravelDto get(String name) throws RepositoryException {
        return dao.get(name);
    }

    public List<FavTravelDto> getAll() throws RepositoryException {
        return dao.getAll();
    }

    public void update(String name, FavTravelDto favTravelDto) throws RepositoryException {
        dao.update(name, favTravelDto);
    }
}
