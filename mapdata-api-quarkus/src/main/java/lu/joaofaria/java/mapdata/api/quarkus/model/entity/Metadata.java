package lu.joaofaria.java.mapdata.api.quarkus.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Metadata extends PanacheEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metadata_id_seq")
    @SequenceGenerator(name = "metadata_id_seq", sequenceName = "metadata_id_seq", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "idmapdata", referencedColumnName = "id", nullable = false)
    public MapData mapData;

    @Column(nullable = false)
    public String value1;

    @Column(nullable = false)
    public String value2;

    // Construtores, getters e setters (não é necessário com Panache)
    public Metadata() {
    }

    public Metadata(MapData mapData, String value1, String value2) {
        this.mapData = mapData;
        this.value1 = value1;
        this.value2 = value2;
    }
}
