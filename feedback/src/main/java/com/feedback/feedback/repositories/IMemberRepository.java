package com.feedback.feedback.repositories;

import com.feedback.feedback.models.Member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Integer> {
    public Member findById(int id);

    public List<Member> findAll();
}
