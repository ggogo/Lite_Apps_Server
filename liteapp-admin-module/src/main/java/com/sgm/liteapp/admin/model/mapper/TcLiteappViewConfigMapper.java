package com.sgm.liteapp.admin.model.mapper;

import com.sgm.liteapp.admin.model.entity.TcLiteappViewConfig;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 轻应用卡片配置 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
public interface TcLiteappViewConfigMapper extends BaseMapper<TcLiteappViewConfig> {

	
	public List<TcLiteappViewConfig> queryTcLiteappViewConfigByReleaseIdAndViewType(@Param("releaseId") String releaseId, @Param("viewType") String[] viewType);
}
