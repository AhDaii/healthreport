package xyz.jpcr.healthreportfinal.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.jpcr.healthreportfinal.entity.Student;
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
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select sno,sname,password,lastfillingtime from Student where cno = #{cno}")
    public List<Student> getStudentByCno(@Param("cno") int cno);
}
