package com.jpabook.ch11.demo.domain.common;

import lombok.*;

import javax.persistence.Embeddable;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

	private String city;

	private String street;

	private String zipCode;

	@Builder
	public Address(String city, String street, String zipCode) {
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public static Address of(String city, String street, String zipCode) {
		return new Address(city, street, zipCode);
	}

}
