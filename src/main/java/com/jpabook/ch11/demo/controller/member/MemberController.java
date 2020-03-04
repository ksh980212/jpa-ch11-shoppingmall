package com.jpabook.ch11.demo.controller.member;

import com.jpabook.ch11.demo.service.member.MemberService;
import com.jpabook.ch11.demo.service.member.dto.MemberCreateRequest;
import com.jpabook.ch11.demo.service.member.dto.MemberFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/api/v1/member")
	public String join(@Valid @RequestBody MemberCreateRequest request) {
		memberService.join(request);
		return "SUCCESS";
	}

	@GetMapping("/api/v1/members")
	public List<MemberFindResponse> findMembers() {
		return memberService.findMembers();
	}

}
