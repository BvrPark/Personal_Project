package personalProject.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private String itemDetail;

    //SELL,SOLD_OUT
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
}
