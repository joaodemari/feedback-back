package com.feedback.feedback.services;

import com.feedback.feedback.IdGenerator;
import com.feedback.feedback.dtos.FeedbackDTO;
import com.feedback.feedback.models.Feedback;
import com.feedback.feedback.models.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feedback.feedback.repositories.IFeedbackRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {
        private final IFeedbackRepository feedbackRepository;

        @Autowired
        private MemberService memberService;

        public FeedbackService(IFeedbackRepository feedbackRepository) {
                this.feedbackRepository = feedbackRepository;
        }

        public void populateFeedbacks() {

                Member member1 = memberService.findById(1);
                Member member2 = memberService.findById(2);
                Member member3 = memberService.findById(3);

                List<Feedback> feedbacks = List.of(
                                new Feedback(1, member1, member2, List.of(1, 2, 3),
                                                null,
                                                "2024-06-01"),
                                new Feedback(2, member2, member3, List.of(2, 4),
                                                "Precisa melhorar a comunicação.", "2024-06-02"),
                                new Feedback(3, member3, member1, List.of(1, 3, 5),
                                                "Excelente liderança.", "2024-06-03"),
                                new Feedback(4, member1, member3, List.of(2, 5),
                                                "Ótimo trabalho em equipe.", "2024-06-04"),
                                new Feedback(5, member2, member1, List.of(3),
                                                "Precisa ser mais pontual.", "2024-06-05"),
                                new Feedback(6, member3, member2, List.of(1, 4),
                                                "Sempre disposto a ajudar.", "2024-06-06"),
                                new Feedback(7, member1, member2, List.of(2, 3, 5),
                                                "Boa apresentação no projeto.", "2024-06-07"),
                                new Feedback(8, member2, member3, List.of(1, 5),
                                                "Sugiro mais atenção aos detalhes.", "2024-06-08"),
                                new Feedback(9, member3, member1, List.of(4),
                                                "Excelente organização.", "2024-06-09"));

                feedbackRepository.saveAll(feedbacks);
        }

        public Feedback createFeedback(FeedbackDTO feedbackDto) {
                Feedback feedback = new Feedback();
                int n = IdGenerator.getNextId();
                while (feedbackRepository.findById(n).isPresent()) {
                        n = IdGenerator.getNextId();
                }
                feedback.setId(n);
                System.out.println(feedback.getId());
                feedback.setCreatedAt(LocalDate.now().toString());
                feedback.setMessage(feedbackDto.getMessage());
                feedback.setTopics(feedbackDto.getTopics());
                feedback.setFromMember(memberService.findById(feedbackDto.getIdFromMember()));
                feedback.setToMember(memberService.findById(feedbackDto.getIdToMember()));
                feedback.setAnonymous(feedbackDto.isAnonymous());
                return feedbackRepository.save(feedback);
        }
}
