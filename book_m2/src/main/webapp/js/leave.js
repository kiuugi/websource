document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();
  const password = document.querySelector("#password");
  if (!password.value) {
    alert("비밀번호 확인");
    password.focus();
    return;
  }
  alert("탈퇴.");
  e.target.submit();
});
