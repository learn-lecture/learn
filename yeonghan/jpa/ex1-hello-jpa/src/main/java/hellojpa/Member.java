package hellojpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name")
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    @Transient
    private int temp;

}
