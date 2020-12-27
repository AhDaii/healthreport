package xyz.jpcr.healthreportfinal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import xyz.jpcr.healthreportfinal.entity.Record;
import xyz.jpcr.healthreportfinal.mapper.RecordMapper;
import xyz.jpcr.healthreportfinal.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hunz1
 * @since 2020-12-23
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {
    @Resource
    RecordMapper recordMapper;

    @Override
    public List<Record> showAllRecordBySno(int sno) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sno", sno);
        return recordMapper.selectList(queryWrapper);
    }

    @Override
    public List<Record> showAllRecordByCno(int cno) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cno", cno);
        return recordMapper.selectList(queryWrapper);
    }

    @Override
    public List<Record> showAllRecordByDate(Date date) {
        return recordMapper.showAllRecordByDate(date);
    }
}
