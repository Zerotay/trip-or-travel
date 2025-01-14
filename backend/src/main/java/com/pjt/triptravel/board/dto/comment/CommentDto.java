package com.pjt.triptravel.board.dto.comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.pjt.triptravel.board.entity.Comment;
import com.pjt.triptravel.common.utils.TimeFormatUtil;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentDto {
	private Long commentId;
	private String comment;
	private Long commenterId;
	private String commenterNickname;
	private String commenterProfileImageUrl;
	private String registrationDate;
	private String lastModifiedDate;
	private List<CommentDto> children;

	public static CommentDto of(Comment comment) {
		return CommentDto.builder()
			.commentId(comment.getId())
			.comment(comment.getComment())
			.commenterId(comment.getCommenter().getId())
			.commenterNickname(comment.getCommenter().getNickname())
			.commenterProfileImageUrl(comment.getCommenter().getProfileImageUrl())
			.children(comment.getChildren().stream().map(CommentDto::of).collect(Collectors.toList()))
			.registrationDate(TimeFormatUtil.convertDateTime(comment.getRegistrationDate()))
			.lastModifiedDate(TimeFormatUtil.convertDateTime(comment.getLastModifiedDate()))
			.build();
	}

	public int getChildrenCount() {
		return children.size();
	}
}
