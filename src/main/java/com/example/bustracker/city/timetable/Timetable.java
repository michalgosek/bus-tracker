package com.example.bustracker.city.timetable;

import com.example.bustracker.city.line.Line;

import javax.persistence.*;

@Entity
@Table(name = "timetables")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Line line;

    public Timetable() {}
}
