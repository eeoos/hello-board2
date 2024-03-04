package board1.helloboard.repository;

import board1.helloboard.domain.Post;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryPostRepository implements PostRepository {

    private static long sequence = 0L;
    private static Map<Long, Post> store = new HashMap<>();


    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public Post modify(Post post) { // 복습
        // ID를 이용해 저장소에서 해당 Post를 찾는다.
        Post foundPost = store.get(post.getId());

        // Post가 존재하는지 확인한다.
        if (foundPost == null) {
            throw new IllegalArgumentException("해당 아이디의 게시글이 존재하지 않습니다. 아이디: " + post.getId());
        }

        // Post가 존재한다면, 새로운 정보로 업데이트한다.
        foundPost.setTitle(post.getTitle());
        foundPost.setName(post.getName());
        foundPost.setContent(post.getContent());
        store.put(foundPost.getId(), foundPost);  // 수정된 부분입니다.
        // 수정된 Post를 반환한다.
        return foundPost;
    }

    @Override
    public void delete(Post post) {
        store.remove(post.getId());
    } // 체크 필요



    @Override
    public Optional<Post> findByContent(String content) {
        return store.values().stream()
                .filter(post -> post.getContent().equals(content))
                .findAny();
    }


    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Post> findByName(String name) {
        return store.values().stream()
                .filter(post -> post.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        return store.values().stream()
                .filter(post -> post.getTitle().equals(title))
                .findAny();
    }

    /*@Override
    public List<Post> findAllByDate(LocalDate date) {
        return store.values().stream()
                .filter(post -> post.getDate().equals(date))
                .collect(Collectors.toList());
    }*/

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
