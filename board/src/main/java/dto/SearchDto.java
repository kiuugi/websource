package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchDto {
    private String criteria;
    private String keyword;

    private int page; // 현재 들어가있는 페이지
    private int amount; // 페이지에 보여줄 게시물 개수

    public SearchDto(String criteria, String keyword) {
        this.criteria = criteria;
        this.keyword = keyword;
    }

    public SearchDto(int page, int amount) {
        this.page = page;
        this.amount = amount;
    }
}
