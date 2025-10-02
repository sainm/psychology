package com.mindvoice.psych.controller.system;

import com.mindvoice.psych.common.pojo.CommonResult;
import com.mindvoice.psych.common.pojo.PageResult;
import com.mindvoice.psych.system.model.form.NoticeForm;
import com.mindvoice.psych.system.model.query.NoticePageQuery;
import com.mindvoice.psych.system.model.vo.NoticeDetailVO;
import com.mindvoice.psych.system.model.vo.NoticePageVO;
import com.mindvoice.psych.system.model.vo.UserNoticePageVO;
import com.mindvoice.psych.system.service.NoticeService;
import com.mindvoice.psych.system.service.UserNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.mindvoice.psych.common.pojo.CommonResult.success;

/**
 * 通知公告前端控制层
 *
 * @author liu
 * @since 2024-08-27 10:31
 */
@Tag(name = "09.通知公告")
@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    private final UserNoticeService userNoticeService;

    @Operation(summary = "通知公告分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('sys:notice:query')")
    public CommonResult<PageResult<NoticePageVO>> getNoticePage(NoticePageQuery queryParams) {
        PageResult<NoticePageVO> result = noticeService.getNoticePage(queryParams);
        return success(result);
    }

    @Operation(summary = "新增通知公告")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:notice:add')")
    public CommonResult<?> saveNotice(@RequestBody @Valid NoticeForm formData) {
        boolean result = noticeService.saveNotice(formData);
        return judge(result);
    }

    @Operation(summary = "获取通知公告表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('sys:notice:edit')")
    public CommonResult<NoticeForm> getNoticeForm(@Parameter(description = "通知公告ID") @PathVariable Long id) {
        NoticeForm formData = noticeService.getNoticeFormData(id);
        return success(formData);
    }

    @Operation(summary = "阅读获取通知公告详情")
    @GetMapping("/{id}/detail")
    public CommonResult<NoticeDetailVO> getNoticeDetail(@Parameter(description = "通知公告ID") @PathVariable Long id) {
        NoticeDetailVO detailVO = noticeService.getNoticeDetail(id);
        return success(detailVO);
    }

    @Operation(summary = "修改通知公告")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:notice:edit')")
    public CommonResult<Void> updateNotice(@Parameter(description = "通知公告ID") @PathVariable Long id, @RequestBody @Validated NoticeForm formData) {
        boolean result = noticeService.updateNotice(id, formData);
        return judge(result);
    }

    @Operation(summary = "发布通知公告")
    @PutMapping("/{id}/publish")
    @PreAuthorize("@ss.hasPerm('sys:notice:publish')")
    public CommonResult<Void> publishNotice(@Parameter(description = "通知公告ID") @PathVariable Long id) {
        boolean result = noticeService.publishNotice(id);
        return judge(result);
    }

    @Operation(summary = "撤回通知公告")
    @PutMapping("/{id}/revoke")
    @PreAuthorize("@ss.hasPerm('sys:notice:revoke')")
    public CommonResult<Void> revokeNotice(@Parameter(description = "通知公告ID") @PathVariable Long id) {
        boolean result = noticeService.revokeNotice(id);
        return judge(result);
    }

    @Operation(summary = "删除通知公告")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:notice:delete')")
    public CommonResult<Void> deleteNotices(@Parameter(description = "通知公告ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        boolean result = noticeService.deleteNotices(ids);
        return Result.judge(result);
    }

    @Operation(summary = "全部已读")
    @PutMapping("/read-all")
    public CommonResult<Boolean> readAll() {
        userNoticeService.readAll();
        return success(true );
    }

    @Operation(summary = "获取我的通知公告分页列表")
    @GetMapping("/my-page")
    public CommonResult<PageResult<UserNoticePageVO>> getMyNoticePage(NoticePageQuery queryParams) {
        PageResult<UserNoticePageVO> result = noticeService.getMyNoticePage(queryParams);
        return success(result);
    }
}
