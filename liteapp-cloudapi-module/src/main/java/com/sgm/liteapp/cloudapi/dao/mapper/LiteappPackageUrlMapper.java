package com.sgm.liteapp.cloudapi.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappPackageUrlEntity;
import com.sgm.liteapp.cloudapi.dto.LiteappVersionQueryDTO;

public interface LiteappPackageUrlMapper extends BaseMapper<LiteappPackageUrlEntity> {

	public List<LiteappPackageUrlEntity> getLiteappPackageUrls(@Param("query") LiteappVersionQueryDTO query);
}
