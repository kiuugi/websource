// 폼이 비어있는지 확인

document.querySelector("#list").addEventListener("click", (e) => {
  location.href = "/qList.do";
});

document.querySelector("#modify").addEventListener("click", (e) => {
  location.href = "/qModify.do?bno=" + bno;
});

document.querySelector("#delete").addEventListener("click", (e) => {
  location.href = "/view/qna_board_pwdCheck.jsp?bno=" + bno;
});

document.querySelector("#reply").addEventListener("click", (e) => {
  location.href = "/qReplyView.do?bno=" + bno;
});
