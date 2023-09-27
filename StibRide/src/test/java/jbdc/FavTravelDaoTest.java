package jbdc;

import config.ConfigManager;
import dto.FavTravelDto;
import myException.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FavTravelDaoTest {

    private FavTravelDao instance;
    private final FavTravelDto fav1;
    private final FavTravelDto fav2;
    private static final int KEY = 1;
    private final List<FavTravelDto> all;

    FavTravelDaoTest() {
        fav1 = new FavTravelDto("SCHOOL",8742,8032);
        fav2 = new FavTravelDto("NoN",8764,8742);
        all = new ArrayList<>();
        all.add(fav1);
//        all.add(fav2);
        try {
            ConfigManager.getInstance().load();
            instance = FavTravelDao.getInstance();
        } catch (IOException | RepositoryException e) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la base de données de test", e);
        }
    }

    @Test
    void testInsert() {
        try {
            instance.insert(fav2);
            assertNotNull(instance.get(fav2.getName()));
            instance.delete(fav2.getName());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsertException() {
        Assertions.assertThrows(RepositoryException.class,() -> {
            instance.insert(null);
        });
    }

    @Test
    void testDeleteExist() {
        try {
            instance.insert(fav2);
            instance.delete(fav2.getName());
            assertNull(instance.get(fav2.getName()));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteException() {
        Assertions.assertThrows(RepositoryException.class,() -> {
            instance.delete(null);
        });
    }

    @Test
    void testGetExist() {
        FavTravelDto result = null;
        try {
         result = instance.get(fav1.getName());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(result,fav1);
    }
    @Test
    void testGetNotExist() {
        try {
         assertNull(instance.get(fav2.getName()));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testGetException() {
        Assertions.assertThrows(RepositoryException.class,() -> {
            instance.get(null);
        });
    }
    @Test
    void testGetAll() {
        List<FavTravelDto> expected = null;
        try {
            expected = instance.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(expected,all);
    }

    @Test
    void testUpdate() {
        try {
            instance.insert(fav2);
            FavTravelDto fav3 = new FavTravelDto("update",8742,8282);
            instance.update("NoN",fav3);
            assertNotNull(instance.get("update"));
            instance.delete(fav3.getName());
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateExceptionNotName() {
        Assertions.assertThrows(RepositoryException.class,() -> {
            FavTravelDto fav3 = new FavTravelDto("update",8742,8282);
            instance.update(null,fav3);
        });
    }

    @Test
    void testUpdateExceptionNotNewInstance() {
        Assertions.assertThrows(RepositoryException.class,() -> {
            instance.update("SCHOOL",null);
        });
    }

}