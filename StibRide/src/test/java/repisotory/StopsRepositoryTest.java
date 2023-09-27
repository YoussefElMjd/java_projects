package repisotory;

import dto.StationsDto;
import dto.StopsDto;
import jbdc.StationsDao;
import jbdc.StopsDao;
import myException.RepositoryException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class StopsRepositoryTest {
    @Mock
    private StopsDao mock;

    private List<List<StopsDto>> graph;

    @BeforeEach
    void init() throws RepositoryException, IOException {
        Mockito.lenient().when(mock.constructGraph("FR")).thenReturn(graph);
    }

    @Test
    void testConstructGraph() throws RepositoryException {
        StopsRepository repository = new StopsRepository(mock);
        List<List<StopsDto>> graph = repository.constructGraph("FR");
        Mockito.verify(mock,Mockito.times(1)).constructGraph("FR");
    }

}