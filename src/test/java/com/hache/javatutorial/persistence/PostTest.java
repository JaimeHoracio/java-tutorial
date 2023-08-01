package com.hache.javatutorial.persistence;

import com.hache.javatutorial.persistence.entities.Post;
import com.hache.javatutorial.persistence.entities.PostComment;
import com.hache.javatutorial.persistence.entities.PostDetail;
import com.hache.javatutorial.persistence.entities.Tag;
import com.hache.javatutorial.persistence.records.PostRecord;
import com.hache.javatutorial.persistence.repository.PostRepository;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void init() {

    }

    @Test
    @DisplayName("Test @OneToMany Post")
    public void postTest() {
        Post post = Post.builder().title("First post").msgPost("Something").build();
        post.addComment(PostComment.builder().review("My first review").build());

        postRepository.save(post);
    }

    @Test
    @DisplayName("Test @OneToOne Post")
    public void postDetailsTest() {
        Post post = Post.builder().title("My second review").msgPost("Something").build();
        PostDetail details = PostDetail.builder().detailsPost("John Wick").build();
        details.setPost(post);
        post.setDetails(details);
        postRepository.save(post);

        PostRecord postSavedBasic = postRepository.findBasicPost(post.getPostId()).get();
        Post postSavedFull = postRepository.findFullPost(post.getPostId()).get();

        assertEquals("My second review", postSavedBasic.title());
        assertEquals("John Wick", postSavedFull.getDetails().getDetailsPost());
    }

    @Test
    @DisplayName("Test @ManyToMany Post")
    public void postTagsTest() {
        Post post1 = Post.builder().title("JPA with Hibernate").msgPost("Something").build();

        Tag tag1 = Tag.builder().name("Java").build();
        Tag tag2 = Tag.builder().name("Hibernate").build();

        post1.addTag(tag1);
        post1.addTag(tag2);

        postRepository.save(post1);
    }

    @Test
    @DisplayName("Test Fail @ManyToMany Post")
    public void postTagsFailTest() {
        Post post1 = Post.builder().title("JPA with Hibernate").msgPost("Something").build();
        Post post2 = Post.builder().title("Native Hibernate").msgPost("Something").build();

        Tag tag1 = Tag.builder().name("Java").build();
        Tag tag2 = Tag.builder().name("Hibernate").build();

        post1.addTag(tag1);
        post1.addTag(tag2);

        post2.addTag(tag1);

        /**
         * Ocurre la excepcion InvalidDataAccessApiUsageException porque luego del primer save se cierra la session de
         * Hibernate y el segundo save las entidades no estan administradas, entidades separadas, y falla.
         * Una posible solucion a esto es fusionar la entidad que falla al contexto de persistencia de Hibernate.
         */
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            postRepository.save(post1);
            postRepository.save(post2);
        });

        String expectedMessage = "detached entity passed to persist:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
