package com.feedback.feedback.services;

import com.feedback.feedback.models.Feedback;
import com.feedback.feedback.models.Member;
import com.feedback.feedback.models.TopicsEnum;
import com.feedback.feedback.repositories.IMemberRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final IMemberRepository memberRepository;

    public MemberService(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findById(int id) {
        return memberRepository.findById(id);
    }

    public Member findFeedbackHero(LocalDate dateStart, LocalDate dateEnd) {
        // Logic to find the feedback hero based on the provided date range
        // This is a placeholder implementation and should be replaced with actual logic
        if (dateStart == null || dateEnd == null) {
            return null;
        }

        if (dateStart.isAfter(dateEnd)) {
            return null;
        }

        List<Member> members = memberRepository.findAll();

        if (members.isEmpty()) {
            return null;
        }

        Member feedbackHero = null;
        int maxFeedbackPoints = 0;
        for (Member member : members) {
            int feedbackPoints = 0;
            for (Feedback feedback : member.getFeedbacksToThisMember()) {
                LocalDate feedbackDate = feedback.getCreatedAt() != null ? LocalDate.parse(feedback.getCreatedAt())
                        : null;
                if (feedbackDate != null && !feedbackDate.isBefore(dateStart) && !feedbackDate.isAfter(dateEnd)) {
                    int sum = 0;
                    for (var topic : feedback.getTopics()) {
                        sum += TopicsEnum.fromId(topic).getPoints();
                    }
                    feedbackPoints += sum;
                }
            }
            if (feedbackPoints > maxFeedbackPoints) {
                maxFeedbackPoints = feedbackPoints;
                feedbackHero = member;
            }
        }

        return feedbackHero;
    }

}
