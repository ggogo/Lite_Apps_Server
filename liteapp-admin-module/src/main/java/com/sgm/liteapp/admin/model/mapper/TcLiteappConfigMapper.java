package com.sgm.liteapp.admin.model.mapper;

import com.sgm.liteapp.admin.model.entity.TcLiteappConfig;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 轻应用配置 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
public interface TcLiteappConfigMapper extends BaseMapper<TcLiteappConfig> {

	public TcLiteappConfig queryTcLiteappConfigById(@Param("releaseId") String releaseId);
	
}
