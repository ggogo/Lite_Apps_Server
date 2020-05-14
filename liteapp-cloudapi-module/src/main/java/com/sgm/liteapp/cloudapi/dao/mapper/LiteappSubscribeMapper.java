package com.sgm.liteapp.cloudapi.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappSubscribeEntity;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappSubscribeLogEntity;
import com.sgm.liteapp.cloudapi.dao.entity.StoreLiteappEntity;
import com.sgm.liteapp.cloudapi.dto.LiteAppUpdateListDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;

public interface LiteappSubscribeMapper extends BaseMapper<LiteappSubscribeEntity> {

	public List<LiteappSubscribeEntity> getUserSubscribedLiteappList(@Param("query") LiteappQueryDTO query);

	public int subscribeApp(@Param("entity") LiteappSubscribeEntity entity);
	public int subscribeAppLog(@Param("entity") LiteappSubscribeLogEntity entity);
	public int unsubscribeApp(@Param("entity") LiteappSubscribeEntity entity);
	public List<LiteAppUpdateListDTO> getUserSubscribedAndBuildInLatestAppList(@Param("query") LiteappQueryDTO query);
}
