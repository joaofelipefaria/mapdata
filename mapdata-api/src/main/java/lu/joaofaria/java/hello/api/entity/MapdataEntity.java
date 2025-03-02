package lu.joaofaria.java.hello.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "mapdata")
public class MapdataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapdata_id_seq")
    @SequenceGenerator(name = "mapdata_id_seq", sequenceName = "mapdata_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String value;

    @Transient
    @OneToMany(mappedBy = "mapdata", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MetadataEntity> metadata;
}
