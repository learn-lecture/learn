package org.study.post.application;

import org.study.post.application.dto.CreatePostRequestDto;
import org.study.post.application.dto.LikePostRequestDto;
import org.study.post.application.dto.UpdatePostRequestDto;
import org.study.post.application.interfaces.LikeRepository;
import org.study.post.application.interfaces.PostRepository;
import org.study.post.domain.Post;
import org.study.post.domain.content.Content;
import org.study.post.domain.content.PostContent;
import org.study.user.application.UserService;
import org.study.user.domain.User;

public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.memberId());
        Post post = Post.createPost(null, author, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }
        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }

}
