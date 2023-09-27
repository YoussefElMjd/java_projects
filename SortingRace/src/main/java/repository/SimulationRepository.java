package repository;

import dao.SimulationDao;
import dto.SimulationDto;
import exception.RepositoryException;

import java.util.List;

public class SimulationRepository {
    private SimulationDao dao;

    public SimulationRepository() throws RepositoryException {
        this.dao = SimulationDao.getInstance();
    }
    public void insert(SimulationDto item) throws RepositoryException {
        this.dao.insert(item);
    }
    public List<SimulationDto> getAll() throws RepositoryException {
        return dao.getAll();
    }
}
