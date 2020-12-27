package xyz.jpcr.healthreportfinal.service;

import xyz.jpcr.healthreportfinal.entity.Student;
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
public interface IStudentService extends IService<Student> {
    public Student getStudentBySno(int sno);
    public int updateLastFillingTime(int sno,Date date);
    public boolean canFilling(int sno);
    public List<Student> getStudentByCno(int cno);
    public int changePwd(int sno,String pwd);

}
