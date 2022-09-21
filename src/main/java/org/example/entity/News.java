package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dz
 * @date 2022-09-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Integer nId;
    private String nImg;
    private String nTitle;
    private String nContent;
}
