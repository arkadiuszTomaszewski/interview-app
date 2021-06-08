package com.atomaszewski.domain.repository;

import com.atomaszewski.domain.model.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, String> {
}
