package com.sgm.liteapp.cloudapi.webapp.controller;

import com.sgm.liteapp.cloudapi.dto.LiteAppBaseInfoDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardDisplayDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppBaseDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardQueryDTO;
import com.sgm.liteapp.cloudapi.service.LiteAppCardService;
import com.sgm.liteapp.commons.web.ResponseBody;
import com.sgm.liteapp.commons.web.SessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/card")
public class LiteAppCardController extends LiteAppBaseController {

    @Autowired
    private LiteAppCardService cardService;

    @PostMapping("/display")
    @ApiOperation(value = "获取默认和当前车机用户所订阅的卡片，并按照自定义的顺序排序显示", notes = "获取默认和当前车机用户所订阅的卡片，并按照自定义的顺序排序显示")
    public ResponseBody<List<LiteAppCardDisplayDTO>> display(@RequestBody LiteAppBaseDTO dto) {
        dto.setIdpUserId(SessionContext.getUser().getUid());
        return ResponseBody.data(responseMessageSuccess, cardService.queryCardList(dto));
    }

    @PostMapping("/associatedapp")
    @ApiOperation(value = "根据卡片的ID获取对应的轻应用配置信息", notes = "根据卡片的ID获取对应的轻应用配置信息")
    public ResponseBody<LiteAppBaseInfoDTO> associatedApp(@RequestBody LiteAppCardQueryDTO dto) {
        dto.setIdpUserId(SessionContext.getUser().getUid());
        return ResponseBody.data(responseMessageSuccess, cardService.queryCardAssociatedApp(dto));
    }

    @PostMapping("/displayconfig/sort")
    @ApiOperation(value = "调整当前卡片显示的顺序", notes = "调整当前卡片显示的顺序")
    public ResponseBody<String> sortCardDisplay(@RequestBody LiteAppCardQueryDTO dto) {
        dto.setIdpUserId(SessionContext.getUser().getUid());
        return ResponseBody.data(responseMessageSuccess, cardService.sortCardDisplay(dto));
    }

    @PostMapping("/displayconfig/switch")
    @ApiOperation(value = "卡片显示/隐藏", notes = "卡片显示/隐藏")
    public ResponseBody<String> setCardDisplay(@RequestBody LiteAppCardQueryDTO dto) {
        dto.setIdpUserId(SessionContext.getUser().getUid());
        return ResponseBody.data(responseMessageSuccess, cardService.setCardDisplay(dto));
    }

}
