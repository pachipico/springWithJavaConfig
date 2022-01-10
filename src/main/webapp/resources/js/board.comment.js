let currPage = 1;
async function postCommentToServer(cmtData) {
  console.log(cmtData);
  try {
    const url = "/bcomment/post";
    const config = {
      method: "post",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(cmtData),
    };
    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}
document.getElementById("cmtPostBtn").addEventListener("click", () => {
  const cmtText = document.getElementById("cmtText");
  if (cmtText.value == null || cmtText.value == "") {
    alert("댓글 내용을 입력해주세요!");
    cmtText.focus();
    return false;
  } else {
    let cmtData = {
      bno: bnoVal,
      writer: document.getElementById("cmtWriter").innerText,
      content: cmtText.value,
    };
    postCommentToServer(cmtData).then((result) => {
      if (parseInt(result)) {
        alert("댓글 등록 성공~");
      }
      getCommentList(cmtData.bno);
    });
  }
});

async function spreadCommentFromServer(bno, page) {
  try {
    const resp = await fetch("/bcomment/" + bno + "/" + page);
    const pageHandler = await resp.json();
    return await pageHandler;
  } catch (error) {
    console.log(error);
  }
}

function getCommentList(bno, page = 1) {
  console.log("getCommentList  called " + bno);
  spreadCommentFromServer(bno, page).then((result) => {
    console.log(result);
    if (result.list.length) {
      const ul = document.getElementById("cmtListArea");
      ul.innerHTML = "";
      document.getElementById("cmtCnt").innerText = result.totalCount;
      for (let cvo of result.list) {
        let li = `<li data-cno="${cvo.cno}" class="list-group-item d-flex justify-content-between align-items-start">`;
        li += `<div class="ms-2 me-auto"><div class="fw-bold">Anonymous</div>`;
        li += `${cvo.content}</div>`;
        li += `<span class="badge bg-dark rounded-pill">${cvo.modAt}</span>`;
        li += `<button type="button" class="btn btn-sm btn-outline-warning py-0 mod" data-bs-toggle="modal" data-bs-target="#myModal">e</button>`;
        li += `<button type="button" class="btn btn-sm btn-outline-danger py-0 del">x</button>`;
        li += `</li>`;
        ul.innerHTML += li;
      }
      printPagination(result);
    } else {
      const ul = document.getElementById("cmtListArea");
      let li = `<li class="list-group-item">Comment List Empty</li>`;
      ul.innerHTML += li;
    }
  });
}

const printPagination = ({ next, prev, startPage, endPage, pgvo }) => {
  let pgn = document.getElementById("cmtPaging");
  pgn.innerHTML = "";
  currPage = pgvo.pageNo;
  let ul = '<ul class="pagination justify-content-center">';
  if (prev) {
    ul += `<li class="page-item">
    <a class="page-link" href="${startPage - 1}">Prev</a>
    </li>`;
  }

  for (let i = startPage; i <= endPage; i++) {
    ul += `<li class="page-item ${i == pgvo.pageNo ? "active" : ""}"
	     aria-current="page">
	      <a class="page-link " href="${i}">${i}</a>
	    </li>`;
  }

  if (next) {
    ul += `<li class="page-item">
	  <a class="page-link" href="${endPage + 1}">Next</a>
	  </li>`;
  }
  ul += `</ul>`;
  pgn.innerHTML = ul;
};

async function eraseCommentAtServer(cno) {
  try {
    const url = "/bcomment/" + cno;
    const config = {
      method: "delete",
    };
    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}
async function editCommentToServer(cmtDataMod) {
  try {
    const url = "/bcomment/" + cmtDataMod.cno;
    const config = {
      method: "put", // patch도 있음
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(cmtDataMod),
    };

    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("del")) {
    let li = e.target.closest("li");
    let cno = li.dataset.cno;
    eraseCommentAtServer(cno).then((result) => {
      alert("댓글 삭제 성공~");
      getCommentList(bnoVal, currPage);
    });
  } else if (e.target.classList.contains("mod")) {
    let li = e.target.closest("li");
    let cmtText = li.querySelector(".fw-bold").nextSibling;
    document.getElementById("cmtTextMod").value = cmtText.nodeValue;
    document.getElementById("cmtModBtn").setAttribute("data-cno", li.dataset.cno);
  } else if (e.target.id == "cmtModBtn") {
    let cmtDataMod = {
      cno: e.target.dataset.cno,
      content: document.getElementById("cmtTextMod").value,
    };
    editCommentToServer(cmtDataMod).then((result) => {
      if (parseInt(result)) {
        document.querySelector(".btn-close").click();
      }
      getCommentList(bnoVal, currPage);
    });
  } else if (e.target.classList.contains("page-link")) {
    e.preventDefault();
    let page = e.target.getAttribute("href");
    getCommentList(bnoVal, page);
  }
});
document.addEventListener("DOMContentLoaded", () => {
  getCommentList(bnoVal);
});
