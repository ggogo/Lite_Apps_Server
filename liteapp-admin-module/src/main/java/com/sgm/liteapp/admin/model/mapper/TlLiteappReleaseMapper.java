package com.sgm.liteapp.admin.model.mapper;

import com.sgm.liteapp.admin.model.dto.LiteappMainDto;
import com.sgm.liteapp.admin.model.entity.TlLiteappRelease;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 轻应用发布历史 Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-04-21
 */
public interface TlLiteappReleaseMapper extends BaseMapper<TlLiteappRelease> {

	public List<TlLiteappRelease> liteappMainQuery(Page<TlLiteappRelease> page, @Param("dto") LiteappMainDto dto);

	public void updateliteappReleasePackageURL(@Param("releaseId") String releaseId,
			@Param("packageUrl") String packageUrl);

}
