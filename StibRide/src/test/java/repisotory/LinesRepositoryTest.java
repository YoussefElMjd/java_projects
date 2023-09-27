package repisotory;

import dto.LinesDto;
import dto.StationsDto;
import jbdc.LinesDao;
import jbdc.StationsDao;
import myException.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class LinesRepositoryTest {
    @Mock
    private LinesDao mock;
    private final LinesDto line1;
    private final LinesDto line2;
    private static final int KEY = 1;
    private final List<LinesDto> all;
    private LinesRepository repository;

    public LinesRepositoryTest() {
        line1 = new LinesDto(KEY);
        line2 = new LinesDto(99999);
        all = new ArrayList<>();
        all.add(line1);
        all.add(line2);
    }

    @BeforeEach
    void init() throws RepositoryException, IOException {
        Mockito.lenient().when(mock.get(line1.getKey())).thenReturn(line1);
        Mockito.lenient().when(mock.get(line2.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.getAll()).thenReturn(all);
        Mockito.lenient().when(mock.get(null)).thenThrow(RepositoryException.class);
        Mockito.lenient().when(mock.contains(line1.getKey())).thenReturn(true);
        Mockito.lenient().when(mock.contains(line2.getKey())).thenReturn(false);
        Mockito.lenient().when(mock.contains(null)).thenReturn(false);
    }

    @Test
    void testGetExist() throws RepositoryException, IOException {
        LinesRepository repository = new LinesRepository(mock);
        LinesDto expected = line1;
        LinesDto result = repository.get(KEY);
        assertEquals(expected,result);
        Mockito.verify(mock,Mockito.times(1)).get(KEY);
    }

    @Test
    void testGetNotExist() throws RepositoryException, IOException {
        LinesRepository repository = new LinesRepository(mock);
        LinesDto result = repository.get(line2.getKey());
        assertNull(result);
        Mockito.verify(mock,Mockito.times(1)).get(line2.getKey());
    }
    @Test
    void testGetIncorrectParameter() throws RepositoryException, IOException {
        LinesRepository repository = new LinesRepository(mock);
        Assertions.assertThrows(RepositoryException.class,() -> {
            repository.get(null);
            Mockito.verify(mock,Mockito.times(1)).get(null);
        });
    }

    @Test
    void testContainsTrue() throws RepositoryException, IOException {
        LinesDto expected = line1;
        LinesRepository repository = new LinesRepository(mock);
        LinesDto result = repository.get(KEY);
        assertTrue(repository.contains(KEY));
        Mockito.verify(mock,Mockito.times(1)).contains(KEY);
    }
    @Test
    void testContainsFalse() throws RepositoryException, IOException {
        LinesDto expected = line1;
        LinesRepository repository = new LinesRepository(mock);
        LinesDto result = repository.get(9999);
        assertFalse(repository.contains(9999));
        Mockito.verify(mock,Mockito.times(1)).contains(9999);
    }

    @Test
    void getAll() throws RepositoryException, IOException {
        LinesRepository repository = new LinesRepository(mock);
        assertEquals(all,repository.getAll());
        Mockito.verify(mock,Mockito.times(1)).getAll();
    }
}