document.querySelector("[name=search]").addEventListener("submit", (e) => {
  e.preventDefault();
  const criteria = document.querySelector("[name=criteria]");
  const keyword = document.querySelector("[name=keyword]");
  // 검색하는 경우 첫 페이지 보여주기
  document.querySelector("[name=page]").value = 1;

  if (criteria.value == "n" || keyword.value == "") {
    criteria.value = "";
    actionForm.submit();
  }

  // if (criteria.value == "n" || keyword.value == "") {
  //   alert("검색 조건을 선택해주세요");
  //   criteria.focus();
  //   return;
  // } else if (keyword.value == "") {
  //   // ==> !keyword.value / String
  //   alert("검색어를 기입해주세요");
  //   keyword.focus();
  //   return;
  // }

  e.target.submit();
});

// page 영역 가져오기
const pagination = document.querySelector(".pagination");
const actionForm = document.querySelector("#actionForm");
console.log(pagination);
// 하단의 번호 클릭 시 a 태그 기능 중지
// a 태그의 href 속성 값 가져오기
// 가져온 값을 actionForm 의 page value값으로 변경
pagination.addEventListener("click", (e) => {
  e.preventDefault();
  // 속성값 가져오기 1) 대상을 쓰고 속성명쓰기
  // 2) getAttribute("속성명")
  console.log(e.target.href); // =>http://localhost:8080/3
  console.log(e.target.getAttribute("href")); // =>3

  const href = e.target.getAttribute("href");

  actionForm.querySelector("[name='page']").value = href;
  actionForm.submit();
});

// 사용자가 목록 개수 변경 시 목록값 가져온 후
// actionForm 의 amount 값 변경 후 actionForm 전송
document.querySelector("select[name='amount']").addEventListener("change", (e) => {
  actionForm.querySelector("[name='amount']").value = e.target.value;
  actionForm.submit();
});

// 게시물 제목 클릭 시 a 태그 기능중지
// href 가져오기 => bno 값
// actionForm 안에 bno태그 추가
// actionForm.action = "/qCount.do"
const moves = document.querySelectorAll(".move");
moves.forEach((move) => {
  move.addEventListener("click", (e) => {
    e.preventDefault();
    console.log(e.target.getAttribute("href"));
    const href = e.target.getAttribute("href");

    const element = `<input type="hidden" name="bno" value="${href}">`;
    actionForm.insertAdjacentHTML("beforeend", element);
    actionForm.action = "/qCount.do";

    // actionForm.querySelector("[name='bno']").value = e.target.getAttribute("href");
    // actionForm.setAttribute("action", "/qCount.do");
    console.log(actionForm);
    actionForm.submit();
  });
});

// 새글 작성 클릭시 a태그 기능 중지
// actionForm 안에 page=1, criteria="", keyword="" 변경 후
// actionForm submit();
document.querySelector(".btn-success").addEventListener("click", (e) => {
  e.preventDefault();
  actionForm.querySelector("[name=page]").value = 1;
  actionForm.querySelector("[name=criteria]").value = "";
  actionForm.querySelector("[name=keyword]").value = "";
  actionForm.action = "/view/qna_board_write.jsp";

  actionForm.submit();
});
