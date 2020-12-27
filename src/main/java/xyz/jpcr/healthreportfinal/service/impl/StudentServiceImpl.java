package xyz.jpcr.healthreportfinal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import xyz.jpcr.healthreportfinal.entity.Student;
import xyz.jpcr.healthreportfinal.mapper.StudentMapper;
import xyz.jpcr.healthreportfinal.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Resource
    StudentMapper studentMapper;
    @Override
    public Student getStudentBySno(int sno) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sno", sno);
        return studentMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateLastFillingTime(int sno,Date date) {
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<Student>();
        updateWrapper.eq("sno", sno);
        Student stu = new Student();
        stu.setLastfillingtime(date);
        return studentMapper.update(stu, updateWrapper);
    }

    @Override
    public boolean canFilling(int sno) {
        Student stu = getStudentBySno(sno);
        System.out.println(stu.getLastfillingtime());
        if(stu == null || stu.getLastfillingtime() == null)
            return false;
        Calendar cur = Calendar.getInstance();
        Calendar pre = Calendar.getInstance();
        cur.setTime(new Date());
        cur.setTime(stu.getLastfillingtime());
        return (cur.get(Calendar.YEAR) != pre.get(Calendar.YEAR))
                || (cur.get(Calendar.MONTH) != pre.get(Calendar.MONTH))
                || (cur.get(Calendar.DAY_OF_MONTH) != pre.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public List<Student> getStudentByCno(int cno) {
        return studentMapper.getStudentByCno(cno);
    }

    @Override
    public int changePwd(int sno,String pwd) {
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<Student>();
        updateWrapper.eq("sno", sno);
        Student stu = new Student();
        stu.setPassword(pwd);
        return studentMapper.update(stu, updateWrapper);
    }

}
