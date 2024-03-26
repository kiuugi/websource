package dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private int bno;
    private String name;
    private String password;
    private String title;
    private String content;
    private String attach;
    private int re_ref;
    private int re_lev;
    private int re_seq;
    private int read_count;
    private Date regDate;

}
