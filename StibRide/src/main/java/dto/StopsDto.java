package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StopsDto {
    private LinesDto id_line;
    private  StationsDto id_station;
    private int id_order;
    private StopsDto stopsDest;

    public StopsDto(LinesDto line, StationsDto station, int order) {
        this.id_line = line;
        this.id_station = station;
        this.id_order = order;
        this.stopsDest = null;
    }

    public StopsDto(LinesDto line, StationsDto station, int order, StopsDto id_station2) {
        this.id_line = line;
        this.id_station = station;
        this.id_order = order;
        this.stopsDest = id_station2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StopsDto)) return false;
        StopsDto stopsDto = (StopsDto) o;
        return getId_order() == stopsDto.getId_order() && getId_station().equals(stopsDto.getId_station()) && Objects.equals(getStopsDest(), stopsDto.getStopsDest());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_station(), getId_order(), getStopsDest());
    }

    public int getId_order() {
        return id_order;
    }

    public LinesDto getId_line() {
        return id_line;
    }

    public StationsDto getId_station() {
        return id_station;
    }

    public StopsDto getStopsDest() {
        return stopsDest;
    }

}
