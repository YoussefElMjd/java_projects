package repisotory;

import config.ConfigManager;
import dto.FavTravelDto;
import jbdc.FavTravelDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class FavTravelRepositoryTest {

    @Mock
    private FavTravelDao mock;

    private final FavTravelDto fav1;
    private final FavTravelDto fav2;
    private static final String KEY = "SCHOOL";
    private final List<FavTravelDto> all;

    public FavTravelRepositoryTest() {
        fav1 = new FavTravelDto("SCHOOL", 8742, 8032);
        fav2 = new FavTravelDto("NoN", 8764, 8742);
        all = new ArrayList<>();
        all.add(fav1);
    }

    @BeforeEach
    void init() throws RepositoryException, IOException {
        Mockito.lenient().when(mock.get(fav1.getName())).thenReturn(fav1);
        Mockito.lenient().when(mock.get(fav2.getName())).thenReturn(null);
        Mockito.lenient().when(mock.getAll()).thenReturn(all);
        Mockito.lenient().when(mock.get(null)).thenThrow(RepositoryException.class);
    }

    @Test
    void testInsert() {
        FavTravelRepository instance = new FavTravelRepository(mock);
        try {
            instance.insert(fav2);
            Mockito.verify(mock, Mockito.times(1)).insert(fav2);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testDeleteExist() {
        try {
            FavTravelRepository instance = new FavTravelRepository(mock);
            instance.insert(fav2);
            instance.delete(fav2.getName());
            assertNull(instance.get(fav2.getName()));
            Mockito.verify(mock, Mockito.times(1)).delete(fav2.getName());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetExist() {
        FavTravelDto result = null;
        FavTravelRepository instance = new FavTravelRepository(mock);
        try {
            result = instance.get(fav1.getName());
            assertEquals(result, fav1);
            Mockito.verify(mock, Mockito.times(1)).get(fav1.getName());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetNotExist() {
        FavTravelRepository instance = new FavTravelRepository(mock);
        try {
            assertNull(instance.get(fav2.getName()));
            Mockito.verify(mock, Mockito.times(1)).get(fav2.getName());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAll() {
        FavTravelRepository instance = new FavTravelRepository(mock);
        List<FavTravelDto> expected = null;
        try {
            expected = instance.getAll();
            Mockito.verify(mock, Mockito.times(1)).getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(expected, all);
    }

    @Test
    void testUpdate() {
        FavTravelRepository instance = new FavTravelRepository(mock);
        try {
            instance.insert(fav2);
            FavTravelDto fav3 = new FavTravelDto("update", 8742, 8282);
            instance.update("NoN", fav3);
            instance.delete(fav3.getName());
            Mockito.verify(mock, Mockito.times(1)).update("NoN", fav3);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

}