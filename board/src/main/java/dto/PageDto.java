package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageDto {

    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private SearchDto searchDto; // 객체 SearchDto 포함시킴
    private int total;

    public PageDto(SearchDto searchDto, int total) {
        this.searchDto = searchDto;
        this.total = total;

        endPage = (int) (Math.ceil(searchDto.getPage() / 10.0)) * 10; // 올림한 값을 반환함
        startPage = endPage - 9;

        int realEnd = (int) (Math.ceil((total / 1.0) / searchDto.getAmount()));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}
