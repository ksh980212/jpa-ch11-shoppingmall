package com.jpabook.ch11.demo.service.member;

import com.jpabook.ch11.demo.domain.member.Member;
import com.jpabook.ch11.demo.domain.member.MemberRepository;
import com.jpabook.ch11.demo.service.member.MemberService;
import com.jpabook.ch11.demo.service.member.dto.MemberCreateRequest;
import com.jpabook.ch11.demo.service.member.dto.MemberFindResponse;
import io.micrometer.core.annotation.TimedSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@AfterEach
	void cleanUp() {
		memberRepository.deleteAll();
	}

	@Test
	void 회원가입_테스트() {
		// given
		String name = "kang";
		String city = "seoul";
		String street = "street";
		String zipCode = "123-1234";

		MemberCreateRequest memberCreateRequest = MemberCreateRequest.builder()
				.name(name)
				.city(city)
				.street(street)
				.zipCode(zipCode)
				.build();

		// when
		memberService.join(memberCreateRequest);

		// then
		Member member = memberRepository.findAll().get(0);
		assertThat(member.getName()).isEqualTo(name);
		assertThat(member.getCity()).isEqualTo(city);
		assertThat(member.getStreet()).isEqualTo(street);
		assertThat(member.getZipCode()).isEqualTo(zipCode);
	}

	@Test
	void 전체멤버의_정보() {
		// given
		Member member1 = Member.testInstance("kang");
		Member member2 = Member.testInstance("kim");
		memberRepository.saveAll(Arrays.asList(member1, member2));

		// when
		List<MemberFindResponse> memberFindResponseList = memberService.findMembers();

		// then
		assertThat(memberFindResponseList.size()).isEqualTo(2);
		Member member = memberRepository.findAll().get(0);
		System.out.println(member.getCity());
	}

	@Test
	void 특정_멤버의_정보를_불러온다() {
		// given
		String name = "kang";
		Member member1 = Member.testInstance("kim");
		Member member2 = Member.testInstance(name);
		memberRepository.saveAll(Arrays.asList(member1, member2));

		// when
		MemberFindResponse memberFindResponse = memberService.findMember(member2.getId());

		// then
		assertThat(memberFindResponse.getName()).isEqualTo(name);
	}

}
