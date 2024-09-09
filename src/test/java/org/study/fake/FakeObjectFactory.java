package org.study.fake;

import org.study.post.application.CommentService;
import org.study.post.application.PostService;
import org.study.post.application.interfaces.CommentRepository;
import org.study.post.application.interfaces.LikeRepository;
import org.study.post.application.interfaces.PostRepository;
import org.study.post.repository.FakeCommentRepository;
import org.study.post.repository.FakeLikeRepository;
import org.study.post.repository.FakePostRepository;
import org.study.user.application.UserRelationService;
import org.study.user.application.UserService;
import org.study.user.repository.FakeUserRepository;
import org.study.user.repository.FakeUserRelationRepository;
import org.study.user.application.interfaces.UserRelationRepository;
import org.study.user.application.interfaces.UserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(fakeCommentRepository, fakeLikeRepository, userService, postService);

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

}
