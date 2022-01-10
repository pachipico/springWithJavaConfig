document.getElementById("trigger").addEventListener("click", (e) => {
  document.getElementById("files").click();
});

document.addEventListener("change", (e) => {
  if (e.target.id == "files") {
    const fileObject = document.getElementById("files").files;
    Array.from(fileObject).forEach(({ name, size }) => {
      console.log(`${name} 의 크기는 ${size / 1024 / 1024}`);
    });
  }
});
