// submit : 발생 시
// submit : 중지
// code(4자리인지), title, writer, price 비어 있는지 확인

document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();
  const code = document.querySelector("#code");
  const title = document.querySelector("#title");
  const writer = document.querySelector("#writer");
  const price = document.querySelector("#price");
  if (!code.value || code.value.length != 4 || isNaN(code.value)) {
    alert("code 값이 비어 있거나 숫자 4자리가 아닙니다.");
    code.focus();
    return;
  } else if (!title.value) {
    alert("title 값이 비어 있습니다.");
    return;
  } else if (!writer.value) {
    alert("writer 값이 비어 있습니다.");
    return;
  } else if (!price.value || isNaN(price.value)) {
    alert("price 값이 비어 있거나 숫자가 아닙니다.");
    return;
  }
  e.target.submit();
});

// 값이 다 존재한다면 submit
