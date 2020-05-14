package com.sgm.liteapp.cloudapi.dao.mapper;

import com.sgm.liteapp.cloudapi.dao.entity.LiteAppUserCardEntity;
import com.sgm.liteapp.cloudapi.dto.LiteAppBaseDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppBaseInfoDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardDisplayDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardQueryDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiteAppUserCardMapper {

    List<LiteAppBaseInfoDTO> queryCardAssociatedApp(LiteAppCardQueryDTO dto);

    int insertUserCardListWithForEach(List<LiteAppUserCardEntity> entities);

    List<LiteAppUserCardEntity> queryUserCardsByAppGuidAndAppVersion(@Param("appGuid") String appGuid,@Param("appVersion") String appVersion );

    int batchDeleteUserCardByGUIDList(List<String> guidList);

    List<LiteAppUserCardEntity> queryUserCardByIdpUserIdAndCardInfo(@Param("idpUserId")String idpUserId,@Param("cardGuid")String cardGuid,@Param("cardVersion")String cardVersion);
    List<LiteAppUserCardEntity> queryUserCardByIdpUserId(@Param("idpUserId")String idpUserId);
    int updateSortNumberOfUserCardByIdpUserIdAndCardInfo(@Param("idpUserId")String idpUserId,@Param("cardGuid")String cardGuid,@Param("cardVersion")String cardVersion, @Param("sortNumber")int sortNumber);
    int insertSortNumberOfUserCardByIdpUserIdAndCardInfo(@Param("id")String id,@Param("idpUserId")String idpUserId,@Param("cardGuid")String cardGuid,@Param("cardVersion")String cardVersion, @Param("sortNumber")int sortNumber);
    int updateDisplayOfUserCardByIdpUserIdAndCardInfo(@Param("idpUserId")String idpUserId,@Param("cardGuid")String cardGuid,@Param("cardVersion")String cardVersion, @Param("display")int display);
    int insertDisplayOfUserCardByIdpUserIdAndCardInfo(@Param("id")String id,@Param("idpUserId")String idpUserId,@Param("cardGuid")String cardGuid,@Param("cardVersion")String cardVersion, @Param("display")int display);

    List<LiteAppCardDisplayDTO> queryUserCardsIncludingBuildInByAppInfo(LiteAppBaseDTO query);
}
