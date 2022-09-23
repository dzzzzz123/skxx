package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dz
 * @date 2022-09-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer cId;
    private Integer sId;
    private String comment;
    private Integer star;
}
