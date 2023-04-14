package personalProject.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    //아이디 중복 제거를 위해 유니크 제약 조건 설정
    @Column(unique = true)
    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //회원 아이디 설정
    public String setName(String name){
        return this.name = name;
    }

}
