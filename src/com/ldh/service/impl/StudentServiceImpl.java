package com.ldh.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldh.dao.StudentDao;
import com.ldh.model.Student;
import com.ldh.service.StudentService;

@Transactional
@Service("studentService")
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;
	
	//分页查询-查所需数据
	@Transactional
	public List<Student> showAllStudents(int limit, int offset) {
		offset=(offset-1)*limit;
		return studentDao.selectAllStudent(limit, offset);
	}
	//分页查询-查询记录总数
	@Transactional
	public int selectCount() {
		return studentDao.selelctCount();
	}

	//根据Id删除学生记录
	@Transactional
	public boolean delStudent(long id) {
		return studentDao.delStudentById(id);		
	}
	
	//根据姓名模糊查询
	@Transactional
	public List<Student> selectStudentByName(String search) {
		return studentDao.selectStudentByName(search);
	}

	//增加学生
    @Transactional
	public boolean addStudent(Student student) {
    	if (studentDao.selectRepeatStudent(student.getId())!=null) {
			return false;
		}else {
			return studentDao.addStudent(student);
		}
	}
    
    //更改学生信息
    @Transactional
	public boolean updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

    //导出学生excel表格
	public HSSFWorkbook export(List<Student> list) {
		String[] excelHeader = { "学号", "姓名", "专业","班级","籍贯"};  
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();    
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("学生信息");    
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
        }    
    
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            Student student = list.get(i);    
            row.createCell(0).setCellValue(student.getId());    
            row.createCell(1).setCellValue(student.getName());    
            row.createCell(2).setCellValue(student.getMajor());    
            row.createCell(3).setCellValue(student.getClassname());
            row.createCell(4).setCellValue(student.getNative_place());
        }    
        return wb;    
	}


	//读取Excel表格信息，返回list
	public List<Student> readExcel(String excel_path) throws IOException{
		InputStream is;
		is = new FileInputStream(excel_path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Student student = null;
        List<Student> list = new ArrayList<Student>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    student = new Student();
                    HSSFCell id = hssfRow.getCell(0);
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell classname = hssfRow.getCell(2);
                    HSSFCell marjor = hssfRow.getCell(3);
                    HSSFCell native_place = hssfRow.getCell(4);
                    //对应数据的格式转换，并将数据存入Student对象  
                    student.setId((long)id.getNumericCellValue());
                    student.setName(name.getStringCellValue());
                    student.setClassname(classname.getStringCellValue());
                    student.setMajor(marjor.getStringCellValue());
                    student.setNative_place(native_place.getStringCellValue());
                    list.add(student);
                }
            }
        }
        return list;
    }

	//将excel读取的数据，保存到数据库，根据学号剔除重复，返回成功导入的记录数
	@Transactional
	public int savaDataToDB(List<Student> list) {
		int count=0;
		Student student=null;
		for (int i = 0; i < list.size(); i++) {
			student=list.get(i);
			if (studentDao.selectRepeatStudent(student.getId())!=null) {
				System.out.println("已存在学号"+student.getId());
			}else {
				studentDao.addStudentFromExcel(student);
				count++;
			}
		}
		return count;
	}
			
}
