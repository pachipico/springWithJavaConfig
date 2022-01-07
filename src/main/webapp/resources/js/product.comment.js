const getCommentFromServer = async (pno, page) => {
    try {
        const res = await fetch(`/comment/${pno}/${page}`);
        const cmtList = await res.json();
        return await cmtList;
    } catch (e) {
        console.log(e);
    }
};

const getCommentList = (pno, page = 1) => {
    getCommentFromServer(pno, page).then((result) => {
        console.log(result);

        if (result.list.length > 0) {
            let ul = document.getElementById("cmtListArea");
            if (page == 1) {
                ul.innerHTML = "";
            }
            document.getElementById("cmtCnt").innerText = result.totalCount;
            result.list.forEach((cvo, i) => {
                const li = `
                    <li data-cno=${cvo.cno} class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">${cvo.writer}</div>
                            ${cvo.content}
                        </div>
                        <span class="badge bg-dark rounded-pill">${cvo.modAt}</span>
                        <button type="button" class="btn btn-sm btn-outline-warning py-0 mod" data-bs-toggle="modal" data-bs-target="#myModal">e</button>
                        <button type="button" class="btn btn-sm btn-outline-danger py-0 del">x</button>
                    </li>`;
                ul.innerHTML += li;
                let moreBtn = document.getElementById("moreBtn");
                if (result.pgvo.pageNo < result.endPage || result.next) {
                    moreBtn.style.visibility = "visible";
                    moreBtn.dataset.page = page + 1;
                } else {
                    moreBtn.style.visibility = "hidden";
                }
            });
        } else {
            const ul = document.getElementById("cmtListArea");
            let li = `<li class="list-group-item">Comment List Empty</li>`;
            ul.innerHTML += li;
        }
    });
};

const postCommentToServer = async (cmtData) => {
    try {
        const url = "/comment/post";
        const config = {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(cmtData),
        };
        console.log(cmtData);
        const res = await fetch(url, config);
        const result = await res.text();
        return result;
    } catch (e) {
        console.log(e);
    }
};

const editCommentToServer = async (cno) => {
    try {
        const comment = document.getElementById("cmtTextMod").value;
        const url = `/comment/modify`;
        const config = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify({
                cno,
                content: comment,
                writer: document.getElementById("cmtWriter").innerText,
            }),
        };
        const res = await fetch(url, config);
        const result = await res.text();
        return result;
    } catch (e) {
        console.log(e);
    }
};

const removeCommentFromServer = async (cno) => {
    try {
        const url = `/comment/${cno}/${pnoVal}`;
        const config = {
            method: "DELETE",
        };
        const res = await fetch(url, config);
        const result = await res.text();
        return result;
    } catch (e) {
        console.log(e);
    }
};

document.getElementById("cmtPostBtn").addEventListener("click", (e) => {
    const cmtText = document.getElementById("cmtText");
    if (cmtText.value == null || cmtText.value.trim() == "") {
        alert("댓글내용을 입력해주세요");
        cmtText.focus();
    } else {
        const cmtData = {
            pno: pnoVal,
            content: cmtText.value,
            writer: document.getElementById("cmtWriter").innerText,
        };
        postCommentToServer(cmtData).then((result) => {
            if (parseInt(result)) {
                alert("댓글 작성 성공");
                getCommentList(pnoVal, e.target.dataset.page);
            } else {
                alert("댓글 작성 실패");
            }
        });
    }
});

document.addEventListener("click", (e) => {
    if (e.target.classList.contains("del")) {
        const cno = e.target.closest("li").dataset.cno;
        removeCommentFromServer(cno).then((result) => {
            if (parseInt(result)) {
                getCommentList(pnoVal);
                alert("댓글 삭제 성공");
            }
        });
    } else if (e.target.classList.contains("mod")) {
        const li = e.target.closest("li");
        const cmtText = li.querySelector(".fw-bold").nextSibling.nodeValue;
        document.getElementById("cmtModBtn").setAttribute("data-cno", li.dataset.cno);
        document.getElementById("cmtTextMod").value = cmtText.trim();
    } else if (e.target.id == "moreBtn") {
        let page = parseInt(e.target.dataset.page);
        getCommentList(pnoVal, page);
    } else if (e.target.id == "cmtModBtn") {
        editCommentToServer(e.target.dataset.cno).then((result) => {
            if (parseInt(result)) {
                document.querySelector(".btn-close").click();
                getCommentList(pnoVal);
                alert("댓글 수정 성공");
            }
        });
    }
});
