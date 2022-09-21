package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author dz
 * @date 2022-09-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer sId;
    private String sAccount;
    private String sPwd;
    private String sName;
    private String sBirth;
    private String sInfo;
    private String sAge;
    private String sSex;
    private String sImg;
    private String isMarvelous;
}
