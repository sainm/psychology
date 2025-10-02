package com.mindvoice.psych.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mindvoice.psych.common.annotation.Log;
import com.mindvoice.psych.system.bo.VisitCount;
import com.mindvoice.psych.system.bo.VisitStatsBO;
import com.mindvoice.psych.system.model.query.LogPageQuery;
import com.mindvoice.psych.system.model.vo.LogPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 系统日志数据访问层
 *
 * @author liu
 * @since 2.10.0
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 获取日志分页列表
     */
    Page<LogPageVO> getLogPage(Page<LogPageVO> page, LogPageQuery queryParams);

    /**
     * 统计浏览数(PV)
     *
     * @param startDate 开始日期 yyyy-MM-dd
     * @param endDate   结束日期 yyyy-MM-dd
     */
    List<VisitCount> getPvCounts(String startDate, String endDate);

    /**
     * 统计IP数
     *
     * @param startDate 开始日期 yyyy-MM-dd
     * @param endDate   结束日期 yyyy-MM-dd
     */
    List<VisitCount> getIpCounts(String startDate, String endDate);

    /**
     * 获取浏览量(PV)统计
     */
    VisitStatsBO getPvStats();

    /**
     * 获取访问IP统计
     */
    VisitStatsBO getUvStats();
}




