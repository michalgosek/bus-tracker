package com.example.bustracker.city.schedule;

import com.example.bustracker.city.line.Line;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "time_schedule")
@Data
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer day;

    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Line line;

    public Schedule(Long id, Integer day, String time, Long lineId) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.line = new Line(lineId);
    }
}
