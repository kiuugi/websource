// 폼이 비어있는지 확인

document.querySelector("#list").addEventListener("click", (e) => {
  // location.href =
  //   "/qList.do?page=" +
  //   document.querySelector("[name=page]").value +
  //   "&amount=" +
  //   document.querySelector("[name=amount]").value +
  //   "&criteria=" +
  //   document.querySelector("[name=criteria]").value +
  //   "&keyword=" +
  //   document.querySelector("[name=keyword]").value;
  // 이렇게 해도 상관은 없을듯

  // actionForm 보내기
  document.querySelector("#actionForm").submit();
});

document.querySelector("#writeForm").addEventListener("submit", (e) => {
  e.preventDefault();
  const name = document.querySelector("#name");
  const title = document.querySelector("#title");
  const content = document.querySelector("#content");
  const password = document.querySelector("#password");

  if (!name.value) {
    alert("작성자 이름을 기입해주세요");
    name.focus();
    return;
  } else if (!title.value) {
    alert("제목을 기입해주세요");
    title.focus();
    return;
  } else if (!content.value) {
    alert("내용을 기입해주세요");
    content.focus();
    return;
  } else if (!password.value) {
    alert("비밀번호을 기입해주세요");
    password.focus();
    return;
  }

  e.target.submit();
});
