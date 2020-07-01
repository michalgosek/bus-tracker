package com.example.bustracker.city.stop;

import com.example.bustracker.city.line.Line;
import com.example.bustracker.city.street.Street;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stops")
@Data
@NoArgsConstructor

public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @Autowired
    private Street street;

    @OneToMany(mappedBy = "stop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Line> lines;

    public Stop(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Stop(Long id, String name, Long streetId) {
        this.id = id;
        this.name = name;
        this.street = new Street(streetId);
    }

    public Stop(Long id) {
        this.id = id;
    }
}
