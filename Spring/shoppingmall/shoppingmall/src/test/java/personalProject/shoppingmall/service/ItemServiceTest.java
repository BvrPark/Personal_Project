package personalProject.shoppingmall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import personalProject.shoppingmall.domain.Item;
import personalProject.shoppingmall.repository.ItemRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void 상품등록() throws Exception{
        //given
        Item item = new Item();
        item.setName("상품 1");
        item.setStockQuantity(50);
        item.setPrice(20000);

        //when
        itemService.saveItem(item);

        //then
        assertEquals(item,itemService.findOne(item.getId()));
    }



}