package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Course;

/**
 * @author dz
 * @date 2022-09-23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreVO {
    private Integer cId;
    private String cName;
    private Integer score;
}
