// submit : 발생 시
// submit : 중지
// code(4자리인지), title, writer, price 비어 있는지 확인

document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();
  const userid = document.querySelector("#userid");
  const password = document.querySelector("#password");
  const name = document.querySelector("#name");
  const email = document.querySelector("#email");
  if (!userid.value || userid.value.length < 6 || userid.value.length > 12) {
    alert("userid 값이 비어 있습니다.");
    console.log(userid.value.length);
    userid.focus();
    return;
  } else if (!password.value || password.value.length < 8 || password.value.length > 12) {
    alert("password 값이 비어 있습니다.");
    password.focus();
    return;
  } else if (!name.value) {
    alert("name 값이 비어 있습니다.");
    name.focus();
    return;
  } else if (!email.value) {
    alert("email 값이 비어 있습니다.");
    email.focus();
    return;
  }
  e.target.submit();
});

// 값이 다 존재한다면 submit
