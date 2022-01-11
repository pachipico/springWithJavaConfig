document.getElementById("trigger").addEventListener("click", (e) => {
  document.getElementById("files").click();
});

const regexp = new RegExp(".(exe|sh|bat|js|msi|dll)$");
const test = new RegExp(".(png|jpg|jpeg|gif|PNG)$");
const maxSize = 1024 * 1024;
const fileSizeValidation = (fileName, fileSize) => {
  if (regexp.test(fileName)) {
    return 0;
  } else if (!test.test(fileName)) {
    return 0;
  } else {
    return fileSize > maxSize ? 0 : 1;
  }
};

document.addEventListener("change", (e) => {
  if (e.target.id == "files") {
    document.getElementById("regBtn").disabled = false;
    const fileObject = document.getElementById("files").files;
    console.log(fileObject);
    let div = document.getElementById("fileZone");
    div.innerHTML = "";
    let ul = `<ol class="list-group list-group-flush">`;
    let isOk = 1;
    for (const file of fileObject) {
      let validResult = fileSizeValidation(file.name, file.size);
      isOk *= validResult;
      ul += `
      <li class="list-group-item d-flex justify-content-between align-items-start">
        <div class="ms-2 me-auto">
          <div class="fw-bold">${validResult ? "👌" : "❌"}</div>
          ${file.name}
        </div>
        <span class="badge bg-${validResult ? "success" : "danger"}" rounded-pill">${parseInt(
        file.size / 1024
      )}kb</span>
      </li>
    `;
    }
    if (!isOk) {
      document.getElementById("regBtn").disabled = true;
    }
    ul += `</ol>`;
    div.innerHTML = ul;
  }
});
