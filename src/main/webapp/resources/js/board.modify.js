const removeFilefromServer = async (uuid) => {
  try {
    const config = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
    };
    const res = await fetch(`/board/file/${uuid}`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("file-x")) {
    removeFilefromServer(e.target.dataset.uuid).then((result) => {
      alert(parseInt(result) ? "파일 삭제 성공" : "파일 삭제 실패");
      e.target.closest("li").remove();
    });
  }
});
