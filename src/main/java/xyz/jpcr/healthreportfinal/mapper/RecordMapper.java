package xyz.jpcr.healthreportfinal.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.jpcr.healthreportfinal.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hunz1
 * @since 2020-12-23
 */
public interface RecordMapper extends BaseMapper<Record> {
    @Select("select * from Record where to_days(date) = to_days(#{date})")
    public List<Record> showAllRecordByDate(@Param("date")Date date);
}
