package com.example.demo.repository;

import com.example.demo.model.SubForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubForumRepository extends JpaRepository<SubForum, Long> {
}
