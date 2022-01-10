document.getElementById("trigger").addEventListener("click", (e) => {
  document.getElementById("files").click();
});

document.addEventListener("change", (e) => {
  if (e.target.id == "files") {
    const fileObject = document.getElementById("files").files;
    console.log(fileObject);
    let fileZone = document.getElementById("fileZone");
    fileZone.innerHTML = `<ul>`;
    Array.from(fileObject).forEach(({ name, size }, i) => {
      if (parseInt(size / 1024) > 1000) {
        alert(`${i + 1} 번째 파일 ${name}의 용량이 너무 큽니다. 1mb 이하의 파일을 첨부해주세요.`);
        document.getElementById("files").value = "";
        fileZone.innerHTML = "";
        return;
      }
      fileZone.innerHTML += `<li>${name}  ${parseInt(size / 1024)}kb</li>`;
    });
    fileZone.innerHTML += `</ul>`;
  }
});
