package com.example.bustracker.city.street;
import com.example.bustracker.city.stop.Stop;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "streets")
@NoArgsConstructor
@Data
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;

    @OneToMany (mappedBy = "street", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stop> stops;

    public Street(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Street(Long streetId) {
        this.id = streetId;
    }
}
