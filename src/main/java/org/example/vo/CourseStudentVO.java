package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Course;

/**
 * @author dz
 * @date 2022-09-22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentVO extends Course {
    private String tName;
}
