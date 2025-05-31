package com.feedback.feedback.controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feedback.feedback.dtos.HeroDTO;
import com.feedback.feedback.models.Member;
import com.feedback.feedback.services.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/merit")
    public ResponseEntity<HeroDTO> findFeedbackHero(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd) {
        HeroDTO heroDTO = memberService.findFeedbackHero(dateStart, dateEnd);
        if (heroDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(heroDTO);

    }
}
