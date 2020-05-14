package com.sgm.liteapp.cloudapi.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgm.liteapp.cloudapi.dao.entity.BuildinLiteappEntity;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;

public interface BuildinLiteappMapper extends BaseMapper<BuildinLiteappEntity> {

	public List<BuildinLiteappEntity> getBuildinLiteappList(@Param("query") LiteappQueryDTO query);
}
