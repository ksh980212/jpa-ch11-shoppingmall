package com.jpabook.ch11.demo.service.item.dto.response;

import com.jpabook.ch11.demo.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemFindResponse {

	private String name;

	private int price;

	private int stockQuantity;

	@Builder
	public ItemFindResponse(String name, int price, int stockQuantity) {
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public static ItemFindResponse of(Item item) {
		return ItemFindResponse.builder()
				.name(item.getName())
				.price(item.getPrice())
				.stockQuantity(item.getStockQuantity())
				.build();
	}

}
