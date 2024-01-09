package redis.security.com.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "studios")
@Data
public class Studio {

    @EmbeddedId
    private StudioId studioId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "masters_of_studios",
    joinColumns = {
            @JoinColumn(table = "studios", name = "studio_address", referencedColumnName = "address"),
            @JoinColumn(table = "studios", name = "studio_title", referencedColumnName = "title")
    },
    inverseJoinColumns = @JoinColumn(table = "masters", name = "master_id", referencedColumnName = "id"))
    private List<Master> masters;
}

