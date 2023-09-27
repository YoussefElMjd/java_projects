package model;

import java.time.Duration;

public record SortResult(String sort, int size, long operation, long duration) {
}
