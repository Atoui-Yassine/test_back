package com.example.test_technique.repositories.coursR;

import com.example.test_technique.models.cours.CoursModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<CoursModel,Long> {
}
