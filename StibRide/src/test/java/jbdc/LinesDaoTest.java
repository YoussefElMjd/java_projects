package jbdc;

import dto.LinesDto;
import config.ConfigManager;
import myException.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinesDaoTest {
    private LinesDao instance;
    private final LinesDto lines1;
    private final LinesDto lines2;
    private static final int KEY = 1;
    private final List<LinesDto> all;

    public LinesDaoTest() {
        lines1 = new LinesDto(KEY);
        lines2 = new LinesDto(99999);
        all = new ArrayList<>();
        all.add(lines1);
        all.add(lines2);
        try {
            ConfigManager.getInstance().load();
            instance = LinesDao.getInstance();
        } catch (IOException | RepositoryException e) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la base de données de test", e);
        }
    }

    @Test
    void testGetExist() throws RepositoryException, IOException {
        LinesDto expected = lines1;
        assertEquals(expected,instance.get(KEY));
    }
    @Test
    void testGetNotExist() throws RepositoryException, IOException {
        assertNull(instance.get(lines2.getKey()));
    }
    @Test
    void testGetIncorrectParameter() throws RepositoryException, IOException {
        Assertions.assertThrows(RepositoryException.class,() -> {
            instance.get(null);
        });
    }

    @Test
    void testContainsTrue() throws RepositoryException, IOException {
        assertTrue(instance.contains(KEY));
    }
    @Test
    void testContainsFalse() throws RepositoryException, IOException {
        assertFalse(instance.contains(lines2.getKey()));
    }
    @Test
    void testContainsIncorrectParameter() throws RepositoryException, IOException {
        Assertions.assertThrows(RepositoryException.class,() -> {
            instance.contains(null);
        });
    }

    @Test
    void getAll() throws RepositoryException, IOException {
        List<LinesDto> expetecd = List.of(new LinesDto(1),new LinesDto(2),new LinesDto(5),new LinesDto(6));
        assertEquals(expetecd,instance.getAll());
    }
}