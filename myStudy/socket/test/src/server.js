const express = require("express");
const app = express();
const http = require("http");
const cors = require("cors");
const { Server } = require("socket.io");
app.use(cors());

const server = http.createServer(app);

const io = new Server(server, {
  cors: {
    origin: "http://localhost:3000", //백을 프론트와 연결하는 부분
    method: ["GET", "POST"],
  },
});

io.on("connection", (socket) => {
  //유저를 특정 룸에 조인시키기
  socket.on("join_room", (data) => {
    console.log(data.key);
    socket.join(data.key);
    !data.isExist && io.emit("room_list", data);
  });

  //메시지를 받아 특정 룸에 메시지 전송하기
  socket.on("send_message", (data) => {
    socket.to(data.key).emit("receive_message", data);
  });

  socket.on("disconnect", () => {
    console.log("User Disconnected", socket.id);
  });
});

server.listen(4000, () => {
  console.log("SERVER RUNNING");
});
