package com.feedback.feedback.controllers;

import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.dtos.HeroDTO;
import com.feedback.feedback.services.MemberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/{memberId}/feedbacks")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByMember(@PathVariable int memberId) {
        List<FeedbackDTO> feedbacks = memberService.findFeedbacksByMemberId(memberId);
        if (feedbacks == null || feedbacks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedbacks);
    }

}
