package com.jpabook.ch11.demo.service.member.dto;

import com.jpabook.ch11.demo.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {

	@NotEmpty
	private String name;

	@NotEmpty
	private String city;

	@NotEmpty
	private String street;

	@NotEmpty
	private String zipCode;

	@Builder
	public MemberCreateRequest(String name, String city, String street, String zipCode) {
		this.name = name;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public Member toEntity() {
		return Member.builder()
				.name(name)
				.city(city)
				.street(street)
				.zipCode(zipCode)
				.build();
	}

}
