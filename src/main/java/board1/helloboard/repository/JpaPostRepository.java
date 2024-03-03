package board1.helloboard.repository;


import board1.helloboard.domain.Post;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaPostRepository implements PostRepository {

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public Post modify(Post post) {
        // EntityManager의 merge 메서드를 이용하여 post 객체를 업데이트한다.
        Post updatedPost = em.merge(post);

        // 업데이트된 Post를 반환한다.
        return updatedPost;
    }




    @Override
    public void remove(Post post) {
        em.remove(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }

    @Override
    public Optional<Post> findByName(String name) {
        List<Post> result = em.createQuery("select m from Post m where m.name = :name", Post.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public Optional<Post> findByTitle(String title) {
        List<Post> result = em.createQuery("select m from Post m where m.title = :title", Post.class)
                .setParameter("title", title)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public Optional<Post> findByContent(String content) {
        List<Post> result = em.createQuery("select m from Post m where m.content = :content", Post.class)
                .setParameter("content", content)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }


}
