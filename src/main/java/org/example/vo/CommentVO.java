package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Comment;

/**
 * @author dz
 * @date 2022-09-24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO extends Comment {
    private String cName;
}
