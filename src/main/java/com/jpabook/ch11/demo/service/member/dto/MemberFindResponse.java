package com.jpabook.ch11.demo.service.member.dto;

import com.jpabook.ch11.demo.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberFindResponse {

	private String name;

	private String city;

	private String street;

	private String zipCode;

	@Builder
	public MemberFindResponse(String name, String city, String street, String zipCode) {
		this.name = name;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public static MemberFindResponse of(Member member) {
		return MemberFindResponse.builder()
				.name(member.getName())
				.city(member.getCity())
				.street(member.getStreet())
				.zipCode(member.getZipCode())
				.build();
	}

}
