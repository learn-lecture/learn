package org.study.post.application;

import org.study.fake.FakeObjectFactory;
import org.study.post.application.dto.CreatePostRequestDto;
import org.study.post.domain.Post;
import org.study.post.domain.content.PostPublicationState;
import org.study.user.application.UserService;
import org.study.user.application.dto.CreateUserRequestDto;
import org.study.user.domain.User;

public class PostApplicationTestTemplate {

    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));;
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));;

    CreatePostRequestDto postDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postDto);

}
