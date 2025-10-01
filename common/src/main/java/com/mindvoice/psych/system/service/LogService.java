package com.mindvoice.psych.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mindvoice.psych.system.model.entity.Log;
import com.mindvoice.psych.system.model.query.LogPageQuery;
import com.mindvoice.psych.system.model.vo.LogPageVO;
import com.mindvoice.psych.system.model.vo.VisitStatsVO;
import com.mindvoice.psych.system.model.vo.VisitTrendVO;


import java.time.LocalDate;
import java.util.List;

/**
 * 系统日志 服务接口
 *
 * @author liu
 * @since 2.10.0
 */
public interface LogService extends IService<Log> {

    /**
     * 获取日志分页列表
     */
    Page<LogPageVO> getLogPage(LogPageQuery queryParams);


    /**
     * 获取访问趋势
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    VisitTrendVO getVisitTrend(LocalDate startDate, LocalDate endDate);

    /**
     * 获取访问统计
     */
    VisitStatsVO getVisitStats();

}
