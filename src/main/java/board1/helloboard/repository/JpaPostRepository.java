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
    public Post save(Post member) {
        em.persist(member);
        return member;
    }
    @Override
    public Optional<Post> findById(Long id) {
        Post member = em.find(Post.class, id);
        return Optional.ofNullable(member);
    }
    @Override
    public List<Post> findAll() {
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();

        }
    @Override
    public Optional<Post> findByName(String name) {
        List<Post> result = em.createQuery("select m from Post m where m.name = :name", Post.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Post modify(Post post) {
        return null;
    }

    @Override
    public void remove(Post post) {

    }

    @Override
    public Optional<Post> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findByContent(String content) {
        return Optional.empty();
    }
}
