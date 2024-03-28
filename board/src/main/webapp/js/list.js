document.querySelector("[name=search]").addEventListener("submit", (e) => {
  e.preventDefault();
  const criteria = document.querySelector("[name=criteria]");
  const keyword = document.querySelector("[name=keyword]");

  if (criteria.value == "n") {
    alert("검색 조건을 선택해주세요");
    criteria.focus();
    return;
  } else if (keyword.value == "") {
    // ==> !keyword.value / String
    alert("검색어를 기입해주세요");
    keyword.focus();
    return;
  }

  e.target.submit();
});

// page 영역 가져오기

const pagination = document.querySelector(".pagination");
console.log(pagination);
