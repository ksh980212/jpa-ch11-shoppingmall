package com.jpabook.ch11.demo.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private int price;

	private int stockQuantity;

	public Item(String name, int price, int stockQuantity) {
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public void addStock(int stockQuantity) {
		this.stockQuantity += stockQuantity;
	}

	public void removeStock(int stockQuantity) {
		int restStock = this.stockQuantity - stockQuantity;
		if (restStock < 0) {
			throw new IllegalArgumentException("need more stock");
		}
		this.stockQuantity = restStock;
	}

}
