package com.koreait.community.cmt;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.community.model.CmtEntity;

@Mapper
public interface CmtMapper {
	List<CmtEntity> selCmtList(CmtEntity p);
	int insCmt(CmtEntity p);
}
