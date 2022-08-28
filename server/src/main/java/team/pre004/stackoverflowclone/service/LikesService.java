package team.pre004.stackoverflowclone.service;

public interface LikesService {
    int likesUp(Long userId, Long postId);
    int likesUpUndo(Long userId, Long postId);
    int likesDown(Long userId, Long postId);
    int likesDownUndo(Long userId, Long postId);
}
