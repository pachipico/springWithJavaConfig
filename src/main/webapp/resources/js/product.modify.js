const removeFilefromServer = async (uuid) => {
  try {
    const config = {
      method: "DELETE",
    };
    const res = await fetch(`/product/file/${uuid}`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("file-x")) {
    removeFilefromServer(e.target.dataset.uuid).then((result) => {
      if (parseInt(result)) {
        e.target.closest("li").remove();
        alert("File deleted successfully");
      }
    });
  }
});
