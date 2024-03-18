// 세션값 저장 클릭 시 sessionSet.jsp 이동
// 세션값 삭제 클릭 시 sessionDel.jsp 이동
// 세션값 초기화 클릭 시
document.querySelector("div button:nth-child(1)").addEventListener("click", (e) => {
  location.href = "sessionSet.jsp"; // 스크립트에서 페이지 이동
});
document.querySelector("div button:nth-child(2)").addEventListener("click", (e) => {
  location.href = "sessionDel.jsp";
});
document.querySelector("div button:nth-child(3)").addEventListener("click", (e) => {
  location.href = "sessionInv.jsp";
});
