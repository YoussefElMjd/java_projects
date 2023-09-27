package dto;

import java.util.Objects;

public class FavTravelDto {

    private String name;
    private int origin;
    private int dest;


    public FavTravelDto(String name, int origin, int dest) {
        this.name = name;
        this.origin = origin;
        this.dest = dest;
    }

    public String getName() {
        return name;
    }

    public int getOrigin() {
        return origin;
    }

    public int getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavTravelDto)) return false;
        FavTravelDto that = (FavTravelDto) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
