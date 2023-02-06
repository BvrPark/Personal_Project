package personalProject.shoppingmall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;
    private String email;

    @Embedded
    private Address address;

}
