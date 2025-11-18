const API_BASE = "http://localhost:8080";

function loadMessages(chatRoomId) {
  fetch(`${API_BASE}/chatrooms/${chatRoomId}/messages`)
    .then(response => response.json())
    .then(messages => {
      const list = document.getElementById("messageList");
      list.innerHTML = ""; // clear existing
      messages.forEach(msg => {
        const li = document.createElement("li");
        li.textContent = `${msg.sender.displayName}: ${msg.content} (${msg.timestamp})`;
        list.appendChild(li);
      });
    })
    .catch(error => console.error("Error loading messages:", error));
}

function postMessage(chatRoomId, senderId, content) {
  fetch(`${API_BASE}/messages`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      content: content,
      sender: { id: senderId },
      chatRoom: { id: chatRoomId }
    })
  })
    .then(response => response.json())
    .then(data => {
      console.log("Message created:", data);
      loadMessages(chatRoomId); // refresh list
    })
    .catch(error => console.error("Error posting message:", error));
}

function updateMessage(messageId, newContent) {
  fetch(`${API_BASE}/messages/${messageId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      content: newContent
    })
  })
    .then(response => response.json())
    .then(data => console.log("Message updated:", data))
    .catch(error => console.error("Error updating message:", error));
}

function deleteMessage(messageId) {
  fetch(`${API_BASE}/messages/${messageId}`, {
    method: "DELETE"
  })
    .then(() => console.log("Message deleted:", messageId))
    .catch(error => console.error("Error deleting message:", error));
}

document.getElementById("messageForm").addEventListener("submit", function (event) {
  event.preventDefault();
  const content = document.getElementById("messageContent").value;
  postMessage(1, 1, content); // Example: chatRoomId=1, senderId=1
  document.getElementById("messageContent").value = "";
});

loadMessages(1);

