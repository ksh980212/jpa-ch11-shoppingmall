package com.jpabook.ch11.demo.service.item.dto.request;

import com.jpabook.ch11.demo.domain.item.Book;
import com.jpabook.ch11.demo.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class BookSaveRequest {

	@NotEmpty
	private String name;

	@NotEmpty
	private int price;

	@NotEmpty
	private int stockQuantity;

	@NotEmpty
	private String author;

	@NotEmpty
	private String isbn;

	@Builder
	public BookSaveRequest(@NotEmpty String name, @NotEmpty int price, @NotEmpty int stockQuantity) {
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public Item toEntity() {
		return Book.builder()
				.name(name)
				.price(price)
				.stockQuantity(stockQuantity)
				.author(author)
				.isbn(isbn)
				.build();
	}

}
