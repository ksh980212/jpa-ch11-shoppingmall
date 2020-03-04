package com.jpabook.ch11.demo.domain.member;

import com.jpabook.ch11.demo.domain.common.Address;
import com.jpabook.ch11.demo.domain.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

	@Embedded
	private Address address;

	@Builder
	public Member(String name, String city, String street, String zipCode) {
		this.name = name;
		this.address = Address.of(city, street, zipCode);
	}

	public String getCity() {
		return address.getCity();
	}

	public String getStreet() {
		return address.getStreet();
	}

	public String getZipCode() {
		return address.getZipCode();
	}

	public static Member testInstance(String name) {
		return Member.builder()
				.name(name)
				.city("city")
				.street("street")
				.zipCode("zipCode")
				.build();
	}

}
