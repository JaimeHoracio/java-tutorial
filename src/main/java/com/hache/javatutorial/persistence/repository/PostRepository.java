package com.hache.javatutorial.persistence.repository;

import com.hache.javatutorial.persistence.entities.Post;
import com.hache.javatutorial.persistence.records.PostRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
            SELECT new com.hache.javatutorial.persistence.records.PostRecord(idPost, title, msgPost)
            FROM Post p
            WHERE p.idPost = :id_post
            """)
    Optional<PostRecord> findBasicPost(@Param("id_post") Long idPost);

    @Query("""
            SELECT p
            FROM Post p
            LEFT JOIN FETCH p.details details
            WHERE p.idPost = :id_post
            """)
    Optional<Post> findFullPost(@Param("id_post") Long idPost);
}
