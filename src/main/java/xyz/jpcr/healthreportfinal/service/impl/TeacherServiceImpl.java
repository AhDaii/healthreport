package xyz.jpcr.healthreportfinal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import xyz.jpcr.healthreportfinal.entity.Teacher;
import xyz.jpcr.healthreportfinal.mapper.TeacherMapper;
import xyz.jpcr.healthreportfinal.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hunz1
 * @since 2020-12-23
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Resource
    TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherByTno(int tno) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tno", tno);
        return teacherMapper.selectOne(queryWrapper);

    }

    @Override
    public int checkPwd(int tno, String pwd) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tno", tno);
        queryWrapper.eq("password", pwd);
        return teacherMapper.selectList(queryWrapper).size();
    }
}
