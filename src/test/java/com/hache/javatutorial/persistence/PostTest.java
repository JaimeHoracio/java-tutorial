package com.hache.javatutorial.persistence;

import com.hache.javatutorial.persistence.entities.Post;
import com.hache.javatutorial.persistence.entities.PostComments;
import com.hache.javatutorial.persistence.entities.PostDetails;
import com.hache.javatutorial.persistence.records.PostRecord;
import com.hache.javatutorial.persistence.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        post.addComments(PostComments.builder().review("My first review").build());

        postRepository.save(post);
    }

    /*
    @Test
    @DisplayName("Test Fail @OneToOne Post")
    public void postDetailsFailTest() {
        Exception exception = assertThrows(LazyInitializationException.class, () -> {
            postRepository.findById(1L).get();
        });

        String expectedMessage = "could not initialize proxy";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    */

    @Test
    @DisplayName("Test @OneToOne Post")
    public void postDetailsFailTest() {
        Post post = Post.builder().title("My second review").msgPost("Something").build();
        PostDetails details = PostDetails.builder().detailsPost("John Wick").build();
        details.setPost(post);
        post.setDetails(details);
        postRepository.save(post);

        PostRecord postSavedBasic = postRepository.findBasicPost(post.getIdPost()).get();
        Post postSavedFull = postRepository.findFullPost(post.getIdPost()).get();

        assertEquals("My second review", postSavedBasic.title());
        assertEquals("John Wick", postSavedFull.getDetails().getDetailsPost());
    }

}
