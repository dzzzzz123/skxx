package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dz
 * @date 2022-09-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Integer tId;
    private String tAccount;
    private String tPwd;
    private String tName;
    private String tBirth;
    private String tInfo;
    private String tSex;
    private String tImg;
    private String tPhone;
    private String tBankAccount;
    private String tSalary;
    private String tTitle;
}
