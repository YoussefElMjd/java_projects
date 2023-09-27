package jbdc;

import dto.LinesDto;
import dto.StationsDto;
import dto.StopsDto;
import config.ConfigManager;
import myException.RepositoryException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StopsDaoTest {
    // il n y a pas de id unique, on fait comment ? trois méthodes comme je les fait ou un get avec trois paramètre.
    private StopsDao instance;

    public StopsDaoTest(){
        try {
            ConfigManager.getInstance().load();
            instance = StopsDao.getInstance();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testConstructGraphSameLine(){
        List<List<StopsDto>> expected = new ArrayList<>();
        List<StopsDto> toAdd = new ArrayList<>();
        toAdd.add(new StopsDto(new LinesDto(1), new StationsDto(1,"BEEKKANT"), 1, new StopsDto(new LinesDto(1),new StationsDto(2,"ETANGS NOIRS"),2)));
        toAdd.add(new StopsDto(new LinesDto(1), new StationsDto(1,"BEEKKANT"), 1, new StopsDto(new LinesDto(6),new StationsDto(5,"OSSEGEM"),2)));
        expected.add(toAdd);
        List<StopsDto> toAdd2 = new ArrayList<>();
        toAdd2.add(new StopsDto(new LinesDto(1), new StationsDto(2,"ETANGS NOIRS"), 2, new StopsDto(new LinesDto(1), new StationsDto(1,"BEEKKANT"), 1)));
        toAdd2.add(new StopsDto(new LinesDto(1), new StationsDto(2,"ETANGS NOIRS"), 2, new StopsDto(new LinesDto(1), new StationsDto(3,"COMPTE DE FLANDRE"), 3)));
        expected.add(toAdd2);
        List<StopsDto> toAdd3 = new ArrayList<>();
        toAdd3.add(new StopsDto(new LinesDto(1), new StationsDto(3,"COMPTE DE FLANDRE"), 3, new StopsDto(new LinesDto(1),new StationsDto(2,"ETANGS NOIRS"),2)));
        toAdd3.add(new StopsDto(new LinesDto(1), new StationsDto(3,"COMPTE DE FLANDRE"), 3, new StopsDto(new LinesDto(1), new StationsDto(4,"SAINT-CATHERINE"), 4)));
        expected.add(toAdd3);
        List<StopsDto> toAdd4 = new ArrayList<>();
        toAdd4.add( new StopsDto(new LinesDto(1), new StationsDto(4,"SAINT-CATHERINE"), 4, new StopsDto(new LinesDto(1), new StationsDto(3,"COMPTE DE FLANDRE"), 3)));
        expected.add(toAdd4);
        List<StopsDto> toAdd5 = new ArrayList<>();
        toAdd5.add(new StopsDto(new LinesDto(6),new StationsDto(5,"OSSEGEM"),2,new StopsDto(new LinesDto(1), new StationsDto(1,"BEEKKANT"), 1)));
        toAdd5.add(new StopsDto(new LinesDto(6),new StationsDto(5,"OSSEGEM"),2, new StopsDto(new LinesDto(6), new StationsDto(6,"BELGICA"), 3)));
        expected.add(toAdd5);
        List<StopsDto> toAdd6 = new ArrayList<>();
        toAdd6.add(new StopsDto(new LinesDto(6), new StationsDto(6,"BELGICA"), 3,new StopsDto(new LinesDto(6),new StationsDto(5,"OSSEGEM"),2)));
        toAdd6.add(new StopsDto(new LinesDto(6), new StationsDto(6,"BELGICA"), 3, new StopsDto(new LinesDto(6), new StationsDto(7,"PANNENHUIS"), 4)));
        expected.add(toAdd6);
        List<List<StopsDto>> result = null;
        try {
            result = instance.constructGraph("FR");
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        assertEquals(result,expected);

    }
}