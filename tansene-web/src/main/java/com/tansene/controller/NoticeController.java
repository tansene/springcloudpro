package com.tansene.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tansene.annotation.CurrentUser;
import com.tansene.annotation.ValidationParam;
import com.tansene.base.PublicResultConstant;
import com.tansene.config.ResponseHelper;
import com.tansene.config.ResponseModel;
import com.tansene.entity.Notice;
import com.tansene.entity.User;
import com.tansene.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 消息通知表 前端控制器
 * </p>
 *
 * @author tansene
 * @since 2020/11/22
 */
@RestController
@RequestMapping("/notice")
@ApiIgnore
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 获取自己的消息列表
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @GetMapping("/infoList")
    public ResponseModel<Page<Notice>> findInfoList(@CurrentUser User user, @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                      @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) throws Exception{

        return ResponseHelper.buildResponseModel(noticeService.selectPage(new Page<>(pageIndex, pageSize),new EntityWrapper<Notice>().
                eq("mobile",user.getMobile()).orderBy("create_time",false)));
    }

    /**
     * 删除全部消息
     * @return
     * @throws Exception
     */
    @DeleteMapping
    public ResponseModel findInfoList(@CurrentUser User user) throws Exception{
        noticeService.deleteByNotices(user);
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }

    /**
     * 消息改变为已读
     * @param requestJson
     * @return
     * @throws Exception
     */
    @PostMapping("/read")
    public ResponseModel read(@ValidationParam("noticeId,isRead")
                             @RequestBody JSONObject requestJson) throws Exception{
        noticeService.read(requestJson);
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }
    /**
     * 未读消息总数
     * @return
     * @throws Exception
     */
    @GetMapping("/noReadCount")
    public ResponseModel getNoRead(@CurrentUser User user) throws Exception{
        return ResponseHelper.buildResponseModel(noticeService.selectList(new
                EntityWrapper<Notice>().where("mobile = {0} and is_read = 0",user.getMobile())).size());
    }


}

