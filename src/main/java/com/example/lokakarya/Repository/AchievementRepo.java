package com.example.lokakarya.Repository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lokakarya.Entity.Achievement;

@Repository
public interface AchievementRepo extends JpaRepository<Achievement, UUID> {
    public List<Achievement> findByGroupAchievementId(UUID groupId);
    List<Achievement> findAllByEnabled(Integer number);
    Page<Achievement> findByAchievementContainingIgnoreCase(String achievement, Pageable pageable);
    long countByAchievementContainingIgnoreCase(String achievement);
}