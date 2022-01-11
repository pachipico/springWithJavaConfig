document.getElementById("trigger").addEventListener("click", (e) => {
  document.getElementById("files").click();
});
const regexp = new RegExp(".(exe|sh|bat|js|msi|dll)$");
const test = new RegExp(".(png|jpg|jpeg|gif|PNG)$");
const maxSize = 1024 * 1024;
const fileSizeValidation = ({ name, size }) => {
  console.log(name, size);
  if (regexp.test(name)) {
    return 0;
  } else if (!test.test(name)) {
    return 0;
  } else {
    return size > maxSize ? 0 : 1;
  }
};

document.getElementById("files").addEventListener("change", (e) => {
  const fileObject = e.target.files;
  console.log(fileObject);
  let fileZone = document.getElementById("fileZone");
  fileZone.innerHTML = "";
  let ul = `<ol class="list-group list-group-flush">`;
  let isOk = 1;
  Array.from(fileObject).forEach((file) => {
    console.log(file);
    let validResult = fileSizeValidation(file);
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
  });
  ul += "</ul>";
  document.getElementById("regBtn").disabled = isOk ? false : true;
  fileZone.innerHTML = ul;
});
