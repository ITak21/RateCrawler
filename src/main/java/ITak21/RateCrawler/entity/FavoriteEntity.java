package ITak21.RateCrawler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "favorite")
public class FavoriteEntity {
    @Id
    @Column(name = "favid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favId;

    @Column(name = "userid")
    private String userId;

    @Column(name = "favname")
    private String favName;
}
