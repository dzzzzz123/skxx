package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dz
 * @date 2022-09-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer cId;
    private String cName;
    private String cStartDate;
    private String cEndDate;
    private Integer cCredit;
    private String cRequire;
    private Integer tId;
}
