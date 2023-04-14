package personalProject.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import personalProject.shoppingmall.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    //SELL,SOLD_OUT
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    //==비지니스 로직==//

    //물량 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    //물량 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("재고가 맞지 않습니다.");
        }
        this.stockQuantity = restStock;
    }
}
