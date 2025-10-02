package com.mindvoice.psych.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindvoice.psych.common.pojo.CommonResult;
import com.mindvoice.psych.common.pojo.PageResult;
import com.mindvoice.psych.system.model.query.LogPageQuery;
import com.mindvoice.psych.system.model.vo.LogPageVO;
import com.mindvoice.psych.system.model.vo.VisitStatsVO;
import com.mindvoice.psych.system.model.vo.VisitTrendVO;
import com.mindvoice.psych.system.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.mindvoice.psych.common.pojo.CommonResult.success;

/**
 * 日志控制层
 *
 * @author liu
 * @since 2.10.0
 */
@Tag(name = "10.日志接口")
@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @Operation(summary = "日志分页列表")
    @GetMapping("/page")
    public CommonResult<PageResult<LogPageVO>> getLogPage(LogPageQuery queryParams) {
        PageResult<LogPageVO> result = logService.getLogPage(queryParams);
        return success(result);
    }

    @Operation(summary = "获取访问趋势")
    @GetMapping("/visit-trend")
    public CommonResult<VisitTrendVO> getVisitTrend(@Parameter(description = "开始时间", example = "yyyy-MM-dd") @RequestParam String startDate, @Parameter(description = "结束时间", example = "yyyy-MM-dd") @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        VisitTrendVO data = logService.getVisitTrend(start, end);
        return success(data);
    }

    @Operation(summary = "获取访问统计")
    @GetMapping("/visit-stats")
    public CommonResult<VisitStatsVO> getVisitStats() {
        VisitStatsVO result = logService.getVisitStats();
        return success(result);
    }

}
