package com.tangu.tangucore.tcmts2.util.excel;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReportUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReportUtil.class);

    public ReportUtil() {
    }

    public static void export(HttpServletRequest request, HttpServletResponse response, ExcelExportVO para) throws ServletException, IOException {
        WritableWorkbook wb = ExcelUtils.createWritableWorkBook(request, response);
        String wbName = null;

        try {
            wbName = ExcelUtils.fillWorkbook(wb, para);
        } catch (Exception var7) {
            logger.error(var7.getMessage(), var7);
        }

        if (para.getFileName() != null && !"".equals(para.getFileName())) {
            wbName = para.getFileName();
        }

        logger.debug("wbName:" + wbName);
        if (wbName != null) {
            wbName = new String(wbName.getBytes("GBK"), "ISO-8859-1");
        }

        response.addHeader("Content-Disposition", "attachment;filename=\"" + wbName + ".xls\"");
        wb.write();

        try {
            wb.close();
        } catch (WriteException var6) {
            logger.error(var6.getMessage(), var6);
        }

    }
}
