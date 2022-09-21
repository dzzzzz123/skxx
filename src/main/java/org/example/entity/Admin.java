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
public class Admin {
    private Integer aId;
    private String aAccount;
    private String aPwd;
}
