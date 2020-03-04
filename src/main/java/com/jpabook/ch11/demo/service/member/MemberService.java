package com.jpabook.ch11.demo.service.member;

import com.jpabook.ch11.demo.domain.member.MemberRepository;
import com.jpabook.ch11.demo.service.member.dto.MemberCreateRequest;
import com.jpabook.ch11.demo.service.member.dto.MemberFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public void join(MemberCreateRequest request) {
		memberRepository.save(request.toEntity());
	}

	@Transactional
	public List<MemberFindResponse> findMembers() {
		List<MemberFindResponse> memberFindResponses = new ArrayList<>();
		memberRepository.findAll().forEach(
				member -> memberFindResponses.add(MemberFindResponse.of(member))
		);
		return memberFindResponses;
	}

}
