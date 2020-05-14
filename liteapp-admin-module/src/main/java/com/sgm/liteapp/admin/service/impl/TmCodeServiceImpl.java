package com.sgm.liteapp.admin.service.impl;

import com.sgm.liteapp.admin.exception.ResultException;
import com.sgm.liteapp.admin.model.dto.TmCodeDto;
import com.sgm.liteapp.admin.model.entity.TmCode;
import com.sgm.liteapp.admin.model.mapper.TmCodeMapper;
import com.sgm.liteapp.admin.service.TmCodeService;
import com.sgm.liteapp.admin.utils.UUIDUtils;
import com.sgm.liteapp.commons.web.SessionContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@Service
public class TmCodeServiceImpl extends ServiceImpl<TmCodeMapper, TmCode> implements TmCodeService {

	@Autowired
	private TmCodeMapper tmCodeMapper;
	
	@Override
	public Page<TmCode> masterCodeInfoQuery(TmCodeDto dto) throws Exception {
		if(StringUtils.isEmpty(dto.getSortName())) {
			dto.setSortName(" CODE_TYPE,CODE_VALUE ");
			dto.setSort(" ASC ");
		}
		Page<TmCode> page = new Page<TmCode>(dto.getPageNo(), dto.getSize());
		List<TmCode> queryTmCode = tmCodeMapper.queryTmCode(page, dto);
		page.setRecords(queryTmCode);
		return page;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String masterCodeInfoInsert(TmCodeDto dto) {
		QueryWrapper<TmCode> queryWrapper=new QueryWrapper<TmCode>();
		queryWrapper.eq("CODE_TYPE", dto.getCodeType()).eq("CODE_VALUE", dto.getCodeValue());
		TmCode tmCode = tmCodeMapper.selectOne(queryWrapper);
		if(tmCode != null) {
			throw new ResultException(500 , "该类型中已经存在值："+dto.getCodeValue());
		}
		TmCode entity = new TmCode();
		entity.setCodeId(UUIDUtils.getUUID());
		entity.setCodeType(dto.getCodeType());
		entity.setCodeValue(dto.getCodeValue());
		entity.setCreateBy("");
		entity.setCreateDate(new Date());
		tmCodeMapper.insert(entity);
		return "Success";
	}

}
