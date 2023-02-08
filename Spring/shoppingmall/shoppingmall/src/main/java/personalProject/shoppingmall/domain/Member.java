package personalProject.shoppingmall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    //아이디 중복 제거를 위해 유니크 제약 조건 설정
    @Column(unique = true)
    private String name;

    private String password;
    private String email;

    @Embedded
    private Address address;

    //회원 아이디 설정
    public String setName(String name){
        return this.name = name;
    }

}
