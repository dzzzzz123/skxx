package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dz
 * @date 2022-09-24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreVO {
    private Integer cId;
    private Integer sId;
    private String score;
    private String cName;
    private String sName;
}
