package com.example.bustracker.extractor;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public final class Extractor {
    public static <T> ResultSetExtractor<Optional<T>> singletonOptionalExtractor(RowMapper<? extends T> mapper) {
        return rs -> rs.next() ? Optional.ofNullable(mapper.mapRow(rs, 1)) : Optional.empty();
    }
}
