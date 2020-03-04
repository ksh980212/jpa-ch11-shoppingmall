package com.jpabook.ch11.demo.service.item;

import com.jpabook.ch11.demo.domain.item.Book;
import com.jpabook.ch11.demo.domain.item.Item;
import com.jpabook.ch11.demo.domain.item.ItemRepository;
import com.jpabook.ch11.demo.service.item.dto.request.BookSaveRequest;
import com.jpabook.ch11.demo.service.item.dto.response.ItemFindResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemServiceTest {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository itemRepository;

	@AfterEach
	void cleanUp() {
		itemRepository.deleteAll();
	}

	@Test
	void 상품이_등록된다() {
		// given
		String name = "상품1";
		int price = 150000;
		int stockQuantity = 100;

		BookSaveRequest bookSaveRequest = BookSaveRequest.builder()
				.name(name)
				.price(price)
				.stockQuantity(stockQuantity)
				.build();

		// when
		itemService.saveItem(bookSaveRequest);

		// then
		Item item = itemRepository.findAll().get(0);
		assertThat(item.getName()).isEqualTo(name);
		assertThat(item.getPrice()).isEqualTo(price);
		assertThat(item.getStockQuantity()).isEqualTo(stockQuantity);
	}

	@Test
	void 전체_상품이_검색된다() {
		// given
		Book book1 = new Book("name1", 25000, 10, "author", "isbn");
		Book book2 = new Book("name2", 22000, 10, "author", "isbn");
		itemRepository.saveAll(Arrays.asList(book1, book2));

		// when
		List<ItemFindResponse> itemFindResponseList = itemService.findItems();

		//then
		assertThat(itemFindResponseList.size()).isEqualTo(2);
	}

	@Test
	void 특정_상품이_검색된다() {
		// given
		String name = "book";
		int price = 25000;
		int stockQuantity = 10;
		String author = "author";
		String isbn = "isbn";

		Item item = itemRepository.save(Book.builder()
				.name(name)
				.price(price)
				.stockQuantity(stockQuantity)
				.author(author)
				.isbn(isbn)
				.build()
		);

		// when
		ItemFindResponse itemFindResponse = itemService.findItem(item.getId());

		// then
		assertThat(itemFindResponse.getName()).isEqualTo(name);
		assertThat(itemFindResponse.getPrice()).isEqualTo(price);
		assertThat(itemFindResponse.getStockQuantity()).isEqualTo(stockQuantity);
	}

}
