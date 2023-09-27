package dto;

import java.time.LocalDateTime;

public class SimulationDto {
    private LocalDateTime date;
    private String sortType;
    private long max_size;

    public SimulationDto(LocalDateTime date, String sortType, long max_size) {
        this.date = date;
        this.sortType = sortType;
        this.max_size = max_size;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getSortType() {
        return sortType;
    }

    public long getMax_size() {
        return max_size;
    }
}
