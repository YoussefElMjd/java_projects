//package repisotory;
//
//import dto.StationsDto;
//import dto.StationsDto;
//import jbdc.StationsDao;
//import jbdc.StationsNlDao;
//import myException.RepositoryException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.platform.runner.JUnitPlatform;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
//class StationsNlRepositoryTest {
//    @Mock
//    private StationsNlDao mock;
//    private final StationsDto station1;
//    private final StationsDto station2;
//    private static final int KEY = 8742;
//    private final List<StationsDto> all;
//
//    public StationsNlRepositoryTest() {
//        station1 = new StationsDto(KEY,"Beekkant");
//        station2 = new StationsDto(99999, "Error");
//
//        all = new ArrayList<>();
//        all.add(station1);
//        all.add(station2);
//    }
//
//    @BeforeEach
//    void init() throws RepositoryException, IOException {
//        Mockito.lenient().when(mock.get(station1.getKey())).thenReturn(station1);
//        Mockito.lenient().when(mock.get(station2.getKey())).thenReturn(null);
//        Mockito.lenient().when(mock.getAll()).thenReturn(all);
//        Mockito.lenient().when(mock.getByName(station1.getName())).thenReturn(station1);
//        Mockito.lenient().when(mock.getByName(station2.getName())).thenReturn(null);
//        Mockito.lenient().when(mock.contains(station1.getKey())).thenReturn(true);
//        Mockito.lenient().when(mock.get(null)).thenThrow(RepositoryException.class);
//        Mockito.lenient().when(mock.getByName(null)).thenThrow(RepositoryException.class);
//        Mockito.lenient().when(mock.contains(station2.getKey())).thenReturn(false);
//        Mockito.lenient().when(mock.contains(null)).thenReturn(false);
//    }
//    @Test
//    void testGetExist() throws RepositoryException {
//        StationsDto expected = station1;
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        StationsDto result = repository.get(KEY);
//
//        assertEquals(expected,result);
//        Mockito.verify(mock,Mockito.times(1)).get(KEY);
//    }
//
//    @Test
//    void testGetNotExist() throws RepositoryException, IOException {
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        StationsDto result = repository.get(station2.getKey());
//        assertNull(result);
//        Mockito.verify(mock,Mockito.times(1)).get(station2.getKey());
//    }
//    @Test
//    void testGetIncorrectParameter() throws RepositoryException, IOException {
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        Assertions.assertThrows(RepositoryException.class,() -> {
//            repository.get(null);
//            Mockito.verify(mock,Mockito.times(1)).get(null);
//        });
//    }
//    @Test
//    void testGetByNameExist() throws RepositoryException {
//        StationsDto expected = station1;
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        StationsDto result = repository.getByName(station1.getName());
//
//        assertEquals(expected,result);
//        Mockito.verify(mock,Mockito.times(1)).getByName(station1.getName());
//    }
//
//    @Test
//    void testGetByNameNotExist() throws RepositoryException, IOException {
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        StationsDto result = repository.getByName(station2.getName());
//        assertNull(result);
//        Mockito.verify(mock,Mockito.times(1)).getByName(station2.getName());
//    }
//    @Test
//    void testGetByNameIncorrectParameter() throws RepositoryException, IOException {
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        Assertions.assertThrows(RepositoryException.class,() -> {
//            repository.getByName(null);
//            Mockito.verify(mock,Mockito.times(1)).getByName(null);
//        });
//    }
//
//    @Test
//    void testContainsTrue() throws RepositoryException, IOException {
//        StationsDto expected = station1;
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        StationsDto result = repository.get(KEY);
//        assertTrue(repository.contains(KEY));
//        Mockito.verify(mock,Mockito.times(1)).contains(KEY);
//    }
//    @Test
//    void testContainsFalse() throws RepositoryException, IOException {
//        StationsDto expected = station1;
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        StationsDto result = repository.get(99999);
//        assertFalse(repository.contains(99999));
//        Mockito.verify(mock,Mockito.times(1)).contains(99999);
//    }
//
//
//    @Test
//    void getAll() throws RepositoryException, IOException {
//        StationsNlRepository repository = new StationsNlRepository(mock);
//        assertEquals(all,repository.getAll());
//        Mockito.verify(mock,Mockito.times(1)).getAll();
//    }
//
//}