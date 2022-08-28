package team.pre004.stackoverflowclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import team.pre004.stackoverflowclone.domain.post.Question;
import team.pre004.stackoverflowclone.dto.post.request.RequestQuestionDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Question addQuestion(RequestQuestionDto requestQuestionDto);

}
