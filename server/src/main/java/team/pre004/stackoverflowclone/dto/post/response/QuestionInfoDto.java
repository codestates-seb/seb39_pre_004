package team.pre004.stackoverflowclone.dto.post.response;

import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.dto.post.request.AnswerDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionInfoDto {

    UserInfoDto owner;

    private Long questionId;

    private String title;
    private String body;
    private Integer likes;
    private int views;

    private List<TagList> tags;

    private String link;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private List<AnswerDto> answers;
    private List<QuestionCommentDto> comments;

}
