// 폼이 비어있는지 확인

const actionForm = document.querySelector("#actionForm");
const element = `<input type="hidden" name="bno" value="${bno}">`;
actionForm.insertAdjacentHTML("beforeend", element);

document.querySelector("#list").addEventListener("click", (e) => {
  // bno 는 보낼 필요가 없음 => bno 삭제
  actionForm.action = "/qList.do";
  actionForm.querySelector("[name=bno]").remove();
  actionForm.submit();
});

document.querySelector("#modify").addEventListener("click", (e) => {
  // location.href = "/qModify.do?bno=" + bno;

  actionForm.action = "/qModify.do";

  actionForm.submit();
});

document.querySelector("#delete").addEventListener("click", (e) => {
  // location.href = "/view/qna_board_pwdCheck.jsp?bno=" + bno;

  actionForm.action = "/view/qna_board_pwdCheck.jsp";

  actionForm.submit();
});

document.querySelector("#reply").addEventListener("click", (e) => {
  // location.href = "/qReplyView.do?bno=" + bno;

  actionForm.action = "/qReplyView.do";

  actionForm.submit();
});
