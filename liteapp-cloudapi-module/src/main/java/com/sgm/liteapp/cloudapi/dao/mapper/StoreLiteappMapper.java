package com.sgm.liteapp.cloudapi.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappConfigEntity;
import com.sgm.liteapp.cloudapi.dao.entity.StoreLiteappEntity;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappVersionQueryDTO;

public interface StoreLiteappMapper extends BaseMapper<StoreLiteappEntity> {

	public List<StoreLiteappEntity> getStoreLiteappList(@Param("query") LiteappQueryDTO query);

	public List<LiteappConfigEntity> getLiteappConfig(@Param("guid") String guid, @Param("version") String version);

	public List<StoreLiteappEntity> getLiteappLatestRelease(@Param("query") LiteappVersionQueryDTO query);

}
