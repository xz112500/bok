package com.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private String username;

    private String date;

    private String comment;
    public Comment(String username,String comment){
        this.comment=comment;
        this.username=username;
    }
}
