package xyz.jpcr.healthreportfinal.service;

import org.apache.ibatis.annotations.Param;
import xyz.jpcr.healthreportfinal.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hunz1
 * @since 2020-12-23
 */
public interface IRecordService extends IService<Record> {
    public List<Record> showAllRecordBySno(int sno);
    public List<Record> showAllRecordByCno(int cno);
    public List<Record> showAllRecordByDate(Date date);
}
