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
public class MarStudent {
    private Integer sId;
    private String sName;
    private String sInfo;
    private String sImg;
    private String contribution;
    private String achievement;
}
