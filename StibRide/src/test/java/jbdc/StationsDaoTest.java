package jbdc;

import dto.StationsDto;
import config.ConfigManager;
import myException.RepositoryException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StationsDaoTest {
    private StationsDao instance;
    private final StationsDto station1;
    private final StationsDto station2;
    private static final int KEY = 1;
    private final List<StationsDto> all;

    public StationsDaoTest() {
        station1 = new StationsDto(KEY, "Beekkant");
        station2 = new StationsDto(99999, "Error");
        all = new ArrayList<>();
        all.add(station1);
        all.add(station2);
        try {
            ConfigManager.getInstance().load();
            instance = StationsDao.getInstance();
        } catch (IOException | RepositoryException e) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la base de données de test", e);
        }
    }

    @Test
    void testGetExist() throws RepositoryException {
        StationsDto expected = station1;
        StationsDto result = instance.get(KEY);
        assertEquals(expected, result);
    }

    @Test
    void testGetNotExist() throws RepositoryException {
        StationsDto result = instance.get(station2.getKey());
        assertNull(result);
    }

    @Test
    void testGetIncorrectParameter() throws RepositoryException {
        assertThrows(RepositoryException.class, () -> {
            instance.get(null);
        });
    }
    @Test
    void testGetByNameExist() throws RepositoryException {
        StationsDto expected = station1;
        StationsDto result = instance.getByName(station1.getName().toUpperCase());
        assertEquals(expected, result);
    }

    @Test
    void testGetByNameNotExist() throws RepositoryException {
        StationsDto result = instance.getByName(station2.getName().toUpperCase());
        assertNull(result);
    }
    @Test
    void testGetByNameIncorrectParameter() throws RepositoryException {
        assertThrows(RepositoryException.class, () -> {
            instance.getByName(null);
        });
    }
    @Test
    void testContainsTrue() throws RepositoryException {
        assertTrue(instance.contains(KEY));
    }

    @Test
    void testContainsFalse() throws RepositoryException {
        assertFalse(instance.contains(9999));
    }

    @Test
    void testContainsIncorrectParameter() throws RepositoryException {
        assertThrows(RepositoryException.class, () -> {
            assertTrue(instance.contains(null));
        });
    }

    @Test
    void getAll() {
        List<StationsDto> expected = List.of(new StationsDto(1,"BEEKKANT"),new StationsDto(2,"ETANGS NOIRS"),
                new StationsDto(3,"COMPTE DE FLANDRE"),new StationsDto(4,"SAINT-CATHERINE"),
                new StationsDto(5,"OSSEGEM"),new StationsDto(6,"BELGICA"),
                new StationsDto(7,"PANNENHUIS"));

        List<StationsDto> result = null;
        try {
            result = instance.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(expected,result);
    }
}