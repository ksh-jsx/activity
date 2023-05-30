import { useState, useEffect } from "react";
import io from "socket.io-client";
import Chat from "./Chat";

const socket = io.connect("http://localhost:4000"); //프론트를 백과 연결하는 부분

function Socket() {
  const [username, setUsername] = useState("");
  const [room, setRoom] = useState("");
  const [key, setKey] = useState("");
  const [roomList, setRoomList] = useState([]);
  const [showChat, setShowChat] = useState(false);

  const createRoom = () => {
    if (username !== "" && room !== "") {
      const newKey = generateRandomString();
      setKey(newKey);

      socket.emit("join_room", {
        room,
        username,
        key: newKey,
        isExist: false,
      });
      setShowChat(true);
    } else {
      alert("닉네임과 방제를 입력하세요");
    }
  };

  const joinRoom = () => {
    if (username !== "") {
      socket.emit("join_room", { room, username, key, isExist: true });
      setShowChat(true);
    } else {
      alert("닉네임을 입력하세요");
    }
  };

  const onClickRoom = (data) => {
    setRoom(data.room);
    setKey(data.key);

    joinRoom();
  };

  const generateRandomString = (length = 10) => {
    const characters =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    let result = "";

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      result += characters.charAt(randomIndex);
    }

    return result;
  };

  useEffect(() => {
    socket.on("room_list", (data) => {
      setRoomList((roomList) => [...roomList, data]);
    });
  }, [socket]);

  return (
    <div className="App">
      {!showChat ? (
        <>
          <h3>create A Room</h3>
          <input
            type="text"
            placeholder="이름"
            onChange={(e) => {
              setUsername(e.target.value);
            }}
          />
          <input
            type="text"
            placeholder="방제"
            onChange={(e) => {
              setRoom(e.target.value);
            }}
          />
          <button onClick={createRoom}>create</button>

          <h3>Room List</h3>
          {roomList.map((data) => (
            <div
              key={data.key}
              onClick={() => onClickRoom(data)}
              style={{
                background: "aliceblue",
                margin: "5px",
                cursor: "pointer",
              }}
            >
              제목: {data.room} 방장: {data.username}
            </div>
          ))}
        </>
      ) : (
        <Chat socket={socket} username={username} room={room} roomKey={key} />
      )}
    </div>
  );
}

export default Socket;
