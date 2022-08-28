package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.dto.response.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllPost(Long PostId, String postType);
    List<CommentDto> getAllPost(Long UserId);



}
