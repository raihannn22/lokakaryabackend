package com.example.lokakarya.Repository;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.lokakarya.Entity.User;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username); 
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByEmailOrUsername(String email, String username);
    User findByUsername(String username);
    List<User> findByDivisionId(UUID divisionId);

    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR " +
           "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR " +
           "LOWER(u.position) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchKeyword, '%'))")
    Page<User> searchUsers(@Param("searchKeyword") String searchKeyword, Pageable pageable);    
}