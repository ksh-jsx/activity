import axios from "axios";
import type { NextPage } from "next";
import { QueryFunctionContext, useQuery } from "react-query";

interface Post {
  id: number;
  title: string;
  author: string;
  description: string;
}

interface User {
  nickname: string;
  email: string;
  postId: number;
}

const getPost = async ({ queryKey }: QueryFunctionContext) => {
  const { data } = await axios.get<Post>(`http://localhost:5000/posts/${queryKey[1]}`);
  return data;
};

const getUser = async ({ queryKey }: QueryFunctionContext) => {
  const { data } = await axios.get<User>(`http://localhost:5000/users/${queryKey[1]}`);
  return data;
};

const DependentQueriesPage: NextPage = () => {
  const { data: user } = useQuery(["user", "kkiri@example.com"], getUser);
  const { data: post } = useQuery(["post", user?.postId], getPost, {
    enabled: !!user?.postId,
  }); //user.postId가 있어야 enabled가 true가 돼서 두 번째 useQuery가 요청 시작
  // 두 번째 useQuery는 첫 번째 useQuery에 의존적이기 때문에 Dependent Queries

  console.log({ user });
  console.log({ post });

  return <div>Dependent Queries Page</div>;
};

export default DependentQueriesPage;
