package com.pjt.triptravel.board.dto.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.pjt.triptravel.board.dto.comment.CommentDto;
import com.pjt.triptravel.board.entity.Comment;
import com.pjt.triptravel.board.entity.Post;
import com.pjt.triptravel.common.utils.TimeFormatUtil;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PostDetailDto {

	private Long postId;
	private String title;
	private String content;
	private int views;
	private int likes;
	private Long boardId;
	private Long writerId;
	private String writerNickname;
	private String writerProfileImageUrl;
	private String registrationDate;
	private String lastModifiedDate;

	public static PostDetailDto of(Post post) {
		return PostDetailDto.builder()
			.postId(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.views(post.getViews())
			.likes(post.getLikes())
			.boardId(post.getBoard().getId())
			.writerId(post.getWriter().getId())
			.writerNickname(post.getWriter().getNickname())
			.writerProfileImageUrl(post.getWriter().getProfileImageUrl())
			.registrationDate(TimeFormatUtil.convertDateTime(post.getRegistrationDate()))
			.lastModifiedDate(TimeFormatUtil.convertDateTime(post.getLastModifiedDate()))
			.build();
	}
}

