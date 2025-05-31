package com.feedback.feedback.services;

import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.dtos.HeroDTO;
import com.feedback.feedback.models.Feedback;
import com.feedback.feedback.models.Member;
import com.feedback.feedback.models.TopicsEnum;
import com.feedback.feedback.repositories.IMemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final IMemberRepository memberRepository;

    public MemberService(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<FeedbackDTO> findFeedbacksByMemberId(int memberId) {
        Member member = memberRepository.findById(memberId);
        if (member == null || member.getFeedbacksToThisMember() == null) {
            return List.of();
        }
        return member.getFeedbacksToThisMember().stream()
                .map(feedback -> new FeedbackDTO(
                        feedback.getFromMember().getId(),
                        feedback.getToMember().getId(),
                        feedback.getTopics().stream().map(
                                topic -> TopicsEnum.fromId(topic).orElseThrow(
                                        () -> new IllegalArgumentException("Invalid topic ID: " + topic)))
                                .toList(),
                        feedback.getMessage(),
                        feedback.isAnonymous()))
                .toList();
    }

    public void populateMembers() {
        List<Member> members = List.of(
                new Member(1, "Samuel Ribeiro",
                        "https://i.imgur.com/7zzeEb8.jpeg"),
                new Member(2, "Bernardo Paiva",
                        "https://i.imgur.com/aMXs8Ui.jpeg"),
                new Member(3, "Leonardo Wingert", "https://i.imgur.com/6x6nb73.jpeg"),
                new Member(4, "Guilherme Kuhn",
                        "https://i.imgur.com/KIX6nja.jpeg"),
                new Member(5, "João Demari", "https://imgur.com/VlUqd9Q.jpeg"),
                new Member(6, "Julio Pinto",
                        "https://imgur.com/Qx4BXu6.jpeg"));

        memberRepository.saveAll(members);
    }

    public Member findById(int id) {
        return memberRepository.findById(id);
    }

    public HeroDTO findFeedbackHero(LocalDate dateStart, LocalDate dateEnd) {
        // Logic to find the feedback hero based on the provided date range
        // This is a placeholder implementation and should be replaced with actual logic
        if (dateStart == null || dateEnd == null) {
            System.out.println("Invalid date range provided.");
            return null;
        }

        if (dateStart.isAfter(dateEnd)) {
            System.out.println("Start date is after end date.");
            return null;
        }

        List<Member> members = memberRepository.findAll();

        if (members.isEmpty()) {
            System.out.println("No members found.");
            return null;
        }

        Member feedbackHero = null;
        int maxFeedbackPoints = 0;
        int totalLikes = 0;
        for (Member member : members) {
            int feedbackPoints = 0;
            int tempLikes = 0;
            for (Feedback feedback : member.getFeedbacksToThisMember()) {
                LocalDate feedbackDate = feedback.getCreatedAt() != null ? LocalDate.parse(feedback.getCreatedAt())
                        : null;

                if (feedbackDate != null && !feedbackDate.isBefore(dateStart) && !feedbackDate.isAfter(dateEnd)) {
                    int sum = 0;
                    for (var topic : feedback.getTopics()) {
                        Optional<TopicsEnum> topicEnum = TopicsEnum.fromId(topic);
                        if (topicEnum.isPresent()) {
                            sum += topicEnum.get().getPoints();
                            if (topicEnum.get().isLike()) {
                                tempLikes++;
                            }
                        }
                    }
                    feedbackPoints += sum;
                }
            }
            if (feedbackPoints > maxFeedbackPoints) {
                maxFeedbackPoints = feedbackPoints;
                feedbackHero = member;
                totalLikes = tempLikes;
            }
        }

        System.out.println("Finding feedback hero between " + dateStart + " and " + dateEnd);

        if (feedbackHero == null) {
            System.out.println("No feedback hero found in the specified date range.");
            return null;
        }

        return new HeroDTO(feedbackHero.getName(), feedbackHero.getPhotoUrl(), totalLikes);
    }

}
