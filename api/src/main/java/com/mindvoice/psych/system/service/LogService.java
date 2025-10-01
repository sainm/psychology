package com.mindvoice.psych.system.service;

import com.mindvoice.psych.system.model.vo.VisitStatsVO;
import com.mindvoice.psych.system.model.vo.VisitTrendVO;

import java.time.LocalDate;

/**
 * 系统日志 服务接口
 *
 * @author liu
 * @since 2.10.0
 */
public interface LogService {



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
