const isServerSide = () => typeof window === "undefined";

const NAME_KEY = "test_nickName";

export const nameStorage = {
  get() {
    if (isServerSide()) return "";
    return sessionStorage.getItem(NAME_KEY);
  },
  set(name) {
    if (isServerSide()) return;
    sessionStorage.setItem(NAME_KEY, name);
  },
  remove() {
    if (isServerSide()) return;
    sessionStorage.removeItem(NAME_KEY);
  },
};
