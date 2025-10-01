package com.mindvoice.psych.system.service;

/**
 * 用户公告状态服务类
 *
 * @author liu
 * @since 2024-08-28 16:56
 */
public interface UserNoticeService {

    /**
     * 全部标记为已读
     *
     * @return 是否成功
     */
    boolean readAll();
}
