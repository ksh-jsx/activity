import { useState } from "react";
import io from "socket.io-client";
import Chat from "./Chat";
const socket = io.connect("http://localhost:4000"); //프론트를 백과 연결하는 부분

function Socket() {
  const [username, setUsername] = useState("");
  const [room, setRoom] = useState("");
  const [showChat, setShowChat] = useState(false);

  const joinRoom = () => {
    if (username !== "" && room !== "") {
      //첫번째 emit
      socket.emit("join_room", room);
      setShowChat(true);
    }
  };

  return (
    <div className="App">
      {!showChat ? (
        <>
          <h3>Join A Chat</h3>
          <input
            type="text"
            placeholder="your name..."
            onChange={(e) => {
              setUsername(e.target.value);
            }}
          />
          <input
            type="text"
            placeholder="Room ID..."
            onChange={(e) => {
              setRoom(e.target.value);
            }}
          />
          <button onClick={joinRoom}>Join A Room</button>
        </>
      ) : (
        <Chat socket={socket} username={username} room={room} />
      )}
    </div>
  );
}

export default Socket;

// client > src > Chat.js
