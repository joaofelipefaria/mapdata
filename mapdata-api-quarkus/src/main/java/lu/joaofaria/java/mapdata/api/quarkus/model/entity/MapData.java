package lu.joaofaria.java.mapdata.api.quarkus.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
public class MapData extends PanacheEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapdata_id_seq")
    @SequenceGenerator(name = "mapdata_id_seq", sequenceName = "mapdata_id_seq", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    public Long id;

    @Column(nullable = false)
    public String value;

    // Construtores, getters e setters (não é necessário com Panache)
    public MapData() {
    }

    public MapData(String value) {
        this.value = value;
    }
}
