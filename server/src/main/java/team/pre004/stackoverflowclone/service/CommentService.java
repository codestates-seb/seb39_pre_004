package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.dto.request.CommentPostDto;
import team.pre004.stackoverflowclone.dto.request.QuestionPostDto;
import team.pre004.stackoverflowclone.dto.response.CommentDto;
import team.pre004.stackoverflowclone.dto.response.QuestionDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments(Long postId, String postType);
    List<CommentDto> getAllComments(Long userId);

    CommentDto createComment(CommentPostDto commentPostDto);
    CommentDto updateComment(CommentPostDto commentPostDto);
    void deleteComment(Long commentId);

}
