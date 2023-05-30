import Channel from "@/components/channel";
import { useRouter } from "next/router";

const ChatPage = () => {
  const router = useRouter();
  const { id } = router.query;

  return (
    <>
      <Channel id={id} />
    </>
  );
};

export default ChatPage;
