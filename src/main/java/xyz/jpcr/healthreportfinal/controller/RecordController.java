package xyz.jpcr.healthreportfinal.controller;


import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import xyz.jpcr.healthreportfinal.entity.Record;
import xyz.jpcr.healthreportfinal.entity.Student;
import xyz.jpcr.healthreportfinal.service.IRecordService;
import xyz.jpcr.healthreportfinal.service.IStudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
@Api(value = "填报记录controller", tags = "填报记录操作接口")
@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {
    @Resource
    IRecordService recordService;
    @Resource
    IStudentService studentService;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @ApiOperation("获取指定学号的所有填报记录")
    @ApiParam(name = "sno", value = "学号")
    @GetMapping("/show_student_record/{sno}")
    public List<Record> showAllRecordBySno(@PathVariable int sno) {
        return recordService.showAllRecordBySno(sno);
    }

    @ApiOperation("获取指定班级的所有填报记录")
    @ApiParam(name = "cno", value = "班级编号")
    @GetMapping("/show_class_record/{cno}")
    public List<Record> showAllRecordByCno(@PathVariable int cno) {
        return recordService.showAllRecordByCno(cno);
    }


    @ApiOperation("获取指定日期的所有填报记录")
    @ApiParam(name = "date", value = "日期")
    @GetMapping("/show_date_record/{date}")
    public List<Record> showAllRecordByDate(@PathVariable Date date) {
        return recordService.showAllRecordByDate(date);
    }

    @ApiOperation("添加填报记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno", value = "学号"),
            @ApiImplicitParam(name = "healthqrcode", value = "健康码状态"),
            @ApiImplicitParam(name = "tripqrcode", value = "行程码状态"),
            @ApiImplicitParam(name = "address", value = "地址")
    })
    @PostMapping("/add/{sno}&{healthqrcode}&{tripqrcode}&{address}")
    public boolean addRecord(@PathVariable int sno,
                         @PathVariable String healthqrcode,
                         @PathVariable String tripqrcode,
                         @PathVariable String address) {
        Student stu = studentService.getStudentBySno(sno);
        Record record = new Record();
        record.setSno(stu.getSno());
        record.setCno(stu.getCno());
        record.setHealthqrcode(healthqrcode);
        record.setTripqrcode(tripqrcode);
        record.setAddress(address);
        Date date = new Date();
        record.setDate(date);
        studentService.updateLastFillingTime(sno, date);
        return recordService.save(record);
    }

}

