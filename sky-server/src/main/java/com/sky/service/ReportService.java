package com.sky.service;

import com.sky.dto.GoodsSalesDTO;
import com.sky.vo.*;

import java.time.LocalDate;

public interface ReportService {

    /**
     * 营业额统计
     */
    TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end);

    /**
     * 用户统计
     */
    UserReportVO userStatistics(LocalDate begin, LocalDate end);

    /**
     * 订单统计
     */
    OrderReportVO orderStatistics(LocalDate begin, LocalDate end);

    /**
     * 销量统计
     */
    SalesTop10ReportVO salesStatistics(LocalDate begin, LocalDate end);
}
