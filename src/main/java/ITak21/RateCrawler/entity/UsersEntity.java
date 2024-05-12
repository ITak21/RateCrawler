package ITak21.RateCrawler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UsersEntity {
    @Id
    private String id;

    @Column(name = "name")
    private String usernm;

    @Column(name = "password")
    private String userpw;

    @Column(name = "createddate")
    private LocalDateTime userdate;

}
