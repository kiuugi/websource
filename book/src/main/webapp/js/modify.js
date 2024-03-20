document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();
  const code = document.querySelector("#code");
  const price = document.querySelector("#price");
  if (!code.value || code.value.length != 4 || isNaN(code.value)) {
    alert("code 값이 비어 있거나 숫자 4자리가 아닙니다.");
    code.focus();
    return;
  } else if (!price.value || isNaN(price.value)) {
    alert("price 값이 비어 있거나 숫자가 아닙니다.");
    return;
  }
  e.target.submit();
});
