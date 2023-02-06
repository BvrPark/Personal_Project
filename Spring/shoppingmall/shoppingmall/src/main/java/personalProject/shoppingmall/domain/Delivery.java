package personalProject.shoppingmall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",
            fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    //READY, COMPLETE
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
