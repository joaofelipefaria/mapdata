package lu.joaofaria.java.hello.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "metadata")
public class MetadataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metadata_id_seq")
    @SequenceGenerator(name = "metadata_id_seq", sequenceName = "metadata_id_seq", allocationSize = 1)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "idmapdata", nullable = false)
    private MapdataEntity mapdata;

    @Column(nullable = false)
    private String value1;

    @Column(nullable = false)
    private String value2;
}
