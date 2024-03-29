// 폼이 비어있는지 확인

document.querySelector(".btn-danger").addEventListener("click", (e) => {
  // location.href = "/qList.do";
  const action = document.querySelector("form");
  action.action = "/qList.do";

  action.submit();
});

// document.querySelector("form").addEventListener("submit", (e) => {

// });
