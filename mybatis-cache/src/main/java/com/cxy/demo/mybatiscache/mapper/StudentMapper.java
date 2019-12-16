package com.cxy.demo.mybatiscache.mapper;


import com.cxy.demo.mybatiscache.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface StudentMapper {

	public StudentEntity getStudentById(int id);

	public int addStudent(StudentEntity student);

	public int updateStudentName(@Param("name") String name, @Param("id") int id);
//
//	public StudentEntity getStudentByIdWithClassInfo(int id);
}
