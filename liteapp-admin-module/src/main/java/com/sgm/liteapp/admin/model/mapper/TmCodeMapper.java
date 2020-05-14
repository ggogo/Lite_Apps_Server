package com.sgm.liteapp.admin.model.mapper;

import com.sgm.liteapp.admin.model.dto.TmCodeDto;
import com.sgm.liteapp.admin.model.entity.TmCode;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
public interface TmCodeMapper extends BaseMapper<TmCode> {

	
	public List<TmCode> queryTmCode(Page<TmCode> page, @Param("dto") TmCodeDto dto);
	
	public List<TmCode> queryTmCodeByCodeType(@Param("codeType") String codeType);
}
