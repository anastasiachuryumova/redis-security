package redis.security.com.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "masters")
@Data
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "login", referencedColumnName = "username")
    private User login;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String skill;

    @Column
    private String style;

    @ManyToMany(mappedBy = "masters")
    private List<Studio> studios;

    @Override
    public String toString () {
        return "test";
    }
}
