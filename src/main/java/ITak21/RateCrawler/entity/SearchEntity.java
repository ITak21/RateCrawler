package ITak21.RateCrawler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "search")
public class SearchEntity {
    @Id
    @Column(name = "searchid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int searchId;

    @Column(name = "userid")
    private String userId;

    @Column(name = "searchname")
    private String searchName;

    @Column(name = "searchcode")
    private String searchCode;

    @Column(name = "searchdate")
    private LocalDateTime searchDate;
}
