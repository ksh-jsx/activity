lotto = [0] * 7

def go(idx, start, n, m):
    if idx == 7:
        tmp = 0
        for i in lotto:
            tmp+=i
        if tmp == 100:
            lotto.sort()
            for i in lotto:
                print(i)
        return

    for i in range(start, len(n)):
        lotto[idx] = n[i]
        go(idx + 1, i + 1, n, m)
    
arr = []
for _ in range(9):
    arr.append(int(input()))

go(0, 0, arr, 9)
