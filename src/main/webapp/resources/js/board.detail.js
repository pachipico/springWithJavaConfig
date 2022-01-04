const ses = document.querySelector("input[name=ses]").value;

// const getCommentList = async (pno) => {
//   try {
//     const res = await fetch(`/comment/list?pno=${pno}`);
//     const result = await res.json();
//     console.log(result);
//     return result;
//   } catch (e) {
//     console.log(e);
//   }
// };

// const showCommentList = async (list) => {
//   const cmtContainer = document.querySelector("#cmtContainer");
//   let comments = "";
//   list.forEach((comment) => {
//     if (ses == comment.writer) {
//       comments += `
//       <tr>
//       <th scope="row">${comment.writer}</th>
//       <td>${comment.modAt}</td>
//       <td id="content">${comment.content}</td>
//       <td>
//           <button id="cmtEditBtn" data-cno="${comment.cno}" class="btn btn-sm btn-outline-warning">edit</button>
//           <button id="cmtEditSbm" data-cno="${comment.cno}" style="display:none" class="btn btn-sm btn-outline-primary">submit</button>
//           <button id="cmtDelBtn" data-cno="${comment.cno}" class="btn btn-sm btn-outline-danger">delete</button>
//         </td>
//         </tr>
//         `;
//     } else {
//       comments += `
//       <tr>

//         <th scope="row">${comment.writer}</th>
//         <td>${comment.modAt}</td>
//         <td>${comment.content}</td>
//         <td>

//         </td>
//       </tr>`;
//     }
//   });
//   cmtContainer.innerHTML = comments;
// };

// const renderComment = (pno) => {
//   getCommentList(pno).then((list) => showCommentList(list));
// };

// const sendCmtToServer = async (pno, email, content) => {
//   try {
//     const url = "/comment/post";
//     const config = {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json; charset=utf-8",
//       },
//       body: JSON.stringify({ pno, writer: email, content }),
//     };
//     const res = await fetch(url, config);
//     const result = await res.text();
//     return result;
//   } catch (e) {
//     console.log(e);
//   }
// };

// const deleteCmt = async (cno) => {
//   try {
//     const url = "/comment/delete";
//     const config = {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json; charset=utf-8",
//       },
//       body: JSON.stringify({ cno }),
//     };
//     const res = await fetch(url, config);
//     const result = await res.text();
//     return result;
//   } catch (e) {
//     console.log(e);
//   }
// };

// const modifyCmt = async (cno, content) => {
//   try {
//     const url = "/comment/modify";
//     const config = {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json; charset=utf-8",
//       },
//       body: JSON.stringify({ cno, content }),
//     };
//     const res = await fetch(url, config);
//     const result = await res.text();
//     return result;
//   } catch (e) {
//     console.log(e);
//   }
// };

// document.addEventListener("DOMContentLoaded", () => {
//   getCommentList(document.querySelector("input[name=pno]").value).then((list) => showCommentList(list));
// });

document.addEventListener("click", (e) => {
  if (e.target.id == "delBtn") {
    document.querySelector("#delForm").submit();
  }
  // if (e.target.id == "cmtAddBtn") {
  //   const container = document.querySelector("#cmtForm");
  //   const email = container.querySelector("input[name=writer]").value;
  //   const content = container.querySelector("textArea").value;
  //   const pno = container.querySelector("input[name=pno]").value;

  //   sendCmtToServer(pno, email, content).then(() => {
  //     renderComment(pno);
  //   });
  // }
  // if (e.target.id == "cmtDelBtn") {
  //   const cno = e.target.dataset.cno;
  //   deleteCmt(cno).then((result) => {
  //     if (result) {
  //       renderComment(document.querySelector("input[name=pno]").value);
  //     }
  //   });
  // }
  // if (e.target.id == "cmtEditBtn") {
  //   const content = e.target.closest("tr").querySelector("#content").innerHTML;
  //   e.target.closest("tr").querySelector("#content").innerHTML = `
  //     <input type="text" name="content" value="${content}"/>
  //     `;
  //   const cmtEditSbm = e.target.closest("tr").querySelector("#cmtEditSbm");
  //   cmtEditSbm.style.display = "";
  //   e.target.style.display = "none";
  // }
  // if (e.target.id == "cmtEditSbm") {
  //   const content = e.target.closest("tr").querySelector("input").value;
  //   modifyCmt(e.target.dataset.cno, content).then((result) => {
  //     if (result) {
  //       renderComment(document.querySelector("input[name=pno]").value);
  //     }
  //   });
  // e.target.parentNode.replaceChild(edit, e.target);
  // }
});
