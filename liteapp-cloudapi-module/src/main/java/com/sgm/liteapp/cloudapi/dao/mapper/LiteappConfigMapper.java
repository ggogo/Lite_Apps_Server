package com.sgm.liteapp.cloudapi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappConfigEntity;
import com.sgm.liteapp.cloudapi.dto.LiteappConfigAndViewDTO;

public interface LiteappConfigMapper extends BaseMapper<LiteappConfigEntity> {

	public List<LiteappConfigAndViewDTO> getLiteappConfigAndViewJoinListByReleaseId(
			@Param("releaseId") String releaseId);


}
