package com.example.bustracker.city.line;

import com.example.bustracker.city.stop.Stop;
import com.example.bustracker.city.timetable.Timetable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bus_lines")
@Data
@NoArgsConstructor
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int lineNumber;

    private String direction;

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timetable> timetables;

    @ManyToOne(fetch = FetchType.LAZY)
    private Stop stop;

    public Line(Long id, String direction, Integer lineNumber, Long stopId) {
        this.id = id;
        this.direction = direction;
        this.lineNumber = lineNumber;
        stop.setId(stopId);
    }
}
