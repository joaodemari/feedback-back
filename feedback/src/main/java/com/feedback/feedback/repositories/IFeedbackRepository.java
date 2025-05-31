package com.feedback.feedback.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.feedback.feedback.models.Feedback;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

}
