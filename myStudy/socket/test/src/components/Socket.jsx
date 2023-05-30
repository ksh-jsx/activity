import { useEffect, useState } from "react";
import io from "socket.io-client";
import Chat from "./Chat";
import { nameStorage } from "@/lib/storage";

const socket = io.connect("http://localhost:4000"); //프론트를 백과 연결하는 부분

function Socket() {
  const [userName, setUserName] = useState("");
  const [roomName, setRoomName] = useState("");
  const [key, setKey] = useState("");
  const [roomList, setRoomList] = useState([]);
  const [showChat, setShowChat] = useState(false);

  const createRoom = () => {
    if (userName !== "" && roomName !== "") {
      nameStorage.set(userName);

      const newKey = generateRandomString();
      setKey(newKey);

      socket.emit("join_room", {
        roomName,
        userName,
        key: newKey,
        isExist: false,
      });

      setShowChat(true);
    } else {
      alert("닉네임과 방제를 입력하세요");
    }
  };

  const joinRoom = () => {
    if (userName !== "") {
      nameStorage.set(userName);

      socket.emit("join_room", { roomName, userName, key, isExist: true });

      setShowChat(true);
    } else {
      alert("닉네임을 입력하세요");
    }
  };

  const onClickRoom = (data) => {
    setRoomName(data.room);
    setKey(data.key);

    joinRoom();
  };

  const generateRandomString = (length = 10) => {
    const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    let result = "";

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      result += characters.charAt(randomIndex);
    }

    return result;
  };

  useEffect(() => {
    const name = nameStorage.get();
    setUserName(name);
  }, []);

  useEffect(() => {
    socket.on("room_list", (data) => {
      setRoomList((roomList) => [...roomList, data]);
    });
  }, [socket]);

  return (
    <div className="App">
      {!showChat ? (
        <>
          <h3>닉네임을 입력하세요</h3>
          <input
            type="text"
            placeholder="이름"
            value={userName}
            onChange={(e) => {
              setUserName(e.target.value);
            }}
          />
          <h3>방 만들기</h3>
          <input
            type="text"
            placeholder="방제"
            value={roomName}
            onChange={(e) => {
              setRoomName(e.target.value);
            }}
          />
          <button onClick={createRoom}>create</button>

          {roomList.length > 0 && <h3>방 리스트</h3>}
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
              제목: {data.roomName} 방장: {data.userName}
            </div>
          ))}
        </>
      ) : (
        <Chat socket={socket} userName={userName} roomName={roomName} roomKey={key} />
      )}
    </div>
  );
}

export default Socket;
