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
                                        () -> new IllegalArgumentException("Invalid topic ID: " + topic)
                                )).toList(),
                        feedback.getMessage(),
                        feedback.isAnonymous()
                ))
                .toList();
    }

    public void populateMembers() {
        List<Member> members = List.of(
                new Member(1, "Samuel Ribeiro",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0mABcIa-JzRlzyG1idAyXkK00R1M6A-5vjA&s"),
                new Member(2, "Bernardo Paiva",
                        "https://veja.abril.com.br/wp-content/uploads/2024/08/neymar-santos.jpg?quality=70&strip=info&w=414&h=280&crop=1"),
                new Member(3, "Leonardo Wingert", "https://a.espncdn.com/photo/2025/0131/r1445897_1296x729_16-9.jpg"),
                new Member(4, "Guilherme Kuhn",
                        "https://admin.cnnbrasil.com.br/wp-content/uploads/sites/12/2023/09/GettyImages-1668971338-e1694439970587.jpg?w=1200&h=900&crop=1"),
                new Member(5, "João Demari", "https://img.nsctotal.com.br/wp-content/uploads/2024/12/Neymar.jpg"),
                new Member(6, "Lucas Silva",
                        "https://admin.cnnbrasil.com.br/wp-content/uploads/sites/12/2025/03/neymar-santos_2e1987-e1741616548279.jpg?w=1200&h=675&crop=1"));

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
