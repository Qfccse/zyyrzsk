package cn.edu.tongji.backend.test.service;

import cn.edu.tongji.backend.test.mapper.ReportFormMapper;
import cn.edu.tongji.backend.test.pojo.ReportForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportFormService {
    @Autowired
    private ReportFormMapper reportFormMapper;

    public void insertReportForm(ReportForm reportForm) {
        reportFormMapper.insertIntoReport(reportForm);
    }

    public List<ReportForm> selectLabReportForm(int l_id,int s_id){
        return reportFormMapper.getLabReportForm(l_id,s_id);
    }

    public List<String> selectLabReportImages(int rf_id){
        return reportFormMapper.getReportImages(rf_id);
    }

    public void insertImage(int rf_id,String path,String file_name){
        reportFormMapper.insertIntoReportImages(rf_id,path,file_name);
    }
}
