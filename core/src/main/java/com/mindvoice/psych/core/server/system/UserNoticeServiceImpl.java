package com.mindvoice.psych.core.server.system;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mindvoice.psych.core.security.util.SecurityUtils;
import com.mindvoice.psych.system.model.entity.UserNotice;
import com.mindvoice.psych.system.model.query.NoticePageQuery;
import com.mindvoice.psych.system.model.vo.NoticePageVO;
import com.mindvoice.psych.system.model.vo.UserNoticePageVO;
import com.mindvoice.psych.system.service.UserNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户公告状态服务实现类
 *
 * @author liu
 * @since 2024-08-28 16:56
 */
@Service
@RequiredArgsConstructor
public class UserNoticeServiceImpl extends ServiceImpl<UserNoticeMapper, UserNotice> implements UserNoticeService {

    private final UserNoticeMapper userNoticeMapper;

    /**
     * 全部标记为已读
     *
     * @return 是否成功
     */
    @Override
    public boolean readAll() {
        Long userId = SecurityUtils.getUserId();
        return this.update(new LambdaUpdateWrapper<UserNotice>()
                .eq(UserNotice::getUserId, userId)
                .eq(UserNotice::getIsRead, 0)
                .set(UserNotice::getIsRead, 1)
        );
    }

    /**
     * 我的通知公告分页列表
     *
     * @param page        分页对象
     * @param queryParams 查询参数
     * @return 通知公告分页列表
     */
    @Override
    public IPage<UserNoticePageVO> getMyNoticePage(Page<NoticePageVO> page, NoticePageQuery queryParams) {
        return this.getBaseMapper().getMyNoticePage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
    }


}
