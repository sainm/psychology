package com.mindvoice.psych.system.service;


import com.mindvoice.psych.system.model.dto.UserOnlineDTO;

import java.util.List;

/**
 * 用户在线状态服务
 * 负责维护用户的在线状态和相关统计
 *
 * @author liu
 * @since 3.0.0
 */
public interface UserOnlineService {

    /**
     * 用户上线
     *
     * @param username  用户名
     * @param sessionId WebSocket会话ID（可选）
     */
    public void userConnected(String username, String sessionId) ;

    /**
     * 用户下线
     *
     * @param username 用户名
     */
    public void userDisconnected(String username);

    /**
     * 获取在线用户列表
     *
     * @return 在线用户名列表
     */
    public List<UserOnlineDTO> getOnlineUsers();

    /**
     * 获取在线用户数量
     *
     * @return 在线用户数
     */
    int getOnlineUserCount();

    /**
     * 检查用户是否在线
     *
     * @param username 用户名
     * @return 是否在线
     */
    public boolean isUserOnline(String username) ;

} 
