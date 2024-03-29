package action;

import java.io.File;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardWriteAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        BoardDto insertDto = new BoardDto();
        insertDto.setName((req.getParameter("name")));
        insertDto.setPassword((req.getParameter("password")));
        insertDto.setTitle((req.getParameter("title")));
        insertDto.setContent((req.getParameter("content")));

        // 페이지 나누기 추가
        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        // 한글일 경우 get으로 넘어올 때 깨짐 => 인코딩
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        // 파일 업로드 처리
        Part part = req.getPart("attach");
        String fileName = getFileName(part);

        String saveDir = "c:\\upload"; // c:\\upload\\1.jpg

        if (!fileName.isEmpty()) {
            // 중복 파일명은 저장을 해주지 않음 => 서버에 저장 시 다른 이름 사용
            // 고유한 값 생성 4cc2dde5-e7a6-43b5-9cf7-83410716bad8 + _ + 사용자가 올린 파일명
            UUID uuid = UUID.randomUUID();
            File uploadFile = new File(saveDir + File.separator + uuid + "_" + fileName);

            part.write(uploadFile.toString()); // 서버의 디스크에 파일 저장
            insertDto.setAttach(uploadFile.getName());
        }

        System.out.println(insertDto);

        BoardService service = new BoardServiceImpl();

        if (!service.insert(insertDto)) {
            path = "/view/qna_board_write.jsp?page=" + page + "&amount=" + amount + "&criteria=" + criteria
                    + "&keyword="
                    + keyword;
        } else {
            path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword="
                    + keyword;
        }

        return new ActionForward(path, true);
    }

    private String getFileName(Part part) {
        String header = part.getHeader("content-disposition");
        String[] arr = header.split(";");
        for (int i = 0; i < arr.length; i++) {
            String temp = arr[i];
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        // Content-Disposition: inline 이런 모양의 header Content를 읽어와서 ; 으로 분리하고 filename으로
        // 시작하는걸 찾아서 filename.* 만 찾는 메소드
        // Content-Disposition: attachment
        // Content-Disposition: attachment; filename="filename.jpg"
        return "";
    }

}
