package xyz.jpcr.healthreportfinal.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import xyz.jpcr.healthreportfinal.entity.Student;
import xyz.jpcr.healthreportfinal.entity.Teacher;
import xyz.jpcr.healthreportfinal.service.IStudentService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hunz1
 * @since 2020-12-23
 */
@Api(value = "学生controller", tags = "学生信息操作接口")
@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {
    @Resource
    IStudentService studentService;

    @ApiOperation("添加学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno", value = "学号"),
            @ApiImplicitParam(name = "sname", value = "学生姓名"),
            @ApiImplicitParam(name = "cno", value = "班级编号")
    })
    @PostMapping("/add/{sno}&{sname}&{cno}")
    public boolean addStudent(@PathVariable int sno,
                              @PathVariable String sname,
                              @PathVariable int cno) {
        Student stu = new Student();
        stu.setSno(sno);
        String strsno = String.valueOf(sno);
        stu.setPassword(strsno.substring(strsno.length()-6));//默认密码为学号后六位
        stu.setSname(sname);
        stu.setCno(cno);
        return studentService.save(stu);
    }

    @ApiOperation("获取所有学生信息")
    @GetMapping("/show_all")
    public List<Student> showAllStudent() {
        return studentService.list();
    }

    @ApiOperation("更改用户上次填报时间")
    @ApiParam(name = "sno", value = "学号")
    @PostMapping("/change/{sno}")
    public int changeLastFillingTime(@PathVariable int sno,Date date) {
        return studentService.updateLastFillingTime(sno,date);
    }

    @ApiOperation("判断用户是否可填报")
    @ApiParam(name = "sno", value = "学号")
    @GetMapping("/check/{sno}")
    public boolean canFilling(@PathVariable int sno) {
        Student stu = studentService.getStudentBySno(sno);
        if(stu.getLastfillingtime() == null)
            return true;
        else
            return studentService.canFilling(sno);
    }

    @ApiOperation("获取同一个班级的所有学生信息")
    @ApiParam(name = "cno", value = "班级号")
    @GetMapping("/show_by_cno/{cno}")
    public List<Student> showByCno(@PathVariable int cno) {
        return studentService.getStudentByCno(cno);
    }

    @ApiOperation("更改学生密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno", value = "学号"),
            @ApiImplicitParam(name = "pwd", value = "密码")
    })
    @PostMapping("/change_pwd/{sno}&{pwd}")
    public int changePwd(@PathVariable int sno, @PathVariable String pwd) {
        return studentService.changePwd(sno, pwd);
    }

    @ApiOperation("学生登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno", value = "学号"),
            @ApiImplicitParam(name = "pwd", value = "密码")
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "学号密码正确"),
            @ApiResponse(code = 0, message = "学号密码错误"),
            @ApiResponse(code = -1, message = "无此学号")
    })
    @PostMapping("/check_pwd/{sno}&{pwd}")
    public int checkPwd(@PathVariable int sno, @PathVariable String pwd) {
        if(pwd.equals("undefined") || pwd.equals(""))
            pwd = null;
        Student stu = studentService.getStudentBySno(sno);
        if(stu == null)
            return -1;
        else if(stu.getPassword() == null || stu.getPassword().equals(""))
            return pwd == null ? 1 : 0;
        else
            return stu.getPassword().equals(pwd) ? 1 : 0;
    }

    @ApiOperation("通过学号获取姓名")
    @ApiParam(name = "sno", value = "教师号")
    @GetMapping("/get_name/{sno}")
    public String getNameByNo(@PathVariable int sno) {
        Student stu = studentService.getStudentBySno(sno);
        return stu.getSname();
    }
}

