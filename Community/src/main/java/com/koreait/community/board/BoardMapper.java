package com.koreait.community.board;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.community.model.BoardEntity;

@Mapper
public interface BoardMapper {
	 int insBoard(BoardEntity p);
}
